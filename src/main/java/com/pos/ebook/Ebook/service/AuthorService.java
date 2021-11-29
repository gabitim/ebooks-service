package com.pos.ebook.Ebook.service;

import com.pos.ebook.Ebook.exceptions.generic.NotFoundException;
import com.pos.ebook.Ebook.model.Author;
import com.pos.ebook.Ebook.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Timofti Gabriel
 */

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private static final String AUTHOR = "Author";

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(AUTHOR, id));
    }

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author replaceAuthor(Author author, Long id) {
        return authorRepository.findById(id)
                .map(oldAuthor -> {
                    oldAuthor.setNume(author.getNume());
                    oldAuthor.setPrenume(author.getPrenume());

                    return authorRepository.save(oldAuthor);
                })
                .orElseGet(() -> {
                    author.setID(id);
                    return authorRepository.save(author);
                });
    }

    public void deleteAuthor(Long id) {
        try {
            authorRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException exception) {
            // we would log this
            throw new NotFoundException(AUTHOR, id);
        }
    }
}
