package com.pos.ebook.Ebook.service;

import com.pos.ebook.Ebook.model.Author;
import com.pos.ebook.Ebook.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Timofti Gabriel
 */

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public boolean existsAuthor(Author author) {
        return getAuthor(author).isPresent();
    }

    public boolean existsAuthorById(Long id) {
        return authorRepository.existsById(id);
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public Optional<Author> getAuthor(Author author) {
        return authorRepository.findAuthorByFirstNameAndLastName(author.getFirstName(), author.getLastName());
    }

    public Optional<Author> findAuthorByExactName(String name) {
        String[] splitName = name.split(" ", 2);

        if (splitName.length < 2) {
            return Optional.empty();
        }
        else {
            return authorRepository.findAuthorByFirstNameAndLastName(splitName[0], splitName[1]);
        }
    }

    public List<Author> findAuthorsByPartialName(String name) {
        return authorRepository.findAuthorByFirstNameContainingOrLastNameContaining(name, name);
    }

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author replaceAuthor(Author newAuthor, Long id) {
        return authorRepository.findById(id)
                .map(author -> {
                    author.setFirstName(newAuthor.getFirstName());
                    author.setLastName(newAuthor.getLastName());

                    return authorRepository.save(author);
                })
                .orElseGet(() -> addAuthor(newAuthor));
    }
}
