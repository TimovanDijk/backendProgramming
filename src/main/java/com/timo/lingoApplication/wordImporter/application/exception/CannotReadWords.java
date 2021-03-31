package com.timo.lingoApplication.wordImporter.application.exception;

public class CannotReadWords extends RuntimeException {
    private CannotReadWords(Throwable cause) {
        super(cause);
    }

    public static CannotReadWords because(Throwable cause) {
        return new CannotReadWords(cause);
    }
}
