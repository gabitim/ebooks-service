package com.pos.ebook.Ebook.exceptions.generic;

/**
 * @author Timofti Gabriel
 */
public class NotFoundException extends RuntimeException{

    public NotFoundException(String resource, String id) {
        super("Could not find " + resource + " with id: " + id);
    }
}
