package com.timo.lingoapplication.word.application;

import com.timo.lingoapplication.word.domain.Word;

public interface WordServiceInterface {
    public Word save(Word word);

    public Boolean doesWordExist(String wordGuess);

    Word getWordbyId(Long id);
}
