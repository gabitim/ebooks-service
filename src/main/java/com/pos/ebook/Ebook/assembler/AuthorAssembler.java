package com.pos.ebook.Ebook.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.pos.ebook.Ebook.api.AuthorController;
import com.pos.ebook.Ebook.exceptions.ResourceNotFoundException;
import com.pos.ebook.Ebook.model.dtos.AuthorDto;
import lombok.SneakyThrows;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Timofti Gabriel
 */

@Component
public class AuthorAssembler implements RepresentationModelAssembler<AuthorDto, EntityModel<AuthorDto>> {

    @SneakyThrows
    @Override
    public EntityModel<AuthorDto> toModel(AuthorDto author) {
        return EntityModel.of(
                author,
                linkTo(methodOn(AuthorController.class).getAuthorById(author.getId())).withSelfRel(),
                linkTo(methodOn(AuthorController.class).getAuthors(null, null)).withRel("parent")
        );
    }

    public CollectionModel<AuthorDto> toCollectionModel(List<AuthorDto> authors, String isbn) throws ResourceNotFoundException {

        authors.forEach(author -> {
            try {
                author.add(linkTo(methodOn(AuthorController.class).getAuthorById(author.getId())).withSelfRel());
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        });

        return CollectionModel.of(
                authors,
                linkTo(methodOn(AuthorController.class).getBookAuthors(isbn)).withSelfRel()
        );
    }
}
