package com.timo.lingoApplication.wordImporter.application;


import com.timo.lingoApplication.wordImporter.ports.WordReader;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WordService {
    private final Random random;
    private final WordReader reader;

    public WordService(Random random, WordReader reader) {
        this.random = random;
        this.reader = reader;
    }

    public String getRandomWord(int length) {
        if (length < 5 || length > 7) {
            throw new RuntimeException("Can only give random words of size 5, 6, 7");
        }

        List<String> words = this.reader
                .readWords()
                .filter((word) -> word.length() == length)
                .collect(Collectors.toList());

        return words.get(random.nextInt(words.size()));
    }
}
