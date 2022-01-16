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
    private String publishing_house;

    @Column(name = "publishing_year", nullable = false)
    private Integer publishing_year;

    @Column(name = "genre", nullable = false)
    private String genre;

    public Book() {}

    public Book(String isbn, String title, String publishing_house, Integer publishing_year, String genre ) {
        this.isbn = isbn;
        this.title = title;
        this.publishing_house = publishing_house;
        this.publishing_year = publishing_year;
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

    public String getPublishing_house() {
        return publishing_house;
    }

    public void setPublishing_house(String publishing_house) {
        this.publishing_house = publishing_house;
    }

    public Integer getPublishing_year() {
        return publishing_year;
    }

    public void setPublishing_year(Integer publishing_year) {
        this.publishing_year = publishing_year;
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
                Objects.equals(publishing_house, book.publishing_house) &&
                Objects.equals(publishing_year, book.publishing_year) &&
                Objects.equals(genre, book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, publishing_house, publishing_year, genre);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", publishingHouse='" + publishing_house + '\'' +
                ", publishingYear=" + publishing_year +
                ", genre='" + genre + '\'' +
                '}';
    }
}
