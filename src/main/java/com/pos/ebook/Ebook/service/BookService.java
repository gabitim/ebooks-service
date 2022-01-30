package com.pos.ebook.Ebook.service;

import com.pos.ebook.Ebook.model.Book;
import com.pos.ebook.Ebook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Timofti Gabriel
 */

@Service
public class BookService {
    private final BookRepository bookRepository;

    private final static int DEFAULT_PAGE_SIZE = 10;

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

    public List<Book> getBooks(int page) {
        return getBooks(page, DEFAULT_PAGE_SIZE);
    }

    public List<Book> getBooks(int page, int noItems) {
        Pageable pageable = PageRequest.of(page, noItems);

        Page<Book> pagedBooks = bookRepository.findAll(pageable);

        if(pagedBooks.hasContent()) {
            return pagedBooks.getContent();
        }
        else {
            return new ArrayList<>();
        }
    }

    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    public List<Book> getBooksByGenre(String genre, int page) {
        return getBooksByGenre(genre, page, DEFAULT_PAGE_SIZE);
    }

    public List<Book> getBooksByGenre(String genre, int page, int noItems) {
        Pageable pageable = PageRequest.of(page, noItems);

        Page<Book> pagedBooks = bookRepository.findByGenre(genre, pageable);

        if(pagedBooks.hasContent()) {
            return pagedBooks.getContent();
        }
        else {
            return new ArrayList<>();
        }
    }

    public List<Book> getBooksByPublishingYear(Integer year) {
        return bookRepository.findByPublishingYear(year);
    }

    public List<Book> getBooksByPublishingYear(Integer year, int page) {
        return getBooksByPublishingYear(year, page, DEFAULT_PAGE_SIZE);
    }

    public List<Book> getBooksByPublishingYear(Integer year, int page, int noItems) {
        Pageable pageable = PageRequest.of(page, noItems);

        Page<Book> pagedBooks = bookRepository.findByPublishingYear(year, pageable);

        if(pagedBooks.hasContent()) {
            return pagedBooks.getContent();
        }
        else {
            return new ArrayList<>();
        }
    }

    public List<Book> getBooksByGenreAndPublishingYear(String genre, Integer year) {
        return bookRepository.findByGenreAndPublishingYear(genre, year);
    }

    public List<Book> getBooksByGenreAndPublishingYear(String genre, Integer year, int page) {
        return getBooksByGenreAndPublishingYear(genre, year, page, DEFAULT_PAGE_SIZE);
    }

    public List<Book> getBooksByGenreAndPublishingYear(String genre, Integer year, int page, int noItems) {
        Pageable pageable = PageRequest.of(page, noItems);

        Page<Book> pagedBooks = bookRepository.findByGenreAndPublishingYear(genre, year, pageable);

        if(pagedBooks.hasContent()) {
            return pagedBooks.getContent();
        }
        else {
            return new ArrayList<>();
        }
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
                    book.setPublishingHouse(newBook.getPublishingHouse());
                    book.setPublishingYear(newBook.getPublishingYear());
                    book.setGenre(newBook.getGenre());

                    return bookRepository.save(book);
                })
                .orElseGet(() -> addBook(newBook));
    }

    public void deleteBook(String isbn) {
        bookRepository.deleteById(isbn);
    }
}

//todo hateoas