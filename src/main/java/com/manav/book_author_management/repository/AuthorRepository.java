package com.manav.book_author_management.repository;

import com.manav.book_author_management.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // JpaRepository provides:
    // findAll() -> getAllAuthors
    // findById() -> getAuthorById
    // save() -> createAuthor / updateAuthor
}
