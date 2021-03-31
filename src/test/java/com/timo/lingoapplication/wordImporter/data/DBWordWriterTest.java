package com.timo.lingoapplication.wordImporter.data;

import com.timo.lingoapplication.word.application.WordService;
import com.timo.lingoapplication.wordImporter.domain.WordFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class DBWordWriterTest {

    @Test
    @DisplayName("RETURNS TRUE WHEN LINES ARE FILTERED")
    void arrayFiltering() {
        List<String> list = List.of("tests", "baard");

        WordFilter wordFilter = mock(WordFilter.class);
        WordService wordService = mock(WordService.class);

        DBWordWriter fileWordWriter = new DBWordWriter(wordFilter, wordService);

        assertTrue(fileWordWriter.arrayFiltering(list));
    }
}
