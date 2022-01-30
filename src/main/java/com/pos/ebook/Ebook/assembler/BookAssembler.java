package com.pos.ebook.Ebook.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.pos.ebook.Ebook.api.AuthorController;
import com.pos.ebook.Ebook.api.BookController;
import com.pos.ebook.Ebook.exceptions.ResourceNotFoundException;
import com.pos.ebook.Ebook.model.dtos.BookDto;

import com.pos.ebook.Ebook.model.dtos.BookPartialDto;
import lombok.SneakyThrows;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * @author Timofti Gabriel
 */

@Component
public class BookAssembler implements RepresentationModelAssembler<BookDto, EntityModel<BookDto>> {

    @SneakyThrows
    @Override
    public EntityModel<BookDto> toModel(BookDto book) {
        return EntityModel.of(
                book,
                linkTo(methodOn(BookController.class).getBookByIsbn(book.getIsbn(), null)).withSelfRel(),
                linkTo(methodOn(BookController.class).getBooks(null, null, null, null)).withRel("parent"),
                linkTo(methodOn(AuthorController.class).getBookAuthors(book.getIsbn())).withRel("authors")
        );
    }

    public EntityModel<BookPartialDto> toModel(BookPartialDto entity, Boolean verbose) throws ResourceNotFoundException {
        return EntityModel.of(
                entity,
                linkTo(methodOn(BookController.class).getBookByIsbn(entity.getIsbn(), verbose)).withSelfRel(),
                linkTo(methodOn(BookController.class).getBooks(null, null, null, null)).withRel("parent")
        );
    }
}
