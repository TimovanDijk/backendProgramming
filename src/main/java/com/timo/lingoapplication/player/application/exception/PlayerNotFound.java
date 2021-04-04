package com.timo.lingoapplication.player.application.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PlayerNotFound extends RuntimeException {
    public PlayerNotFound(Long id) {
        super("The player with ID: " + id + " could not be found.");
    }
}
