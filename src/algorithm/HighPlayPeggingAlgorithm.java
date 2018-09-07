package src.algorithm;

import src.card.Card;
import src.framework.PegTable;

import java.util.ArrayList;

public class HighPlayPeggingAlgorithm implements PeggingAlgorithm {
    @Override
    public Card choosePlay(ArrayList<Card> hand, PegTable table) {
        Card highPlay= null;
        int highScore= -1;

        for(Card c : hand){
            int score= table.playScoreQuery(c);
            if(score > highScore){
                highPlay= c;
                highScore= score;
            }
        }

        return highPlay;
    }
}
