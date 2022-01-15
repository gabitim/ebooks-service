package com.pos.ebook.Ebook.service;

import com.pos.ebook.Ebook.model.Author;
import com.pos.ebook.Ebook.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

//    public Author getAuthorById(Long id) {
//        return authorRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException(AUTHOR, id));
//    }

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    public ResponseEntity<Author> replaceAuthor(Author author, Long id) {
        return authorRepository.findById(id)
                .map(oldAuthor -> {
                    oldAuthor.setNume(author.getNume());
                    oldAuthor.setPrenume(author.getPrenume());

                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(authorRepository.save(oldAuthor));
                })
                .orElseGet(() -> {
                    author.setID(id);
                    return ResponseEntity.status(HttpStatus.CREATED).body(authorRepository.save(author));
                });
    }

//    public void deleteAuthor(Long id) {
//        try {
//            authorRepository.deleteById(id);
//        }
//        catch (EmptyResultDataAccessException exception) {
//            // we would log this
//            throw new NotFoundException(AUTHOR, id);
//        }
//    }
}
