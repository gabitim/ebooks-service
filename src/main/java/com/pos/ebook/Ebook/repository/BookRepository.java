package com.pos.ebook.Ebook.repository;

import com.pos.ebook.Ebook.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Timofti Gabriel
 */

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByGenre(String genre);
    List<Book> findByPublishingYear(Integer publishingYear);
    List<Book> findByGenreAndPublishingYear(String genre, Integer publishingYear);
    Page<Book> findAll(Pageable pageable);
    Page<Book> findByGenre(String genre, Pageable pageable);
    Page<Book> findByPublishingYear(Integer publishingYear, Pageable pageable);
    Page<Book> findByGenreAndPublishingYear(String genre, Integer publishingYear, Pageable pageable);
}
