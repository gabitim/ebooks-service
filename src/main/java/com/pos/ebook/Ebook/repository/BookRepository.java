package com.pos.ebook.Ebook.repository;

import com.pos.ebook.Ebook.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Timofti Gabriel
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
