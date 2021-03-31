package com.timo.lingoapplication.wordImporter.application;

import com.timo.lingoapplication.wordImporter.application.exception.CannotReadWords;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Slf4j
public class TxtFileWordReader implements WordReader {
    @Value("${wordSource}")
    private final Path source;

    public TxtFileWordReader(Path source) {
        this.source = source;
    }

    @Override
    public Stream<String> readWords() {
        try {
            System.out.println("Reading words from file " + this.source);
            return Files.lines(this.source);
        } catch (IOException e) {
            throw CannotReadWords.because(e);
        }
    }
}
