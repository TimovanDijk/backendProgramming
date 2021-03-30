package com.timo.lingoApplication.word.persistence;

import com.timo.lingoApplication.word.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WordRepository extends JpaRepository<Word, Long> {
    @Query(
            value = "SELECT * FROM WORD ORDER BY RANDOM() LIMIT 1", nativeQuery = true
    )
    Word getRandomWord();

    Boolean wordExists(String word);
}
