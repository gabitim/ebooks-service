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

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author replaceAuthor(Author newAuthor, Long id) {
        return authorRepository.findById(id)
                .map(author -> {
                    author.setFirst_name(newAuthor.getFirst_name());
                    author.setLast_name(newAuthor.getLast_name());

                    return authorRepository.save(author);
                })
                .orElseGet(() -> addAuthor(newAuthor));
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
