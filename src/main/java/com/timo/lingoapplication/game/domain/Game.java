package com.timo.lingoapplication.game.domain;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import com.timo.lingoapplication.word.domain.Word;

import javax.persistence.*;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nullable
    private int roundNumber;

    @ManyToOne
    @JoinColumn(name="word_id", nullable=false)
    private Word word;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GameType gameType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GameState gameState;


    public Game(Long id, int roundNumber, Word word, GameType gameType, GameState gameState) {
        this.id = id;
        this.roundNumber = roundNumber;
        this.word = word;
        this.gameType = gameType;
        this.gameState = gameState;
    }

    public Game(GameType gameType, GameState gameState) {
        this.gameType = gameType;
        this.gameState = gameState;
        this.roundNumber = 0;
    }

    public Game() {
        this.roundNumber = 0;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public GameType getGameType() {
        return gameType;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", roundNumber=" + roundNumber +
                ", gameType=" + gameType +
                ", gameState=" + gameState +
                '}';
    }

}
