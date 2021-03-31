package com.timo.lingoapplication.wordImporter.application;

import java.util.stream.Stream;

public interface WordReader {
    Stream<String> readWords();
}
