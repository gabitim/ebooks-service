package com.pos.ebook.Ebook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Timofti Gabriel
 */

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class ResourceNotAcceptableException extends Exception {

    public ResourceNotAcceptableException(String message){
        super(message);
    }
}
