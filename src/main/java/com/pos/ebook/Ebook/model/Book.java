package com.pos.ebook.Ebook.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Timofti Gabriel
 */

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long ISBN;
    @Column(unique = true)
    private String Titlu;
    private String Editura;
    private int An_publicare;
    private String Gen_literar;

    public Book() {
    }

    public Book(String Titlu, String Editura, int An_publicare, String Gen_literar ) {
        this.Titlu = Titlu;
        this.Editura = Editura;
        this.An_publicare = An_publicare;
        this.Gen_literar = Gen_literar;
    }

    public Long getISBN() {
        return ISBN;
    }

    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitlu() {
        return Titlu;
    }

    public void setTitlu(String titlu) {
        Titlu = titlu;
    }

    public String getEditura() {
        return Editura;
    }

    public void setEditura(String editura) {
        Editura = editura;
    }

    public int getAn_publicare() {
        return An_publicare;
    }

    public void setAn_publicare(int an_publicare) {
        An_publicare = an_publicare;
    }

    public String getGen_literar() {
        return Gen_literar;
    }

    public void setGen_literar(String gen_literar) {
        Gen_literar = gen_literar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(ISBN, book.ISBN) &&
                Objects.equals(Titlu, book.Titlu) &&
                Objects.equals(Editura, book.Editura) &&
                Objects.equals(An_publicare, book.An_publicare) &&
                Objects.equals(Gen_literar, book.Gen_literar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN, Titlu, Editura, An_publicare, Gen_literar);
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", Titlu='" + Titlu + '\'' +
                ", Editura='" + Editura + '\'' +
                ", An_publicare=" + An_publicare +
                ", Gen_literar='" + Gen_literar + '\'' +
                '}';
    }
}
