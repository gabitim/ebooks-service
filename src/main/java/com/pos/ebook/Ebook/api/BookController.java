package com.pos.ebook.Ebook.api;

import com.pos.ebook.Ebook.model.Book;
import com.pos.ebook.Ebook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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
    Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/{id}")
    Book replaceBook(@RequestBody Book book, @PathVariable Long id) {
        return bookService.replaceBook(book, id);
    }

    @DeleteMapping("/{id}")
    void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
