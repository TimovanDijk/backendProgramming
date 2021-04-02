package com.timo.lingoapplication.shared.message;

public class LetterFeedbackMessage {
    private int position;
    private String letter;
    private LetterState letterState;

    public LetterFeedbackMessage(int position, String letter, LetterState letterState) {
        this.position = position;
        this.letter = letter;
        this.letterState = letterState;
    }

    public int getPosition() {
        return position;
    }

    public String getLetter() {
        return letter;
    }

    public LetterState getLetterState() {
        return letterState;
    }

    @Override
    public String toString() {
        return "LetterFeedbackMessage{" +
                "position=" + position +
                ", letter='" + letter + '\'' +
                ", letterState=" + letterState +
                '}';
    }
}
