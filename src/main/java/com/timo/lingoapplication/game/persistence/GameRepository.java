package com.timo.lingoapplication.game.persistence;

import com.timo.lingoapplication.game.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
