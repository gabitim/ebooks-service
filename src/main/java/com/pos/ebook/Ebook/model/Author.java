package com.pos.ebook.Ebook.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Timofti Gabriel
 */

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String Prenume;
    private String Nume;

    public Author() {}

    public Author(Long ID, String prenume, String nume) {
        this.ID = ID;
        Prenume = prenume;
        Nume = nume;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getPrenume() {
        return Prenume;
    }

    public void setPrenume(String prenume) {
        Prenume = prenume;
    }

    public String getNume() {
        return Nume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(ID, author.ID) && Objects.equals(Prenume, author.Prenume) && Objects.equals(Nume, author.Nume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, Prenume, Nume);
    }

    @Override
    public String toString() {
        return "Author{" +
                "ID=" + ID +
                ", Prenume='" + Prenume + '\'' +
                ", Nume='" + Nume + '\'' +
                '}';
    }
}
