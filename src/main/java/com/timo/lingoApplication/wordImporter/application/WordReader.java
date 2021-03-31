package com.timo.lingoApplication.wordImporter.application;

import java.util.stream.Stream;

public interface WordReader {
    Stream<String> readWords();
}
