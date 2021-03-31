package com.timo.lingoapplication.wordimporter.application;

import java.util.stream.Stream;

public interface WordReader {
    Stream<String> readWords();
}
