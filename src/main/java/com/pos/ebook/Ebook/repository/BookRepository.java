package com.pos.ebook.Ebook.repository;

import com.pos.ebook.Ebook.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Timofti Gabriel
 */

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    Page<Book> findAll(Pageable pageable);
}
