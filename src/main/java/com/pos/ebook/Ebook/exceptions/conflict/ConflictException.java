package com.pos.ebook.Ebook.exceptions.conflict;

/**
 * @author Timofti Gabriel
 */
public class ConflictException extends RuntimeException{

    public <T> ConflictException(String resource, T id) {
        super("Resource " + resource + " with id: " + id + " already exists");
    }
}
