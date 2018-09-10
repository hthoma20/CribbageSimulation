package src.card;

import java.util.Arrays;

//a cribbage hand which is able to
//calculate its score

public class CribHand{
	//the presumably four-card hand
	private Card[] hand;
	//the cut card
	private Card cut;
	//the hand combined with the cut, sorted by rank
	private Card[] sortedHand;
	
	//used to indicate that some hands may not actually use a cut
	//a hand with no cut will still have a cut, but this boolean will be false
	//to indicate the cut is meaningless
	private boolean hasCut;

	//used to determine whether a flsuh must include the cut
	private boolean isCrib;
	
	//negative one to indicate score is uncalculated
	private int score;

	public CribHand(Card[] hand, Card cut, boolean isCrib){
		this.hand= hand;
		this.cut= cut;
		this.hasCut= true;
		this.isCrib= isCrib;
		
		this.sortedHand= sortHand();
		
		this.score= findScore();
	}

	public CribHand(Card[] hand, Card cut){
		this(hand,cut,false);
	}
	
	//creates a crib hand with no cut
	public CribHand(Card[] hand){
		this(Arrays.copyOf(hand,hand.length-1),hand[hand.length-1]);
		this.hasCut= false;
	}
	
	//combines the hand and the cut
	//sorts by rank
	//returns resultant array
	private Card[] sortHand(){
		//create the new array, one bigger than the hand
		//to add the cut
		Card[] cards= new Card[hand.length+1];
		
		//insertion sort the hand into the cards array
		
		//first, insert the cut
		cards[0]= cut;
		
		//next insert each card from the hand
		for(int i=0;i<hand.length;i++){
			//find where this card belongs
			int insertIdx;
			for(insertIdx= i+1; insertIdx > 0; insertIdx--){
				//if the card from the hand is bigger than
				//the one before it in the cards array
				if(hand[i].compareTo(cards[insertIdx-1]) > 0){
					//then we have found where we want to insert the card
					break;
				}
			}
			//now we know where to insert the card
			//we will shift every sorted card over
			for(int j=i+1;j>insertIdx;j--){
				cards[j]= cards[j-1];
			}
			cards[insertIdx]= hand[i];
		}
		
		return cards;
	}
	
	//calculates and returns the score of this hand
	private int findScore(){
		int score= 0;
		
		score+= 2*count15();
		score+= 2*countPair();
		score+= scoreRuns();
		score+= scoreFlush();
		
		if(hasNibs()) score+= 1;
		
		return score;
	}
	
	//returns whether this hand contains nibs
	private boolean hasNibs(){
		if(!hasCut) return false;
		
		for(Card c : hand){
			//if the card is a jack and matches cut suit
			if(c.rank == Rank.JACK && c.suit == cut.suit){
				return true;
			}
		}
		
		return false;
	}
	
	//returns the number of 15's in this hand
	private int count15(){
		return count15(0,0);
	}
	
	//a recursive helper method to finding 15's in the hand
	//takes the current sum of the group we are working on
	//and the index of the next card which may be included in the sum
	//returns the number of groups that exist in the sortedHand
	//array, starting at idx, which sum to (15-sum)
	private int count15(int sum, int idx){
		if(idx >= sortedHand.length){
			return 0;
		}
		//since the cards are sorted, we don't need to check
		//past this card if it's too big
		if(sum + sortedHand[idx].value() > 15){
			return 0;
		}
		
		//the number of 15's found so far
		int count= 0;
		//start on each card
		for(int i=idx;i<sortedHand.length;i++){
			int currVal= sum + sortedHand[i].value();
			
			//if this card makes 15, add it
			if(currVal == 15){
				count++;
			}
			//otherwise check other combos with this card
			else{
				count+= count15(currVal,i+1);
			}
		}
		
		return count;
	}
	
	//returns the number of pairs in the hand
	private int countPair(){
		//the number of pairs in the hand
		int numPairs=0;
		
		for (int i=0; i<sortedHand.length; i++) {
			for (int j=i+1; j<sortedHand.length; j++) {
				//count the pair if a match is found
				if (sortedHand[i].rank == sortedHand[j].rank) {
					numPairs += 1;
				}
				//if the first and second card do not match, we don't need to 
				//keep checking the hand because we are checking the sorted hand
				else {
					break;
				}
			}
		}
		
		return numPairs;
	}
	
	//returns the number of points made by runs
	//only works with five card hand
	private int scoreRuns(){
		int runScore= 0;
		int start= 0;
		//we need to have at least three more cards to check for a run
		while(start <= sortedHand.length-3){
			int runLen= 1; //the length of the run
			int multiplier= 1; //the run multiplier determined by pairs in the run
			
			int i;
			for(i=start;i<sortedHand.length-1;i++){
				//count pairs
				int pairSize= 1;
				while(sortedHand[i].compareTo(sortedHand[i+1]) == 0){
					pairSize++;
					i++;
					if(i==sortedHand.length-1){
						break;
					}
				}
				
				//the size of the pair determines the multiplier
				multiplier*= pairSize;
				
				if(i==sortedHand.length-1){
					break;
				}
				
				//check if this is still a run
				if(sortedHand[i].compareTo(sortedHand[i+1]) == -1){
					runLen++;
				}
				//otherwise, break the loop
				else{
					break;
				}
			}
			
			if(runLen >= 3){
				runScore+= runLen*multiplier;
			}
			
			//start checking for another run
			start= i+1;
		}
		
		return runScore;
	}
	
	//returns the number of points given by a flush
	//in this hand,
	//typically four or five
	private int scoreFlush(){
		//if this is a wimpy hand, there is no flush
		if(sortedHand.length < 4) return 0;

		//if we have not cut, consider the entire hand
		//to be the flush hand
		//if we do have a cut, only look at the non-cut cards
		Card[] hand= hasCut ? this.hand : this.sortedHand;

		Suit flushSuit= hand[0].suit;
		//check the hand
		for(int i=1;i<hand.length;i++){
			//if we find a non-matching card,
			//there is no flush
			if(hand[i].suit != flushSuit){
				return 0;
			}
		}
		
		//if we get here, the hand is a flush
		//we just need to check if the cut matches as well
		if(hasCut && cut.suit == flushSuit){
			return hand.length + 1;
		}
		//otherwise, its just the hand, and in the crib is worth nothing
		return isCrib ? 0 : hand.length;
	}
	
	public int getScore(){
		return score;
	}
	
	public void print(){
		System.out.println("Hand:");
		for(Card c : hand){
			System.out.println("\t" + c);
		}
		System.out.println("Cut:");
		System.out.println("\t" + cut);
		System.out.println("Sorted:");
		for(Card c : sortedHand){
			System.out.println("\t"+c);
		}
	}

}
