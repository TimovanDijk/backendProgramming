package com.timo.lingoapplication.game.application;

import com.timo.lingoapplication.game.application.exception.GameConcluded;
import com.timo.lingoapplication.game.application.exception.GameNotFound;
import com.timo.lingoapplication.game.application.exception.InvalidWord;
import com.timo.lingoapplication.game.application.exception.OutOfAttempts;
import com.timo.lingoapplication.game.domain.Game;
import com.timo.lingoapplication.game.domain.GameState;
import com.timo.lingoapplication.game.domain.GameType;
import com.timo.lingoapplication.game.persistence.GameRepository;
import com.timo.lingoapplication.player.application.exception.PlayerNotFound;
import com.timo.lingoapplication.session.application.SessionService;
import com.timo.lingoapplication.shared.message.GameStateMessage;
import com.timo.lingoapplication.shared.message.LetterFeedbackMessage;
import com.timo.lingoapplication.word.application.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService implements GameServiceInterface {
    private GameRepository gameRepository;
    private WordService wordService;
    private SessionService sessionService;

    @Autowired
    public GameService(GameRepository gameRepository, WordService wordService, SessionService sessionService) {
        this.gameRepository = gameRepository;
        this.wordService = wordService;
        this.sessionService = sessionService;
    }

    @Override
    public Game createGame(GameType gameType) {
        //Set rounds to 0, playerId and the enum states
        Game game = new Game(gameType, GameState.IN_PROGRESS);
        int length = 5;
        length = getLength(gameType);
        game.setWord(wordService.getRandomWord(length));
        gameRepository.save(game);

        return game;
    }

    @Override
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game getById(Long gameId) throws GameNotFound {
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        if (!optionalGame.isPresent()) {
            throw new GameNotFound(gameId);
        }
        return optionalGame.get();
    }

    @Override
    public GameStateMessage attemptAnswer(Long gameId, String answerAttempt) throws InvalidWord {
        Game game = this.getById(gameId);
        int roundnumber = game.getRoundNumber();
        Long wordId = game.getWord().getId();
        int length = getLength(game.getGameType());
        if (!answerAttempt.matches("^[a-z]{" + length + "}$")) {
            throw new InvalidWord();
        }

        //Checking game states
        if (game.getGameState() != GameState.FINISHED) {
            if (roundnumber < 5) {
                if (wordService.checkIfAnswerCorrect(answerAttempt, wordId)) {
                    List<LetterFeedbackMessage> letterFeedback = wordService.checkLettersOfAnswer(answerAttempt, wordId);
                    game.setGameState(GameState.FINISHED);
                    game.setRoundNumber(game.getRoundNumber() + 1);
                    gameRepository.save(game);
                    Game newGame = createGame(cycleGameType(game.getGameType()));
                    sessionService.addGameToSession(newGame, sessionService.getSessionByGame(game));

                    return new GameStateMessage(true, newGame, "Your answer of: " + answerAttempt + " is correct!", letterFeedback);
                } else {
                    List<LetterFeedbackMessage> letterFeedback = wordService.checkLettersOfAnswer(answerAttempt, wordId);
                    System.out.println(letterFeedback);
                    game.setRoundNumber(game.getRoundNumber() + 1);
                    gameRepository.save(game);

                    return new GameStateMessage(false, game, "Your answer of: " + answerAttempt + " is not correct.", letterFeedback);
                }
            } else {
                game.setGameState(GameState.FINISHED);
                gameRepository.save(game);
                throw new OutOfAttempts();
            }
        } else {
            throw new GameConcluded();
        }
    }

    @Override
    public double calculateScore(List<Game> games) {
        double score = 0;
        for (Game game : games) {
            score = score +  ((6 - game.getRoundNumber()) * getLength(game.getGameType()));
        }

        return score;
    }

    @Override
    public Game createFirstGame(Long playerId) {
        Game game = createGame(GameType.FIVE_LETTERS);
        sessionService.createSession(game, playerId);
        return game;
    }

    private int getLength(GameType gameType) {
        switch (gameType) {
            case SIX_LETTERS:
                return 6;
            case SEVEN_LETTERS:
                return 7;
            default:
                return 5;
        }
    }

    private GameType cycleGameType(GameType gameType) {
        switch (gameType) {
            case FIVE_LETTERS:
                return GameType.SIX_LETTERS;
            case SIX_LETTERS:
                return GameType.SEVEN_LETTERS;
            default:
                return GameType.FIVE_LETTERS;
        }
    }
}
