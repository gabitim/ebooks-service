package com.pos.ebook.Ebook.model.dtos;

import com.pos.ebook.Ebook.model.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

/**
 * @author Timofti Gabriel
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class BookDto extends BookPartialDto {
    @NotBlank(message = "isbn field is required")
    @Size(min = 1, max = 10, message = "isbn field size must be between 1 and 10")
    private String isbn;

    @NotBlank(message = "title field is required")
    @Size(min = 1, max = 50, message = "title field size must be between 1 and 50")
    private String title;

    @NotBlank(message = "publishingHouse field is required")
    @Size(min = 1, max = 50, message = "publishingHouse field size must be between 1 and 50")
    private String publishingHouse;

    @NotNull(message = "publishingYear field is required")
    private Integer publishingYear;

    @NotBlank(message = "genre field is required")
    @Size(min = 1, max = 50, message = "genre field size must be between 1 and 50")
    private String genre;

    public Book toBook() {
        return new Book(isbn, title, publishingHouse, publishingYear, genre);
    }

    public static BookDto from(Book book) {
        return new BookDto()
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle())
                .setPublishingHouse(book.getPublishingHouse())
                .setPublishingYear(book.getPublishingYear())
                .setGenre(book.getGenre());
    }
}
