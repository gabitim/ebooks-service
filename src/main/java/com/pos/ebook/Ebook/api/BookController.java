package com.pos.ebook.Ebook.api;

import com.pos.ebook.Ebook.exceptions.ResourceConflictException;
import com.pos.ebook.Ebook.exceptions.CustomDataIntegrityViolationExceptionHelper;
import com.pos.ebook.Ebook.exceptions.ResourceNotAcceptableException;
import com.pos.ebook.Ebook.exceptions.ResourceNotFoundException;
import com.pos.ebook.Ebook.model.dtos.BookDto;
import com.pos.ebook.Ebook.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Timofti Gabriel
 */

@Validated
@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    List<BookDto> getBooks() {
        return bookService.getBooks().stream().map(BookDto::from).collect(Collectors.toList());
    }

    @GetMapping("/books/{isbn}")
    BookDto getBookByIsbn(@PathVariable String isbn) throws ResourceNotFoundException {
        return BookDto.from(bookService.getBookByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("No book found with isbn: " + isbn))
        );
    }

    @PostMapping
    ResponseEntity<BookDto> addBook(@Valid @RequestBody BookDto bookDto) throws ResourceConflictException {
        if (bookService.existsBookByIsbn(bookDto.getIsbn())) {
            throw new ResourceConflictException("There is already a book with isbn: " + bookDto.getIsbn());
        }
        else {
            try {
                return new ResponseEntity<>(BookDto.from(bookService.addBook(bookDto.toBook())), HttpStatus.CREATED);
            }
            catch (DataIntegrityViolationException exception) {
                String message = new CustomDataIntegrityViolationExceptionHelper(exception).getMessage();

                throw new ResourceConflictException(message);
            }
        }
    }

    @PutMapping("books/{isbn}")
    ResponseEntity<BookDto> replaceBook(@Valid @RequestBody BookDto bookDto, @PathVariable String isbn)
            throws ResourceConflictException, ResourceNotAcceptableException {
        HttpStatus httpStatus;

        if(! bookDto.getIsbn().equals(isbn)) {
            throw new ResourceNotAcceptableException("isbn body field doesn't match the isbn path variable");
        }

        if (bookService.existsBookByIsbn(isbn)) {
            httpStatus = HttpStatus.NO_CONTENT;
        }
        else {
            httpStatus = HttpStatus.CREATED;
        }

        try {
            return new ResponseEntity<>(BookDto.from(bookService.replaceBook(bookDto.toBook(), isbn)), httpStatus);
        }
        catch (DataIntegrityViolationException exception) {
            String message = new CustomDataIntegrityViolationExceptionHelper(exception).getMessage();

            throw new ResourceConflictException(message);
        }
    }

    @DeleteMapping("books/{isbn}")
    ResponseEntity<?> deleteBook(@PathVariable String isbn) throws ResourceNotFoundException {
        if (bookService.existsBookByIsbn(isbn)) {
            bookService.deleteBook(isbn);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            throw new ResourceNotFoundException("No book found with isbn: " + isbn);
        }
   }
}
