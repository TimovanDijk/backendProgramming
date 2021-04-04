package com.timo.lingoapplication.player.application;

import com.timo.lingoapplication.player.application.exception.PlayerNotFound;
import com.timo.lingoapplication.player.domain.Player;
import com.timo.lingoapplication.player.persistence.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {
    private PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getPlayerById(Long playerId) throws PlayerNotFound {
        Optional<Player> optionalPlayer = playerRepository.findById(playerId);
        if (!optionalPlayer.isPresent()) {
            throw new PlayerNotFound(playerId);
        }
        return optionalPlayer.get();
    }
}
