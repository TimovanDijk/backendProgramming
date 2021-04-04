package com.timo.lingoapplication.session.application;

import com.timo.lingoapplication.game.domain.Game;
import com.timo.lingoapplication.session.domain.Session;

public interface SessionServiceInterface {
    Session createSession(Game game, Long playerId);

    Session addGameToSession(Game game, Session session);

    Session getSessionByGame(Game game);
}
