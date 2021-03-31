package com.timo.lingoApplication.wordImporter.application;

import com.timo.lingoApplication.wordImporter.data.WordWriter;
import com.timo.lingoApplication.wordImporter.domain.WordFilter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class WordImporterTest {
    @Test
    void imports_words_from_a_reader_to_a_writer_using_a_filter() {
        List<String> wordList = List.of(
                "pizza",
                "pizzas",
                "bier"
        );

        // Reader (mock) --> Filter (mock) --> Writer (spy)
        WordReader mockReader = mock(WordReader.class);
        when(mockReader.readWords())
                .thenReturn(wordList.stream());

        WordFilter mockFilter = mock(WordFilter.class);
        when(mockFilter.verify(anyString()))
                .thenReturn(true);

        WordWriter spyWriter = spy(WordWriter.class);

        WordImporter importer = new WordImporter(mockReader, mockFilter, spyWriter);

        importer.importWords();

        // Writer should only be invoked once when import words is run
        verify(spyWriter, times(1))
                .writeWords(wordList);
    }
}
