package com.timo.lingoapplication.word.application.exception;

public class WordNotFound extends RuntimeException {
    public WordNotFound(Long id) {
        super("The word with ID: " + id + " could not be found.");
    }
}