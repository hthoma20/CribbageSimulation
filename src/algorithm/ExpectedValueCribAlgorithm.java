package src.algorithm;

import src.card.Card;
import src.card.CribDeck;
import src.card.CribHand;

/**
 * a crib algorithm which looks at each possible cut to determine the score of the
 * given hand
 */
public class ExpectedValueCribAlgorithm extends HighFourCribAlgorithm{
    protected int score6(Card[] keep, Card[] crib, boolean isDealer){
        //get a list of all possible cuts
        CribDeck deck= new CribDeck(false);
        deck.removeMatches(keep);
        deck.removeMatches(crib);
        Card[] cuts= deck.getDeckArray();

        //go find the expected value
        int totalScores= 0;
        for(Card cut : cuts){
            CribHand keepHand= new CribHand(keep,cut);
            CribHand cribHand= new CribHand(crib,cut);

            totalScores+= keepHand.getScore();
            totalScores+= isDealer ? cribHand.getScore() : -cribHand.getScore();
        }

        return totalScores/cuts.length;
    }
}
