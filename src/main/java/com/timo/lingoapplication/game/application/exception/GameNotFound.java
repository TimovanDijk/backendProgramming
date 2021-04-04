package com.timo.lingoapplication.game.application.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GameNotFound extends RuntimeException {
    public GameNotFound(Long id) {
        super("The game with ID: " + id + " could not be found.");
    }
}
