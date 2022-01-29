package com.pos.ebook.Ebook.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Timofti Gabriel
 */

@Entity
@Table(name = "book_author")
public class Book_Author {

    @Column(name = "index", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long index;

    @Column(name = "isbn_book", nullable = false)
    private String isbn_book;

    @Column(name = "id_author", nullable = false)
    private Integer id_author;

    public Book_Author() {}

    public Book_Author(Long index, String isbn_book, Integer id_author) {
        this.index = index;
        this.isbn_book = isbn_book;
        this.id_author = id_author;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getIsbn_book() {
        return isbn_book;
    }

    public void setIsbn_book(String isbn_book) {
        this.isbn_book = isbn_book;
    }

    public Integer getId_author() {
        return id_author;
    }

    public void setId_author(Integer id_author) {
        this.id_author = id_author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book_Author that = (Book_Author) o;
        return Objects.equals(index, that.index) &&
                Objects.equals(isbn_book, that.isbn_book) &&
                Objects.equals(id_author, that.id_author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, isbn_book, id_author);
    }

    @Override
    public String toString() {
        return "BookAuthor{" +
                "index=" + index +
                ", isbn_book='" + isbn_book + '\'' +
                ", id_author=" + id_author +
                '}';
    }
}
