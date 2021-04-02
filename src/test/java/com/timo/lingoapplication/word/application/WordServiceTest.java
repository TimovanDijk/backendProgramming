package com.timo.lingoapplication.word.application;

import com.timo.lingoapplication.shared.message.LetterFeedbackMessage;
import com.timo.lingoapplication.shared.message.LetterState;
import com.timo.lingoapplication.word.application.exception.WordNotFound;
import com.timo.lingoapplication.word.domain.Word;
import com.timo.lingoapplication.word.persistence.WordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("WordServiceTest")
class WordServiceTest {

    private static final Word word = new Word(1L, "achter");

    @Test
    @DisplayName("CHECK IF WORD HAS BEEN SAVED")
    void save() {
        Word word = new Word(1L, "terug");
        WordRepository wordRepository = Mockito.mock(WordRepository.class);
        WordService wordService = new WordService(wordRepository);

        when(wordRepository.save(word)).thenReturn(word);
        Word result = wordService.save(word);
        assertEquals(word, result);

    }

    @Test
    @DisplayName("RETURNS TRUE IF WORD DOES NOT EXIST")
    void wordExists() {
        Word word = new Word(1L, "vKfEp");

        WordRepository wordRepository = Mockito.mock(WordRepository.class);
        WordService wordService = new WordService(wordRepository);

        when(wordRepository.existsByWord(word.toString())).thenReturn(true);

        Boolean result = wordService.doesWordExist(word.toString());

        assertTrue(result);
    }

    @Test
    @DisplayName("RETURNS WORD WHEN FOUND")
    void getWordById() {
        WordRepository wordRepository = Mockito.mock(WordRepository.class);

        when(wordRepository.findById(word.getId())).thenReturn(java.util.Optional.of(word));

        WordService wordService = new WordService(wordRepository);
        Word result = wordService.getWordbyId(word.getId());

        assertEquals(word, result);
    }

    @Test
    @DisplayName("CHECK FOR ERROR")
    void getWordByWrongId() {
        Word word = new Word(22l, "kaasje");
        WordRepository wordRepository = Mockito.mock(WordRepository.class);

        WordService wordService = new WordService(wordRepository);
        WordNotFound thrown = assertThrows(
                WordNotFound.class,
                () -> wordService.getWordbyId(word.getId()),
                "Expected getWordById(22l) to throw, but it didn't"
        );

        assertEquals("The word with ID: 22 could not be found.", thrown.getMessage());


    }

    @Test
    @DisplayName("CHECK FOR ERROR")
    void checkAnswers() {
        WordRepository wordRepository = Mockito.mock(WordRepository.class);

        WordService wordService = new WordService(wordRepository);
        when(wordRepository.findById(word.getId())).thenReturn(java.util.Optional.of(word));

        boolean correct = wordService.checkIfAnswerCorrect(word.getWord(), word.getId());
        boolean incorrect = wordService.checkIfAnswerCorrect("incorrecto" , word.getId());

        assertTrue(correct);
        assertTrue(!incorrect);
    }

    @Test
    @DisplayName("CHECK FOR FEEDBACK MESSAGE")
    void checkAnswersWithFeedback() {
        WordRepository wordRepository = Mockito.mock(WordRepository.class);

        WordService wordService = new WordService(wordRepository);
        when(wordRepository.findById(word.getId())).thenReturn(java.util.Optional.of(word));

        List<LetterFeedbackMessage> correct = wordService.checkLettersOfAnswer(word.getWord(), word.getId());
        List<LetterFeedbackMessage> incorrect = wordService.checkLettersOfAnswer("achtfa" , word.getId());
        List<LetterFeedbackMessage> present = wordService.checkLettersOfAnswer("chater" , word.getId());

        List<LetterFeedbackMessage> correctMock = new ArrayList<>();
        correctMock.add(new LetterFeedbackMessage(0, "a", LetterState.CORRECT));
        correctMock.add(new LetterFeedbackMessage(1, "c", LetterState.CORRECT));
        correctMock.add(new LetterFeedbackMessage(2, "h", LetterState.CORRECT));
        correctMock.add(new LetterFeedbackMessage(3, "t", LetterState.CORRECT));
        correctMock.add(new LetterFeedbackMessage(4, "e", LetterState.CORRECT));
        correctMock.add(new LetterFeedbackMessage(5, "r", LetterState.CORRECT));

        List<LetterFeedbackMessage> incorrectMock = new ArrayList<>();
        incorrectMock.add(new LetterFeedbackMessage(0, "a", LetterState.CORRECT));
        incorrectMock.add(new LetterFeedbackMessage(1, "c", LetterState.CORRECT));
        incorrectMock.add(new LetterFeedbackMessage(2, "h", LetterState.CORRECT));
        incorrectMock.add(new LetterFeedbackMessage(3, "t", LetterState.CORRECT));
        incorrectMock.add(new LetterFeedbackMessage(4, "f", LetterState.ABSENT));
        incorrectMock.add(new LetterFeedbackMessage(5, "a", LetterState.ABSENT));

        List<LetterFeedbackMessage> presentMock = new ArrayList<>();
        presentMock.add(new LetterFeedbackMessage(0, "c", LetterState.CONTAINS));
        presentMock.add(new LetterFeedbackMessage(1, "h", LetterState.CONTAINS));
        presentMock.add(new LetterFeedbackMessage(2, "a", LetterState.CONTAINS));
        presentMock.add(new LetterFeedbackMessage(3, "t", LetterState.CORRECT));
        presentMock.add(new LetterFeedbackMessage(4, "e", LetterState.CORRECT));
        presentMock.add(new LetterFeedbackMessage(5, "r", LetterState.CORRECT));

        assertEquals(correct.toString(), correctMock.toString());
        assertEquals(incorrect.toString(), incorrectMock.toString());
        assertEquals(present.toString(), presentMock.toString());
    }

}
