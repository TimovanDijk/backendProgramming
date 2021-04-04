package com.timo.lingoapplication.player.application;

import com.timo.lingoapplication.game.application.GameService;
import com.timo.lingoapplication.game.application.exception.GameNotFound;
import com.timo.lingoapplication.game.domain.Game;
import com.timo.lingoapplication.game.domain.GameState;
import com.timo.lingoapplication.game.domain.GameType;
import com.timo.lingoapplication.game.persistence.GameRepository;
import com.timo.lingoapplication.player.application.exception.PlayerNotFound;
import com.timo.lingoapplication.player.domain.Player;
import com.timo.lingoapplication.player.persistence.PlayerRepository;
import com.timo.lingoapplication.session.application.SessionService;
import com.timo.lingoapplication.word.application.WordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DisplayName("PlayerServiceTest")
public class PlayerServiceTest {
    private static final Player player = new Player(1l, "name", "password", "email");
    @Test
    @DisplayName("CHECK IF CORRECT PLAYER IS RETURNED")
    void getPlayerById() {
        PlayerRepository playerRepository = Mockito.mock(PlayerRepository.class);
        PlayerService playerService = new PlayerService(playerRepository);

        when(playerRepository.findById(player.getId())).thenReturn(java.util.Optional.of(player));

        Player playerResult = playerService.getPlayerById(player.getId());
        assertEquals(player, playerResult);
    }

    @Test
    @DisplayName("CHECK FOR ERROR IN PlAYER")
    void getPlayerByWrongId() {
        PlayerRepository playerRepository = Mockito.mock(PlayerRepository.class);
        PlayerService playerService = new PlayerService(playerRepository);
        PlayerNotFound thrown = assertThrows(
                PlayerNotFound.class,
                () -> playerService.getPlayerById(22l),
                "Expected getByPlayerById(22l) to throw, but it didn't"
        );

        assertEquals("The player with ID: 22 could not be found.", thrown.getMessage());

    }
}
