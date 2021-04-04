package com.timo.lingoapplication.game.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OutOfAttempts extends RuntimeException {
    public OutOfAttempts() {
        super("You have no more attempts left.");
    }
}
