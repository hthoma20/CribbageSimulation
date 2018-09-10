package src.algorithm;

import src.card.Card;
import src.card.CribDeck;
import src.framework.PegTable;

import java.util.ArrayList;

public class DefensivePeggingAlgorithm implements PeggingAlgorithm {
    @Override
    public Card choosePlay(ArrayList<Card> hand, PegTable table) {
        //get a list of all possible cards the opponent could have
        CribDeck deck= new CribDeck(false);
        deck.removeMatches(Card.cardListToArray(hand));
        deck.removeMatches(table.getPegDeck());
        Card[] possible= deck.getDeckArray();

        //now determine what to play
        int minEV= 200; //way bigger than possible
        int maxScore= -1; //less than possible
        Card bestPlay= null;

        for(Card myPlay : hand){
            int myScore= 0;
            int theirEV= 0;
            for(Card theirPlay : possible){
                int[] scores= table.playScoreQuery(new Card[]{myPlay,theirPlay});
                myScore= scores[0];
                theirEV+= scores[1];
            }
            theirEV/= possible.length;

            if(theirEV < minEV){
                minEV= theirEV;
                maxScore= myScore;
                bestPlay= myPlay;
            }
            else if(theirEV == minEV){
                if(myScore > maxScore){
                    maxScore= myScore;
                    bestPlay= myPlay;
                }
            }//if we should change bestPlay
        }//for each card in my hand

        return bestPlay;
    }
}
