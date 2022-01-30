package com.pos.ebook.Ebook.repository;

import com.pos.ebook.Ebook.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Timofti Gabriel
 */

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);
    List<Author> findAuthorByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}
