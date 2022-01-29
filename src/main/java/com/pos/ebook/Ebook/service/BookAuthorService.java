package com.pos.ebook.Ebook.service;

import com.pos.ebook.Ebook.model.Author;
import com.pos.ebook.Ebook.model.Book;
import com.pos.ebook.Ebook.model.Book_Author;
import com.pos.ebook.Ebook.repository.BookAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Timofti Gabriel
 */

@Service
public class BookAuthorService {
    private final BookAuthorRepository bookAuthorRepository;

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @Autowired
    public BookAuthorService(BookAuthorRepository bookAuthorRepository) {
        this.bookAuthorRepository = bookAuthorRepository;
    }

    public boolean existsAuthorById(Integer id) {
        return authorService.existsAuthorById(Long.valueOf(id));
    }

    public List<Integer> getAuthorsIdsByBookIsbn(String isbn) {
        return bookAuthorRepository.findAuthorsIdByBookIsbn(isbn);
    }

    public List<Book> getBooksByAuthorId(Long id_author) {
        List<String> booksIsbns =  bookAuthorRepository.findBooksIsbnByAuthorId(Math.toIntExact(id_author));

        return booksIsbns.stream().map(isbn -> bookService.getBookByIsbn(isbn)
                .orElseThrow()).collect(Collectors.toList());
    }

    public List<Author> getAuthorsByBookIsbn(String isbn) {
        return getAuthorsIdsByBookIsbn(isbn).stream().map(id -> authorService.getAuthorById(Long.valueOf(id))
                .orElseThrow()).collect(Collectors.toList());
    }

    public void addBookAuthor(String isbn, Integer id) {
        Book_Author book_author = new Book_Author();
        book_author.setIsbn_book(isbn);
        book_author.setId_author(id);

        bookAuthorRepository.save(book_author);
    }

    public void deleteBookAuthors(String isbn) {
        bookAuthorRepository.deleteBookAuthors(isbn);
    }

    public void deleteBookAuthor(String isbn, Integer id) {
        bookAuthorRepository.deleteBookAuthor(isbn, id);
    }
}
