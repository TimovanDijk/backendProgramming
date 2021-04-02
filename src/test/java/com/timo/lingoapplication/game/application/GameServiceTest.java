package com.timo.lingoapplication.game.application;

import com.timo.lingoapplication.game.application.exception.GameNotFound;
import com.timo.lingoapplication.game.domain.Game;
import com.timo.lingoapplication.game.domain.GameState;
import com.timo.lingoapplication.game.domain.GameType;
import com.timo.lingoapplication.game.persistence.GameRepository;
import com.timo.lingoapplication.shared.message.GameStateMessage;
import com.timo.lingoapplication.shared.message.LetterFeedbackMessage;
import com.timo.lingoapplication.shared.message.LetterState;
import com.timo.lingoapplication.word.application.WordService;
import com.timo.lingoapplication.word.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DisplayName("WordServiceTest")
class GameServiceTest {
    private static final Word word = new Word(1L, "achter");
    Game game = new Game(1l, 0, word, GameType.SIX_LETTERS, GameState.IN_PROGRESS);

    @Test
    @DisplayName("CHECK IF GAME HAS BEEN SAVED")
    void save() {
        GameRepository gameRepository = Mockito.mock(GameRepository.class);
        WordService wordService = Mockito.mock(WordService.class);
        GameService gameService = new GameService(gameRepository, wordService);

        when(gameRepository.save(game)).thenReturn(game);
        Game savedGame = gameService.saveGame(game);
        assertEquals(game, savedGame);

    }

    @Test
    @DisplayName("RETURNS GAME WHEN FOUND")
    void getGameById() {
        GameRepository gameRepository = Mockito.mock(GameRepository.class);
        WordService wordService = Mockito.mock(WordService.class);

        when(gameRepository.findById(game.getId())).thenReturn(java.util.Optional.of(game));

        GameService gameService = new GameService(gameRepository, wordService);
        Game result = gameService.getById(game.getId());

        assertEquals(game, result);
    }

    @Test
    @DisplayName("CHECK FOR ERROR IN GAME")
    void getGameByWrongId() {
        Game game = new Game(22l, 0, word, GameType.SIX_LETTERS, GameState.IN_PROGRESS);
        GameRepository gameRepository = Mockito.mock(GameRepository.class);
        WordService wordService = Mockito.mock(WordService.class);

        GameService gameService = new GameService(gameRepository, wordService);
        GameNotFound thrown = assertThrows(
                GameNotFound.class,
                () -> gameService.getById(game.getId()),
                "Expected getById(22l) to throw, but it didn't"
        );

        assertEquals("The game with ID: 22 could not be found.", thrown.getMessage());


    }

    @Test
    @DisplayName("CHECKS ANSWERS")
    void attemptAnswers() {
        GameRepository gameRepository = Mockito.mock(GameRepository.class);
        WordService wordService = Mockito.mock(WordService.class);
        GameService gameService = new GameService(gameRepository, wordService);

        when(gameRepository.findById(game.getId())).thenReturn(java.util.Optional.of(game));
        when(wordService.checkIfAnswerCorrect(word.getWord(), word.getId())).thenReturn(true);

        GameStateMessage wrongAnswer = gameService.attemptAnswer(game.getId(), "achtes");
        GameStateMessage correctAnswer = gameService.attemptAnswer(game.getId(), "achter");
        GameStateMessage concludedAnswer = gameService.attemptAnswer(game.getId(), "achtef");

        Game filledGame = game;
        filledGame.setGameState(GameState.IN_PROGRESS);
        filledGame.setRoundNumber(5);
        filledGame.setId(5l);

        when(gameRepository.findById(filledGame.getId())).thenReturn(java.util.Optional.of(filledGame));

        GameStateMessage noAttemptsAnswer = gameService.attemptAnswer(game.getId(), "achtef");

        Game newGame = correctAnswer.getGame();

        List<LetterFeedbackMessage> letterFeedbackMessages = new ArrayList<>();

        GameStateMessage wrong = new GameStateMessage(false, game, "Your answer of: achtes is not correct.", letterFeedbackMessages);
        GameStateMessage correct = new GameStateMessage(true, newGame, "Your answer of: achter is correct!", letterFeedbackMessages);
        GameStateMessage concluded = new GameStateMessage(false, game, "Game has concluded.");
        GameStateMessage noAttempts = new GameStateMessage(false, game, "Maximum number of attempts for this game has been reached.");

        assertThat(wrongAnswer).isEqualToComparingFieldByFieldRecursively(wrong);
        assertThat(correctAnswer).isEqualToComparingFieldByFieldRecursively(correct);
        assertThat(concludedAnswer).isEqualToComparingFieldByFieldRecursively(concluded);
        assertThat(noAttemptsAnswer).isEqualToComparingFieldByFieldRecursively(noAttempts);
    }
}
