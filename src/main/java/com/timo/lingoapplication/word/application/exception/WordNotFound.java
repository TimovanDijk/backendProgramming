package com.timo.lingoapplication.word.application.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class WordNotFound extends RuntimeException {
    public WordNotFound(Long id) {
        super("The word with ID: " + id + " could not be found.");
    }
}
