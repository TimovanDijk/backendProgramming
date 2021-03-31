package com.timo.lingoapplication.wordimporter.application.exception;

public class CannotReadWords extends RuntimeException {
    private CannotReadWords(Throwable cause) {
        super(cause);
    }

    public static CannotReadWords because(Throwable cause) {
        return new CannotReadWords(cause);
    }
}
