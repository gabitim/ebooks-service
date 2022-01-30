package com.pos.ebook.Ebook.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Timofti Gabriel
 */

@Entity
@Table(name = "book")
public class Book {

    @Column(name = "isbn")
    @Id
    private String isbn;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "publishing_house", nullable = false)
    private String publishingHouse;

    @Column(name = "publishing_year", nullable = false)
    private Integer publishingYear;

    @Column(name = "genre", nullable = false)
    private String genre;

    public Book() {}

    public Book(String isbn, String title, String publishingHouse, Integer publishingYear, String genre ) {
        this.isbn = isbn;
        this.title = title;
        this.publishingHouse = publishingHouse;
        this.publishingYear = publishingYear;
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public Integer getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(Integer publishingYear) {
        this.publishingYear = publishingYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn) &&
                Objects.equals(title, book.title) &&
                Objects.equals(publishingHouse, book.publishingHouse) &&
                Objects.equals(publishingYear, book.publishingYear) &&
                Objects.equals(genre, book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, publishingHouse, publishingYear, genre);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", publishingHouse='" + publishingHouse + '\'' +
                ", publishingYear=" + publishingYear +
                ", genre='" + genre + '\'' +
                '}';
    }
}
