package com.timo.lingoApplication.word.application;

import com.timo.lingoApplication.word.domain.Word;
import com.timo.lingoApplication.word.persistence.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordService implements WordServiceInterface{
    private WordRepository wordRepository;
    @Autowired
    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public Word save(Word word) {
        return wordRepository.save(word);
    }
}
