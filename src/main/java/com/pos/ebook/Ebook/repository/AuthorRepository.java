package com.pos.ebook.Ebook.repository;

import com.pos.ebook.Ebook.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Timofti Gabriel
 */

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT author FROM Author author WHERE first_name = :first_name AND last_name = :last_name")
    Optional<Author> findAuthor(@Param("first_name") String first_name, @Param("last_name") String last_name);
}
