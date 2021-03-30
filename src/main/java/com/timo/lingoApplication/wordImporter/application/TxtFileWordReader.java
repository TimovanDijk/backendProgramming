package com.timo.lingoApplication.wordImporter.application;

import com.timo.lingoApplication.shared.exception.CannotReadWords;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Slf4j
public class TxtFileWordReader implements WordReader {
    private final Path source;

    public TxtFileWordReader(Path source) {
        this.source = source;
    }

    @Override
    public Stream<String> readWords() {
        try {
            log.info("Reading words from file {}", this.source);
            return Files.lines(this.source);
        } catch (IOException e) {
            throw CannotReadWords.because(e);
        }
    }
}
