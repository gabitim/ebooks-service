package com.pos.ebook.Ebook.repository;

import com.pos.ebook.Ebook.model.Book_Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Timofti Gabriel
 */

@Repository
public interface BookAuthorRepository extends JpaRepository<Book_Author, Long> {

    @Query("SELECT isbn_book FROM Book_Author WHERE id_author = :id_author")
    List<String> findBooksIsbnByAuthorId(@Param("id_author") Integer id);

    @Query("SELECT id_author FROM Book_Author WHERE isbn_book = :isbn_book")
    List<Integer> findAuthorsIdByBookIsbn(@Param("isbn_book") String isbn);

    @Transactional
    @Modifying
    @Query("DELETE FROM Book_Author WHERE isbn_book = :isbn_book")
    void deleteBookAuthors(@Param("isbn_book") String isbn);

    @Transactional
    @Modifying
    @Query("DELETE FROM Book_Author WHERE isbn_book = :isbn_book AND id_author = :id_author")
    void deleteBookAuthor(@Param("isbn_book") String isbn, @Param("id_author") Integer id);
}
