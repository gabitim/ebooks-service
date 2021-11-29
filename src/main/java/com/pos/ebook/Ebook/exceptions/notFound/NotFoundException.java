package com.pos.ebook.Ebook.exceptions.notFound;

/**
 * @author Timofti Gabriel
 */
public class NotFoundException extends RuntimeException{

    public <T> NotFoundException(String resource, T id) {
        super("Could not find " + resource + " with id: " + id.toString());
    }
}
