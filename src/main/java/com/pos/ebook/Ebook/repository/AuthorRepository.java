package com.pos.ebook.Ebook.repository;

import com.pos.ebook.Ebook.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Timofti Gabriel
 */

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
