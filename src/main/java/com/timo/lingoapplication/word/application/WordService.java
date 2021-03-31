package com.timo.lingoapplication.word.application;

import com.timo.lingoapplication.word.application.exception.WordNotFound;
import com.timo.lingoapplication.word.domain.Word;
import com.timo.lingoapplication.word.persistence.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public Boolean doesWordExist(String wordGuess) {
        return wordRepository.existsByWord(wordGuess);
    }

    @Override
    public Word getWordbyId(Long id) throws WordNotFound {
        Optional<Word> optionalWord = wordRepository.findById(id);
        if (!optionalWord.isPresent()) {
            throw new WordNotFound(id);
        }
        return optionalWord.get();
    }
}
