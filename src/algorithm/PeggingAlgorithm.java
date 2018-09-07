package src.algorithm;

import src.card.Card;
import src.framework.PegTable;

import java.util.ArrayList;

public interface PeggingAlgorithm {
    //chooses a card to play given a player's hand
    //and a PegTable on which to play
    public Card choosePlay(ArrayList<Card> hand, PegTable table);
}
