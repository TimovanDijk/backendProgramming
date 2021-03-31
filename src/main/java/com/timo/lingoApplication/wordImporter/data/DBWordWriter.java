package com.timo.lingoApplication.wordImporter.data;

import com.timo.lingoApplication.word.application.WordService;
import com.timo.lingoApplication.word.domain.Word;
import com.timo.lingoApplication.wordImporter.domain.WordFilter;

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
