package com.timo.lingoapplication.word.application;

import com.timo.lingoapplication.shared.message.LetterFeedbackMessage;
import com.timo.lingoapplication.shared.message.LetterState;
import com.timo.lingoapplication.word.application.exception.WordNotFound;
import com.timo.lingoapplication.word.domain.Word;
import com.timo.lingoapplication.word.persistence.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class WordService implements WordServiceInterface {
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
    public boolean doesWordExist(String wordGuess) {
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

    @Override
    public Word getRandomWord(int length) {
        Word word = null;
        while(true) {
            word = wordRepository.getRandomWord();
            if (word.getWord().length() == length) {
                break;
            }
        }

        return word;
    }

    @Override
    public boolean checkIfAnswerCorrect(String answer, Long wordId) {
        Word word = getWordbyId(wordId);
        return word.getWord().equals(answer);
    }

    @Override
    public List<LetterFeedbackMessage> checkLettersOfAnswer(String answer, Long wordId) {
        String word = getWordbyId(wordId).getWord();
        ArrayList<LetterFeedbackMessage> letterFeedbackMessages = new ArrayList<>();
        //Inspiration for this arraylist pulled from another solution made with the help of Alex Rothuis (gotta give credit)
        ArrayList<String> availableCharacters = new ArrayList<>();
        Collections.addAll(availableCharacters, word.split(""));

        for (int i = 0; i < word.length(); i++) {
            String guessChar = Character.toString(answer.charAt(i));
            String wordChar = Character.toString(word.charAt(i));

            if (guessChar.equals(wordChar)) {
                availableCharacters.set(i, null);
                letterFeedbackMessages.add(new LetterFeedbackMessage(i, guessChar, LetterState.CORRECT));
                continue;
            }
            if (availableCharacters.contains(guessChar)) {
                availableCharacters.set(availableCharacters.indexOf(guessChar), null);
                letterFeedbackMessages.add(new LetterFeedbackMessage(i, guessChar, LetterState.CONTAINS));
                continue;
            }
            letterFeedbackMessages.add(new LetterFeedbackMessage(i, guessChar, LetterState.ABSENT));

        }
        return letterFeedbackMessages;
    }
}
