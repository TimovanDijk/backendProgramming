package com.timo.lingoApplication.word.application;

import com.timo.lingoApplication.word.domain.Word;

public interface WordServiceInterface {
    public Word save(Word word);

    public Boolean doesWordExist(String wordGuess);

    Word getWordbyId(Long id);
}
