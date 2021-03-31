package com.timo.lingoApplication.word.application;

import com.timo.lingoApplication.shared.exception.WordNotFound;
import com.timo.lingoApplication.word.domain.Word;
import com.timo.lingoApplication.word.persistence.WordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("WordServiceTest")
public class WordServiceTest {

    private static final Word word = new Word(1L, "achter");

    @Test
    @DisplayName("CHECK IF WORD HAS BEEN SAVED")
    public void save() {
        Word word = new Word(1L, "terug");
        WordRepository wordRepository = Mockito.mock(WordRepository.class);
        WordService wordService = new WordService(wordRepository);

        when(wordRepository.save(word)).thenReturn(word);
        Word result = wordService.save(word);
        assertEquals(word, result);

    }

    @Test
    @DisplayName("RETURNS TRUE IF WORD DOES NOT EXIST")
    public void wordExists() {
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

        assertEquals(thrown.getMessage(), "The word with ID: 22 could not be found.");


    }
}
