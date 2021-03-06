package com.timo.lingoapplication.wordimporter.application;



import com.timo.lingoapplication.wordimporter.data.WordWriter;
import com.timo.lingoapplication.wordimporter.domain.WordFilter;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class WordImporter {
    private final WordReader reader;
    private final WordFilter filter;
    private final WordWriter writer;

    public WordImporter(WordReader reader, WordFilter filter, WordWriter writer) {
        this.reader = reader;
        this.filter = filter;
        this.writer = writer;
    }

    public void importWords() {
        List<String> filteredWords = reader
                .readWords()
                .filter(filter::verify)
                .collect(toList());

        writer.writeWords(filteredWords);
    }
}
