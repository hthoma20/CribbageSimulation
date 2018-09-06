package src.algorithm;

import src.Card.Card;
import src.framework.PegTable;

import java.util.ArrayList;

public class RandomPeggingAlgorithm implements PeggingAlgorithm {
    @Override
    public Card choosePlay(ArrayList<Card> hand, PegTable table) {
        //figure out if you can play without a go
        //make a list of legally playable cards
        ArrayList<Card> playable= new ArrayList<Card>(hand.size());
        int currSum= table.sumPegDeck();
        int goPoint= table.getGoPoint();

        for(Card c : hand){
            //if this is playable
            if(currSum + c.value() <= goPoint){
                playable.add(c);
            }
        }
        //if we have no playable choice
        if(playable.size() < 1) return null;

        //now we can select which card to play from the playable list
        return playable.get(0);
    }
}
