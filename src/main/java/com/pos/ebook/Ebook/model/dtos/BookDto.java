package com.pos.ebook.Ebook.model.dtos;

import com.pos.ebook.Ebook.model.Book;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

/**
 * @author Timofti Gabriel
 */

@Data
@Accessors(chain = true)
public class BookDto {
    @NotBlank(message = "isbn field is required")
    @Size(min = 1, max = 10, message = "isbn field size must be between 1 and 10")
    private String isbn;

    @NotBlank(message = "title field is required")
    @Size(min = 1, max = 50, message = "title field size must be between 1 and 50")
    private String title;

    @NotBlank(message = "publishing_house field is required")
    @Size(min = 1, max = 50, message = "publishing_house field size must be between 1 and 50")
    private String publishing_house;

    @NotNull(message = "publishing_year field is required")
    private Integer publishing_year;

    @NotBlank(message = "genre field is required")
    @Size(min = 1, max = 50, message = "genre field size must be between 1 and 50")
    private String genre;

    public Book toBook() {
        return new Book(isbn, title, publishing_house, publishing_year, genre);
    }

    public static BookDto from(Book book) {
        return new BookDto()
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle())
                .setPublishing_house(book.getPublishing_house())
                .setPublishing_year(book.getPublishing_year())
                .setGenre(book.getGenre());
    }
}
