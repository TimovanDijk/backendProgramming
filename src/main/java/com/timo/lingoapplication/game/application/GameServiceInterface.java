package com.timo.lingoapplication.game.application;

import com.timo.lingoapplication.game.application.exception.GameNotFound;
import com.timo.lingoapplication.game.domain.Game;
import com.timo.lingoapplication.game.domain.GameType;

public interface GameServiceInterface {
    Game createGame(GameType gameType);

    Game getById(Long gameId) throws GameNotFound;

    Game saveGame(Game game);

    GameStateMessage attemptAnswer(Long gameId, String answerAttempt);
}
