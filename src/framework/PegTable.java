package src.framework;

import src.Card.Card;

import java.util.ArrayList;

public class PegTable{
	private Pegger cutter; //the first player to peg
	private Pegger dealer;
	private Pegger currPegger; //the current player to peg
	
	private CribScore score; //the score keeper
	
	//score from pegging
	private int pegPoint;
	//the score that determines whether you take a "go" point or not
	private final int goPoint= 31; 
	
	//the cards played in order during pegging
	private ArrayList <Card> pegDeck= new ArrayList <Card>();
		
	public PegTable(Pegger cutter, Pegger dealer, CribScore score){
		this.cutter= cutter;
		this.dealer= dealer;
		this.currPegger= cutter; 
		
		this.score= score;
	}
	
	public void doPegging() throws GameOverException{
		//we want to keep pegging if either player
		//still has a card
		while(cutter.hasCards() || dealer.hasCards()){
			doPegRound();
		}
	}
	
	//does a "round" of pegging, up to 31
	private void doPegRound(){
		//continue regular pegging play while there is not a go
		Card play= currPeggerPlay();
		while(play != null){
			playCard(play);
			switchPegger();
			
			play= currPeggerPlay();
		}
		
		//when there is a go, the other player should continue to play
		//until they cannot
		switchPegger();
		play= currPeggerPlay();
		while(play != null){
			playCard(play);
		}
		
		//score the go
		score.score(currPegger,1);
		
		//switch turns for next round
		switchPegger();
	}
	
	private Card currPeggerPlay(){
		return currPegger.playCard(this);
	}
	
	//plays the card and adds any scored points
	//to the current player's score
	private void playCard(Card card) throws GameOverException{
		pegDeck.add(card);
		score.score(currPegger,getPlayScore());
	}

	/**
	 * Calculates the value that playing the given card
	 * would yeild.
	 * Does not alter the current pegger, or the game score
	 *
	 * @param play the card to simulate playing
	 * @return the score that play would give
	 */
	public int playScoreQuery(Card play){
		pegDeck.add(play);
		int score= getPlayScore();
		pegDeck.remove(play);

		return score;
	}
	
	private void switchPegger() {
		if(currPegger == cutter){
			currPegger= dealer;
		}
		else{
			currPegger= cutter;
		}
	}
	
	public int sumPegDeck() {
		int sum= 0;
		for(Card c : pegDeck){
			sum+= c.value();
		}
		
		return sum;
	}
	
	//returns the score from pairs
	private int scorePairs() {
		
		//the most recent card played
		Card topCard= pegDeck.get(pegDeck.size()-1);
		
		//number of pairs
		//the first card matches the first card
		int numCards= 1;
		
		//now we are checking to see if the preceding cards match the top card
		//we are starting the for loop looking at the previous card
		for (int i= pegDeck.size()-2; i>= 0; i--) {
			//if top card and prev card have same rank
			if (topCard.compareTo(pegDeck.get(i)) == 0) {
				numCards+= 1;
			}
			else {
				break;
			}
		}
		
		return choosePairs(numCards)*2;
		
	}
	
	//returns numCards choose 2
	private int choosePairs(int numCards) {
		
		//the number of pairs
		int numPairs= 0; 
		
		//do not want to include the first card
		//for example: 3 choose 2 would only count from the second card,
		//and not the first
		for (int i= numCards-1; i>0; i--) {
			numPairs+= i;
		}
		
		return numPairs;
	}
	
	//returns the score from a run in pegDeck
	private int scoreRuns() {
		//start at the front, and see if the cards are a run
		//stop at size()-3 because runs must be at least 3 long
		for(int i=0;i<=pegDeck.size()-3;i++){
			//are the cards starting at i, a run?
			if(isRun(i)){
				return pegDeck.size()-i;
			}
		}
		
		//if we get here, no more runs
		return 0;
	}
	
	//tells whether the cards starting at start
	//are a run
	private boolean isRun(int start){
		//the cards are a run iff
		//there are no repeats
		//and max-min == n-1
		
		Card min= pegDeck.get(start);
		Card max= pegDeck.get(start);
		
		for(int i=start;i<pegDeck.size();i++){
			if(pegDeck.get(i).compareTo(min) < 0){
				min= pegDeck.get(i);
			}
			if(pegDeck.get(i).compareTo(max) > 0){
				max= pegDeck.get(i);
			}
		}
		
		//if max-min != n-1, then there is no run
		if(max.compareTo(min) != pegDeck.size()-start-1){
			return false;
		}
		
		//check for repeats
		for(int i=start;i<pegDeck.size();i++){
			for(int j=i+1;j<pegDeck.size();j++){
				//if there is a pair
				if(pegDeck.get(i).compareTo(pegDeck.get(j)) == 0){
					//there is no run
					return false;
				}
			}
		}
		
		//if we get here, then max-min == n-1
		//and there are no repeats
		//so we have a run
		return true;
	}
	
	//gets the resulting score of the most recently played
	//card on the pegDeck
	private int getPlayScore(){
		int score= 0;
		
		//the sum of the cards
		int cardSum= sumPegDeck();
		
		//sum of 15
		if (cardSum == 15) {
			score+= 2;
		}
		
		//pairs
		score+= scorePairs();
			
		
		//runs
		score+= scoreRuns();
		
		//31
		if (cardSum == 31) {
			score+= 1;
		}
		
		return score;
	}
	
	public Card[] getPegDeck(){
		Card[] deck= new Card[pegDeck.size()];
		pegDeck.toArray(deck);
		
		return deck;
	}
	
	public int getGoPoint(){
		return goPoint;
	}
}
