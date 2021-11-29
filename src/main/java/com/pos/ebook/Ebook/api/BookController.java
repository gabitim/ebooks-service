package com.pos.ebook.Ebook.api;

import com.pos.ebook.Ebook.model.Book;
import com.pos.ebook.Ebook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Timofti Gabriel
 */

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    Book getBookById(@PathVariable String id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    ResponseEntity<Book> addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/{id}")
    ResponseEntity<Book> replaceBook(@RequestBody Book book, @PathVariable String id) {
        return bookService.replaceBook(book, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
    }
}
