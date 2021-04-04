package com.timo.lingoapplication.game.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidWord extends RuntimeException {
    public InvalidWord() {
        super("An invalid word was entered, please use non-capital letters only and use the correct word length");
    }
}