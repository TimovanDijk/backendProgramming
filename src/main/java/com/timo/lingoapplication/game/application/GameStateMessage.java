package com.timo.lingoapplication.game.application;

import com.timo.lingoapplication.game.domain.Game;

public class GameStateMessage {
    private Boolean correct;
    private Game game;
    private String message;

    public GameStateMessage(Boolean correct, Game game, String message) {
        this.correct = correct;
        this.game = game;
        this.message = message;
    }

    public GameStateMessage() {
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "GameStateMessage{" +
                "correct=" + correct +
                ", game=" + game +
                ", message='" + message + '\'' +
                '}';
    }
}
