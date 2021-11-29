package com.pos.ebook.Ebook.service;

import com.pos.ebook.Ebook.exceptions.books.BookNotFoundException;
import com.pos.ebook.Ebook.model.Book;
import com.pos.ebook.Ebook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book replaceBook(Book book, String id) {
        return bookRepository.findById(id)
                .map(oldBook -> {
                    oldBook.setTitlu(book.getTitlu());
                    oldBook.setEditura(book.getEditura());
                    oldBook.setAn_publicare(book.getAn_publicare());
                    oldBook.setGen_literar(book.getGen_literar());

                    return bookRepository.save(oldBook);
                })
                .orElseGet(() -> {
                    book.setISBN(id);
                    return bookRepository.save(book);
                });
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }
}
