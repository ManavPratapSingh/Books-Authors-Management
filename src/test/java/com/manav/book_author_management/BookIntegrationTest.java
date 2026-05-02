package com.manav.book_author_management;

import com.manav.book_author_management.entity.Author;
import com.manav.book_author_management.entity.Book;
import com.manav.book_author_management.repository.AuthorRepository;
import com.manav.book_author_management.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BookIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    private Author testAuthor;

    @BeforeEach
    void setUp() {
        testAuthor = Author.builder()
                .name("Jane Smith")
                .bio("Mystery novelist")
                .build();
        testAuthor = authorRepository.save(testAuthor);
    }

    @Test
    void testBookCruFlow() throws Exception {
        // 1. Read: List books
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/list"))
                .andExpect(model().attributeExists("books"));

        // 2. Create: Show form
        mockMvc.perform(get("/books/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/form"))
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attributeExists("authors"));

        // 3. Create: Submit form
        mockMvc.perform(post("/books")
                        .param("name", "The Secret Case")
                        .param("isbn", "123-456-789")
                        .param("genre", "Mystery")
                        .param("description", "A thrilling case.")
                        .param("author.id", testAuthor.getId().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));

        // Verify book was saved
        Book savedBook = bookRepository.findAll().get(0);
        
        // 4. Update: Show edit form
        mockMvc.perform(get("/books/edit/" + savedBook.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("books/form"))
                .andExpect(model().attribute("book", hasProperty("name", is("The Secret Case"))));

        // 5. Update: Submit update
        mockMvc.perform(post("/books/update")
                        .param("id", savedBook.getId().toString())
                        .param("name", "The Secret Case - Updated")
                        .param("isbn", "123-456-789")
                        .param("genre", "Mystery")
                        .param("author.id", testAuthor.getId().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
    }
}
