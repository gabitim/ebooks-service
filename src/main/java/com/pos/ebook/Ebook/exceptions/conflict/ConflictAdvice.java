package com.pos.ebook.Ebook.exceptions.conflict;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Timofti Gabriel
 */

@ControllerAdvice
public class ConflictAdvice {

    @ResponseBody
    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String conflictHandler(ConflictException ex) {
        return ex.getMessage();
    }
}
