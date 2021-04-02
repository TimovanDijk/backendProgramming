package com.timo.lingoapplication.word.application;

import com.timo.lingoapplication.shared.message.LetterFeedbackMessage;
import com.timo.lingoapplication.word.domain.Word;

import java.util.List;

public interface WordServiceInterface {
    Word save(Word word);

    boolean doesWordExist(String wordGuess);

    Word getWordbyId(Long id);

    Word getRandomWord();

    boolean checkIfAnswerCorrect(String answer, Long wordId);

    List<LetterFeedbackMessage> checkLettersOfAnswer(String answer, Long wordId);
}
