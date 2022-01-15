package com.pos.ebook.Ebook.model.dtos;

import com.pos.ebook.Ebook.model.Book;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

/**
 * @author Timofti Gabriel
 */

@Data
@Accessors(chain = true)
public class BookDto {
    @NotBlank(message = "isbn field is required")
    @Size(min = 1, max = 10, message = "isbn field size must be between 1 and 10")
    private String ISBN;

    @NotBlank(message = "titlu field is required")
    @Size(min = 1, max = 50, message = "titlu field size must be between 1 and 50")
    private String Titlu;

    @NotBlank(message = "editura field is required")
    @Size(min = 1, max = 50, message = "editura field size must be between 1 and 50")
    private String Editura;

    @NotNull(message = "an_publicare field is required")
    private Integer An_publicare;

    @NotBlank(message = "gen_literar field is required")
    @Size(min = 1, max = 50, message = "gen_literar field size must be between 1 and 50")
    private String Gen_literar;

    public Book toBook() {
        return new Book(ISBN, Titlu, Editura, An_publicare, Gen_literar);
    }

    public static BookDto from(Book book) {
        return new BookDto()
                .setISBN(book.getISBN())
                .setTitlu(book.getTitlu())
                .setEditura(book.getEditura())
                .setAn_publicare(book.getAn_publicare())
                .setGen_literar(book.getGen_literar());
    }
}
