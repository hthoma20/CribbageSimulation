package src.algorithm;

import src.Card.Card;

public class RandomCribAlgorithm implements CribAlgorithm {
	public Card[] makeCrib(Card[] hand, boolean isDealer){
		Card[] crib= new Card[2];
		//choose the back two cards
		for(int i=0;i<crib.length;i++){
			crib[i]= hand[hand.length-i-1];
		}
		
		return crib;
	}
}
