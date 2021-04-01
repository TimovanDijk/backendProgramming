package com.timo.lingoapplication.game.application.exception;

public class GameNotFound extends RuntimeException {
    public GameNotFound(Long id) {
        super("The game with ID: " + id + " could not be found.");
    }
}
