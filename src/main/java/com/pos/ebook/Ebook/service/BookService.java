package com.pos.ebook.Ebook.service;

import com.pos.ebook.Ebook.model.Book;
import com.pos.ebook.Ebook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Timofti Gabriel
 */

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public boolean existsBookByIsbn(String isbn) {
        return bookRepository.existsById(isbn);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findById(isbn);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book replaceBook(Book newBook, String id) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(newBook.getTitle());
                    book.setPublishing_house(newBook.getPublishing_house());
                    book.setPublishing_year(newBook.getPublishing_year());
                    book.setGenre(newBook.getGenre());

                    return bookRepository.save(book);
                })
                .orElseGet(() -> addBook(newBook));
    }

    public void deleteBook(String isbn) {
        bookRepository.deleteById(isbn);
    }
}

//todo
// 1. rename fields in eng camelCase
// 2. book_author service
// 3. hateoas