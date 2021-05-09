package ru.itis.gilyazov.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CardNotFoundException extends Exception {

    public CardNotFoundException(String msg) {
        super(msg);
    }
}
