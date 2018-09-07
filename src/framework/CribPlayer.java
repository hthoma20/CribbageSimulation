package src.framework;

import src.algorithm.CribAlgorithm;
import src.algorithm.PeggingAlgorithm;
import src.card.Card;
import src.card.CribHand;

import java.util.ArrayList;

public class CribPlayer implements Pegger{
	private ArrayList<Card> hand;
	
	private ArrayList<Card> pegHand;
	
	//the algorithms used to determine how to play
	private CribAlgorithm cribAlg;
	private PeggingAlgorithm pegAlg;
	
	public CribPlayer(CribAlgorithm cribAlg, PeggingAlgorithm pegAlg){
		this.hand= null;
		this.pegHand= null;
		this.cribAlg= cribAlg;
		this.pegAlg= pegAlg;
	}
	
	//returns the two cards that the
	//player is putting in the crib
	//removes the cards from the player's hand
	//isDealer specifies whose crib it will be
	public Card[] getCrib(boolean isDealer){
		//find the crib
		Card[] crib= cribAlg.makeCrib(Card.cardListToArray(hand),isDealer);
		
		//remove those cards from the hand
		for(Card c : crib){
			hand.remove(c);
		}
		
		return crib;
	}
	
	public void setHand(Card[] cards){
		this.hand= Card.cardArrayToList(cards);
	}
	
	public Card[] getHand(){
		return Card.cardListToArray(hand);
	}
	
	//tells the player to enter pegging stage
	public void startPegging(){
		this.pegHand= Card.copyCardList(hand);
	}
	
	//pegger methods
	public Card playCard(PegTable table){
		if(pegHand == null) startPegging();
		
		Card toPlay= pegAlg.choosePlay(hand,table);
		pegHand.remove(toPlay);
		return toPlay;
	}
	
	public boolean hasCards(){
		return pegHand != null && pegHand.size() > 0;
	}
}
