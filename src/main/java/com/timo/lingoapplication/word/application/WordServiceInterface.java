package com.timo.lingoapplication.word.application;

import com.timo.lingoapplication.word.domain.Word;

public interface WordServiceInterface {
    Word save(Word word);

    boolean doesWordExist(String wordGuess);

    Word getWordbyId(Long id);

    Word getRandomWord();

    boolean checkIfAnswerCorrect(String answer, Long wordId);
}
