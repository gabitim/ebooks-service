package com.pos.ebook.Ebook.api;

import com.pos.ebook.Ebook.assembler.AuthorAssembler;
import com.pos.ebook.Ebook.exceptions.ResourceConflictException;
import com.pos.ebook.Ebook.exceptions.ResourceNotFoundException;
import com.pos.ebook.Ebook.model.dtos.AuthorDto;
import com.pos.ebook.Ebook.service.AuthorService;
import com.pos.ebook.Ebook.service.BookAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Timofti Gabriel
 */

@Validated
@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookAuthorService bookAuthorService;

    @Autowired
    AuthorAssembler authorAssembler;

    @GetMapping("/authors")
    public List<AuthorDto> getAuthors(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "match", required = false) String match
            ) {
        System.out.println(name + " " + match);
        if (name != null && !Objects.equals(match, "exact")) {
            return authorService.findAuthorsByPartialName(name).stream().map(AuthorDto::from).collect(Collectors.toList());
        }
        if (name != null && Objects.equals(match, "exact")) {
            return authorService.findAuthorByExactName(name).stream().map(AuthorDto::from).collect(Collectors.toList());
        }
        else {
            return authorService.getAuthors().stream().map(AuthorDto::from).collect(Collectors.toList());
        }
    }

    @GetMapping("/authors/{id}")
    public EntityModel<AuthorDto> getAuthorById(@PathVariable Long id) throws ResourceNotFoundException {
        AuthorDto author = AuthorDto.from(authorService.getAuthorById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No author found with this id: " + id)));

        return authorAssembler.toModel(author);
    }

    @GetMapping("/books/{isbn}/authors")
    public CollectionModel<AuthorDto> getBookAuthors(@PathVariable String isbn) throws ResourceNotFoundException {
        List<AuthorDto> authors =  bookAuthorService.getAuthorsByBookIsbn(isbn)
                .stream().map(AuthorDto::from).collect(Collectors.toList());

        return authorAssembler.toCollectionModel(authors, isbn);
    }

    @PostMapping("/authors")
    ResponseEntity<AuthorDto> addAuthor(@Valid @RequestBody AuthorDto authorDto) throws ResourceConflictException {
        if (authorService.existsAuthor(authorDto.toAuthor())) {
            throw new ResourceConflictException(
                    "There is already an author with this name: " + authorDto.getFirstName() + " " + authorDto.getLastName());
        }
        else {
            return new ResponseEntity<>(AuthorDto.from(authorService.addAuthor(authorDto.toAuthor())), HttpStatus.CREATED);
        }
    }

    @PutMapping("/authors/{id}")
    ResponseEntity<AuthorDto> replaceAuthor(@Valid @RequestBody AuthorDto authorDto, @PathVariable Long id)
            throws ResourceConflictException {
        HttpStatus httpStatus;

        if (authorService.existsAuthor(authorDto.toAuthor())) {
            throw new ResourceConflictException(
                    "There is already an author with this name: " + authorDto.getFirstName() + " " + authorDto.getLastName());
        }

        if(authorService.existsAuthorById(id)) {
            httpStatus = HttpStatus.NO_CONTENT;
        }
        else {
            httpStatus = HttpStatus.CREATED;
        }

        return new ResponseEntity<>(AuthorDto.from(authorService.replaceAuthor(authorDto.toAuthor(), id)), httpStatus);
    }
}
