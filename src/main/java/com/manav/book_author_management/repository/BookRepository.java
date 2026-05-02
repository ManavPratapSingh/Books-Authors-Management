package com.manav.book_author_management.repository;

import com.manav.book_author_management.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b JOIN FETCH b.author")
    List<Book> getAllBooksWithAuthors();

    // JpaRepository provides:
    // findAll() -> getAllBooks
    // findById() -> getBookById
    // save() -> createBook / updateBook
}
