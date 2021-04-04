package com.timo.lingoapplication.session.application;


import com.timo.lingoapplication.game.application.GameService;
import com.timo.lingoapplication.game.domain.Game;
import com.timo.lingoapplication.game.domain.GameState;
import com.timo.lingoapplication.game.domain.GameType;
import com.timo.lingoapplication.game.persistence.GameRepository;
import com.timo.lingoapplication.player.application.PlayerService;
import com.timo.lingoapplication.player.domain.Player;
import com.timo.lingoapplication.player.persistence.PlayerRepository;
import com.timo.lingoapplication.session.domain.Session;
import com.timo.lingoapplication.session.persistence.SessionRepository;
import com.timo.lingoapplication.word.application.WordService;
import com.timo.lingoapplication.word.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("SessionServiceTest")
class SessionServiceTest {
    private static final Word word = new Word(1L, "achter");
    private static final Game game = new Game(1l, 0, word, GameType.SIX_LETTERS, GameState.IN_PROGRESS);
    private static final Player player = new Player(1l, "name", "password", "email");
    @Test
    @DisplayName("CHECK IF SESSION HAS BEEN CREATED")
    void createSession() {
        PlayerService playerService = Mockito.mock(PlayerService.class);
        SessionRepository sessionRepository = Mockito.mock(SessionRepository.class);

        when(playerService.getPlayerById(1l)).thenReturn(player);
        SessionService sessionService = new SessionService(playerService, sessionRepository);

        Session realSession = sessionService.createSession(game, 1l);

        List<Game> mockGames = new ArrayList<>();
        mockGames.add(game);

        assertThat(realSession).isEqualToComparingFieldByFieldRecursively(new Session(player, mockGames, 0));
    }

    @Test
    @DisplayName("CHECK IF GAME HAS BEEN ADDED TO SESSION")
    void addGameToSession() {
        PlayerService playerService = Mockito.mock(PlayerService.class);
        SessionRepository sessionRepository = Mockito.mock(SessionRepository.class);

        Game newGame = new Game(2l, 0, word, GameType.SIX_LETTERS, GameState.IN_PROGRESS);

        when(playerService.getPlayerById(1l)).thenReturn(player);
        SessionService sessionService = new SessionService(playerService, sessionRepository);


        List<Game> mockGames = new ArrayList<>();
        mockGames.add(game);
        Session oldSession = new Session(player, mockGames, 0);

        Session realSession = sessionService.addGameToSession(newGame, oldSession);
        mockGames.add(newGame);
        Session mockSession = new Session(player, mockGames, 0);

        assertThat(realSession).isEqualToComparingFieldByFieldRecursively(mockSession);
    }

    @Test
    @DisplayName("GET SESSION BY GAME")
    void getSessionByGame() {
        PlayerService playerService = Mockito.mock(PlayerService.class);
        SessionRepository sessionRepository = Mockito.mock(SessionRepository.class);

        SessionService sessionService = new SessionService(playerService, sessionRepository);

        List<Game> mockGames = new ArrayList<>();
        mockGames.add(game);

        Session mockSession = new Session(player, mockGames, 0);

        when(sessionRepository.getSessionByGamesIsContaining(game)).thenReturn(mockSession);

        Session realSession = sessionService.getSessionByGame(game);

        assertThat(realSession).isEqualToComparingFieldByFieldRecursively(mockSession);
    }


}
