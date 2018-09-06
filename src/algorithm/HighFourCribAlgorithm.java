package src.algorithm;

import src.Card.Card;
import src.Card.CribHand;

public class HighFourCribAlgorithm implements CribAlgorithm {
	public Card[] makeCrib(Card[] hand, boolean isDealer){
		Card[] crib= new Card[2];
		
		Card[][] combos= makeEachCombo(hand);
		
		//find which combo is the best
		int maxScore= -1;
		Card[] best= null;
		
		for(Card[] cards : combos){
			CribHand scoring= new CribHand(cards);
			int score= scoring.getScore();
			if(score > maxScore){
				maxScore= score;
				best= cards;
			}
		}
		
		//now we have the best hand
		//which cards are not part of it?
		int index= 0;
		for(Card c : hand){
			//if the best hand doesn't contain c,
			//add it to the crib
			if(!contains(best,c)){
				crib[index++]= c;
			}
		}
		
		return crib;
	}
	
	//makes each 4-card combination
	protected Card[][] makeEachCombo(Card[] hand){
		int numCombos= choose2(hand.length); 
		Card[][] combos= new Card[numCombos][];
		int index= 0;
		
		//pick which four cards to take
		for(int i=0;i<hand.length;i++){
			for(int j=i+1;j<hand.length;j++){
				for(int k=j+1;k<hand.length;k++){
					for(int m=k+1;m<hand.length;m++){
						combos[index++]= makeCombo(hand,new int[]{i,j,k,m});
					}
				}
			}
		}
		
		return combos;
	}
	
	//makes a new Card array out of the given array and the
	//indexes to include
	private Card[] makeCombo(Card[] hand,int[] include){
		Card[] combo= new Card[include.length];
		
		for(int i=0;i<combo.length;i++){
			combo[i]= hand[include[i]];
		}
		
		return combo;
	}
	
	//tells whether the given cards contain the given card
	private boolean contains(Card[] cards, Card card){
		for(Card c : cards){
			if(c == card) return true;
		}
		
		return false;
	}
	
	//returns Choose(n,2)
	private int choose2(int n){
		return (n*(n-1))/2;
	}
}
