package com.timo.lingoapplication.session.persistence;

import com.timo.lingoapplication.game.domain.Game;
import com.timo.lingoapplication.session.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Session getSessionByGamesIsContaining(Game game);
}
