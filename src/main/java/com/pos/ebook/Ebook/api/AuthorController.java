package com.pos.ebook.Ebook.api;

import com.pos.ebook.Ebook.model.Author;
import com.pos.ebook.Ebook.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Timofti Gabriel
 */

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping
    List<Author> getAuthors() {
        return authorService.getAuthors();
    }

//    @GetMapping("/{id}")
//    Author getAuthorById(@PathVariable Long id) {
//        return authorService.getAuthorById(id);
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Author addAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }

    @PutMapping("/{id}")
    ResponseEntity<Author> replaceAuthor(@RequestBody Author author, @PathVariable Long id) {
        return authorService.replaceAuthor(author, id);
    }

//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    void deleteAuthor(@PathVariable Long id) {
//        authorService.deleteAuthor(id);
//    }
}
