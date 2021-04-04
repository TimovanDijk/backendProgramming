package com.timo.lingoapplication.game.presentation;

import com.timo.lingoapplication.game.application.GameServiceInterface;
import com.timo.lingoapplication.game.domain.Game;
import com.timo.lingoapplication.player.application.exception.PlayerNotFound;
import com.timo.lingoapplication.shared.message.GameStateMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/game")
public class GameController {
    private GameServiceInterface gameServiceInterface;

    public GameController(GameServiceInterface gameServiceInterface) {
        this.gameServiceInterface = gameServiceInterface;
    }

    @GetMapping("/start/{playerId}")
    public Game startGame(@PathVariable Long playerId) throws PlayerNotFound {
        try {
            return gameServiceInterface.createFirstGame(playerId);
        } catch (PlayerNotFound e) {
            System.out.println(e.getMessage());
            throw new PlayerNotFound(playerId);
        }
    }

    @PostMapping("{gameid}/answer")
    public GameStateMessage attemptAnswer(@PathVariable Long gameid, @RequestBody String answer) {
        return gameServiceInterface.attemptAnswer(gameid, answer);
    }

    @GetMapping("/calculate")
    public double calculateScores(@RequestBody List<Game> games) {
        return gameServiceInterface.calculateScore(games);
    }
}
