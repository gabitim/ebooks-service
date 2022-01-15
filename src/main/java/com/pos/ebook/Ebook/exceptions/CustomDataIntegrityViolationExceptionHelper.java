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
            if (isUniqueConstraintForBookTitlu(castToConstraintViolationException(exception).getConstraintName())) {
                return "There is already a book with this title";
            }
            // ... other constraints
        }

        return "";
    }

    private boolean isConstraintViolationException() {
        return exception.getCause() instanceof ConstraintViolationException;
    }

    private ConstraintViolationException castToConstraintViolationException(DataIntegrityViolationException exception) {
        return ((ConstraintViolationException) exception.getCause());
    }

    private boolean isUniqueConstraintForBookTitlu(String constraintName) {
        return constraintName.equals("books.Titlu_UNIQUE");
    }
}
