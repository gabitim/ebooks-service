package com.pos.ebook.Ebook.exceptions.books;

/**
 * @author Timofti Gabriel
 */
public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(String id) {
        super("Could not find book " + id);
    }
}
