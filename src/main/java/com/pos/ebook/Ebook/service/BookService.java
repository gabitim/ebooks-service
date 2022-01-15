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

    public boolean existsBookById(String id) {
        return bookRepository.existsById(id);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(String id) {
        return bookRepository.findById(id);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book replaceBook(Book newBook, String id) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitlu(newBook.getTitlu());
                    book.setEditura(newBook.getEditura());
                    book.setAn_publicare(newBook.getAn_publicare());
                    book.setGen_literar(newBook.getGen_literar());

                    return bookRepository.save(book);
                })
                .orElseGet(() -> addBook(newBook));
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }
}

//todo
// 1. rename fields in eng camelCase
// 2. book_author service
// 3. hateoas