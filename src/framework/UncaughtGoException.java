package src.framework;

import src.card.Card;

public class UncaughtGoException extends RuntimeException {
    private Card play;

    public UncaughtGoException(Card play) {
        this.play = play;
    }

    public Card getPlay() {
        return play;
    }
}
