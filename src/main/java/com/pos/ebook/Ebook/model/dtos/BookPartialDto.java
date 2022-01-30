package com.pos.ebook.Ebook.model.dtos;

import com.pos.ebook.Ebook.model.Book;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Timofti Gabriel
 */

@Data
@Accessors(chain = true)
public class BookPartialDto {
    String isbn;
    String title;
    String genre;

    public static BookPartialDto from(Book book) {
        return new BookPartialDto()
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle())
                .setGenre(book.getGenre());
    }
}
