package com.pos.ebook.Ebook.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

public class CustomDataIntegrityViolationExceptionHelper {
    private final DataIntegrityViolationException exception;

    public CustomDataIntegrityViolationExceptionHelper(DataIntegrityViolationException exception) {
        this.exception = exception;
    }

    public String getMessage() {
        if(isConstraintViolationException()) {
            if (isUniqueConstraintForBookTitle(castToConstraintViolationException(exception).getConstraintName())) {
                return "There is already a book with this title";
            }
            // ... other constraints
        }

        return exception.getMessage();
    }

    private boolean isConstraintViolationException() {
        return exception.getCause() instanceof ConstraintViolationException;
    }

    private ConstraintViolationException castToConstraintViolationException(DataIntegrityViolationException exception) {
        return ((ConstraintViolationException) exception.getCause());
    }

    private boolean isUniqueConstraintForBookTitle(String constraintName) {
        return constraintName.equals("book.title_UNIQUE");
    }
}
