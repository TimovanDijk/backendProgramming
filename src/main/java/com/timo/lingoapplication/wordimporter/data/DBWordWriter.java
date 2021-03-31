package com.timo.lingoapplication.wordimporter.data;

import com.timo.lingoapplication.word.application.WordService;
import com.timo.lingoapplication.word.domain.Word;
import com.timo.lingoapplication.wordimporter.domain.WordFilter;

import java.util.List;


public class DBWordWriter {

    private WordFilter wordFilter;
    private WordService wordService;

    public DBWordWriter(WordFilter wordFilter, WordService wordService) {
        this.wordFilter = wordFilter;
        this.wordService = wordService;
    }

    public boolean arrayFiltering(List<String> lines) {

        //FILTER LENGTH
        for (String line : lines) {
            if (wordFilter.verify(line)) {
                Word word = new Word();
                word.setWord(line);
                wordService.save(word);
            }
        }
        return true;
    }
}
