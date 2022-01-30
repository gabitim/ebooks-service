package com.pos.ebook.Ebook.model.dtos;

import com.pos.ebook.Ebook.model.Author;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Timofti Gabriel
 */

@Data
@Accessors(chain = true)
public class AuthorDto {

    private Long id;

    @NotBlank(message = "first_name field is required")
    @Size(min = 1, max = 50, message = "first_name field size must be between 1 and 50")
    private String firstName;

    @NotBlank(message = "last_name field is required")
    @Size(min = 1, max = 50, message = "last_name field size must be between 1 and 50")
    private String lastName;

    public Author toAuthor() {
        return new Author(id, firstName, lastName);
    }

    public static AuthorDto from(Author author) {
        return new AuthorDto()
                .setFirstName(author.getFirstName())
                .setLastName(author.getLastName())
                .setId(author.getId());
    }
}
