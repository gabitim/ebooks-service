package com.pos.ebook.Ebook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Timofti Gabriel
 */

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceConflictException extends Exception {

    public ResourceConflictException(String message) {
        super(message);
    }
}

