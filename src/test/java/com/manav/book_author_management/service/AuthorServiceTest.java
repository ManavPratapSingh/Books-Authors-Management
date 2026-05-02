package com.manav.book_author_management.service;

import com.manav.book_author_management.entity.Author;
import com.manav.book_author_management.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    private Author author;

    @BeforeEach
    void setUp() {
        author = Author.builder()
                .id(1L)
                .name("John Doe")
                .bio("A famous writer")
                .build();
    }

    @Test
    void getAllAuthors_ShouldReturnList() {
        when(authorRepository.findAll()).thenReturn(Arrays.asList(author));

        List<Author> result = authorService.getAllAuthors();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void getAuthorById_WhenExists_ShouldReturnAuthor() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Author result = authorService.getAuthorById(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

    @Test
    void getAuthorById_WhenNotExists_ShouldThrowException() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> authorService.getAuthorById(1L));
    }

    @Test
    void createAuthor_ShouldReturnSavedAuthor() {
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        Author result = authorService.createAuthor(new Author());

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }
}
