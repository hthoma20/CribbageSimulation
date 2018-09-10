package src.algorithm;

import src.card.Card;
import src.card.CribHand;

public class DefensiveCribAlgorithm extends HighSixCribAlgorithm {
    @Override
    protected int score6(Card[] keep, Card[] crib, boolean isDealer){
        CribHand cribHand= new CribHand(crib);
        int cribScore= cribHand.getScore();

        //if the dealer isn't us, and would get any points
        if(!isDealer && cribScore>0){
            return cribScore;
        }

        return super.score6(keep, crib, isDealer);
    }
}
