package com.timo.lingoapplication.player.persistence;

import com.timo.lingoapplication.player.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
