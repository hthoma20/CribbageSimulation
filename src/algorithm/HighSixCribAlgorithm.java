package src.algorithm;

import src.card.Card;
import src.card.CribHand;

/**
 * an algorithm that accounts for the points the dealer will score
 */
public class HighSixCribAlgorithm extends HighFourCribAlgorithm {

    @Override
    protected int score6(Card[] keep, Card[] crib, boolean isDealer){
        CribHand keepHand= new CribHand(keep);
        CribHand cribHand= new CribHand(crib);

        int score= keepHand.getScore();
        if(isDealer){
            score+= cribHand.getScore();
        }
        else{
            score-= cribHand.getScore();
        }

        //System.out.println("\t"+score);
        return score;
    }
}
