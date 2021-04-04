package com.timo.lingoapplication.session.application;

import com.timo.lingoapplication.game.application.GameService;
import com.timo.lingoapplication.game.domain.Game;
import com.timo.lingoapplication.player.application.PlayerService;
import com.timo.lingoapplication.player.application.exception.PlayerNotFound;
import com.timo.lingoapplication.player.domain.Player;
import com.timo.lingoapplication.player.persistence.PlayerRepository;
import com.timo.lingoapplication.session.domain.Session;
import com.timo.lingoapplication.session.persistence.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService implements SessionServiceInterface{
    private PlayerService playerService;
    private SessionRepository sessionRepository;

    @Autowired
    public SessionService(PlayerService playerService, SessionRepository sessionRepository) {
        this.playerService = playerService;
        this.sessionRepository = sessionRepository;
    }

    public Session createSession(Game game, Long playerId) {
        Player player = playerService.getPlayerById(playerId);
        List<Game> games = new ArrayList<>();
        games.add(game);
        Session newSession = new Session(player, games, 0);

        sessionRepository.save(newSession);

        return newSession;
    }

    public Session addGameToSession(Game game, Session session) {
        List<Game> games = session.getGames();
        games.add(game);
        session.setGames(games);

        sessionRepository.save(session);

        return session;
    }

    public Session getSessionByGame(Game game) {
        return sessionRepository.getSessionByGamesIsContaining(game);
    }


}
