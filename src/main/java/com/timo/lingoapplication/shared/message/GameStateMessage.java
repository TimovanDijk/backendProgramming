package com.timo.lingoapplication.shared.message;

import com.timo.lingoapplication.game.domain.Game;

import java.util.List;

public class GameStateMessage {
    private Boolean correct;
    private Game game;
    private String message;
    private List<LetterFeedbackMessage> letterFeedback;

    public GameStateMessage(Boolean correct, Game game, String message, List<LetterFeedbackMessage> letterFeedback) {
        this.correct = correct;
        this.game = game;
        this.message = message;
        this.letterFeedback = letterFeedback;
    }

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

    public Game getGame() {
        return game;
    }

    public String getMessage() {
        return message;
    }

    public List<LetterFeedbackMessage> getLetterFeedback() {
        return letterFeedback;
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
