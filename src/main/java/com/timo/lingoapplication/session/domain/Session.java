package com.timo.lingoapplication.session.domain;

import com.timo.lingoapplication.game.domain.Game;
import com.timo.lingoapplication.player.domain.Player;

import javax.persistence.*;
import java.util.List;

@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Player player;

    @OneToMany
    private List<Game> games;

    private double sessionScore;

    public Session() {
    }

    public Session(Long id, Player player, List<Game> games, double sessionScore) {
        this.id = id;
        this.player = player;
        this.games = games;
        this.sessionScore = sessionScore;
    }

    public Session(Player player, List<Game> games, double sessionScore) {
        this.player = player;
        this.games = games;
        this.sessionScore = sessionScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void setSessionScore(double sessionScore) {
        this.sessionScore = sessionScore;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", player=" + player +
                ", games=" + games +
                ", sessionScore=" + sessionScore +
                '}';
    }
}
