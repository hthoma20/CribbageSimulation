package src.algorithm;

import src.card.Card;
import src.card.CribHand;

public class HighFourCribAlgorithm implements CribAlgorithm {
	public Card[] makeCrib(Card[] hand, boolean isDealer){
		Card[][] combos= makeEachCombo(hand);
		
		//find which combo is the best
		int maxScore= -3; //it is inconceivable that a score could be less than -2
		Card[] bestCrib= null;
		
		for(Card[] keep : combos){
			Card[] crib= findCrib(hand,keep);
			int score= score6(keep,crib,isDealer);
			if(score > maxScore){
				maxScore= score;
				bestCrib= crib;
			}
		}

		return bestCrib;
	}

	private Card[] findCrib(Card[] hand, Card[] keep){
		Card[] crib= new Card[hand.length-keep.length];

		int index= 0;
		for(Card c : hand){
			//if the card is not to keep
			if(!contains(keep,c)){
				//add it to the crib
				crib[index++]= c;
			}
		}

		return crib;
	}

	//returns the score of these 4 cards
	protected int score6(Card[] keep, Card[] crib, boolean isDealer){
		CribHand scoring= new CribHand(keep);
		return scoring.getScore();
	}
	
	//makes each 4-card combination
	private Card[][] makeEachCombo(Card[] hand){
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
