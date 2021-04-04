package com.timo.lingoapplication.game.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class GameConcluded extends RuntimeException {
    public GameConcluded() {
        super("Game has concluded.");
    }
}