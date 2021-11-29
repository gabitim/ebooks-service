package com.pos.ebook.Ebook.service;

import com.pos.ebook.Ebook.exceptions.conflict.ConflictException;
import com.pos.ebook.Ebook.exceptions.notFound.NotFoundException;
import com.pos.ebook.Ebook.model.Book;
import com.pos.ebook.Ebook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Timofti Gabriel
 */

@Service
public class BookService {
    private final BookRepository bookRepository;
    private static final String BOOK = "Book";

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BOOK, id));
    }

    public ResponseEntity<Book> addBook(Book book) {
        if(bookRepository.findById(book.getISBN()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(bookRepository.save(book));
        }
        else {
            throw new ConflictException(BOOK, book.getISBN());
        }
    }

    public ResponseEntity<Book> replaceBook(Book book, String id) {
        return bookRepository.findById(id)
                .map(oldBook -> {
                    oldBook.setTitlu(book.getTitlu());
                    oldBook.setEditura(book.getEditura());
                    oldBook.setAn_publicare(book.getAn_publicare());
                    oldBook.setGen_literar(book.getGen_literar());

                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(bookRepository.save(oldBook));
                })
                .orElseGet(() -> {
                    book.setISBN(id);
                    return ResponseEntity.status(HttpStatus.CREATED).body(bookRepository.save(book));
                });
    }

    public void deleteBook(String id) {
        try {
            bookRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException exception) {
            // we would log this
            throw(new NotFoundException(BOOK, id));
        }
    }
}

//todo
// 1. put/post verify content (numbers vs strings, missing or more fields) and throw 406 - Not Acceptable
// 2. put/post verify content for unique conflicts ( book title, and same author twice )
// 3. book_author service
// 4. hateoas