package com.pos.ebook.Ebook.api;

import com.pos.ebook.Ebook.exceptions.ResourceNotFoundException;
import com.pos.ebook.Ebook.model.dtos.AuthorDto;
import com.pos.ebook.Ebook.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Timofti Gabriel
 */

@Validated
@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping
    List<AuthorDto> getAuthors() {
        return authorService.getAuthors().stream().map(AuthorDto::from).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    AuthorDto getAuthorById(@PathVariable Long id) throws ResourceNotFoundException {
        return AuthorDto.from(authorService.getAuthorById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No author found with this id: " + id))
        );
    }
}
