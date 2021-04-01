package com.timo.lingoapplication.game.application;

import com.timo.lingoapplication.game.application.exception.GameNotFound;
import com.timo.lingoapplication.game.domain.Game;
import com.timo.lingoapplication.game.domain.GameState;
import com.timo.lingoapplication.game.domain.GameType;
import com.timo.lingoapplication.game.persistence.GameRepository;
import com.timo.lingoapplication.word.application.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService implements GameServiceInterface {
    private GameRepository gameRepository;
    private WordService wordService;

    @Autowired
    public GameService(GameRepository gameRepository, WordService wordService) {
        this.gameRepository = gameRepository;
        this.wordService = wordService;
    }

    @Override
    public Game createGame(GameType gameType) {
        //Set rounds to 0, playerId and the enum states
        Game game = new Game(gameType, GameState.IN_PROGRESS);
        game.setWord(wordService.getRandomWord());
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
    public GameStateMessage attemptAnswer(Long gameId, String answerAttempt) {
        Game game = this.getById(gameId);
        int roundnumber = game.getRoundNumber();
        Long wordId = game.getWord().getId();
        if (game.getGameState() != GameState.FINISHED) {
            if (roundnumber < 5) {
                if (wordService.checkIfAnswerCorrect(answerAttempt, wordId)) {
                    game.setGameState(GameState.FINISHED);
                    game.setRoundNumber(game.getRoundNumber() + 1);
                    gameRepository.save(game);
                    Game newGame = createGame(cycleGameType(game.getGameType()));

                    return new GameStateMessage(true, newGame, "Your answer of: " + answerAttempt + " is correct!");
                } else {
                    game.setRoundNumber(game.getRoundNumber() + 1);
                    gameRepository.save(game);

                    return new GameStateMessage(false, game, "Your answer of: " + answerAttempt + " is not correct.");
                }
            } else {
                game.setGameState(GameState.FINISHED);
                gameRepository.save(game);
                return new GameStateMessage(false, game, "Maximum number of attempts for this game has been reached.");
            }
        } else {
            return new GameStateMessage(false, game, "Game has concluded.");
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
