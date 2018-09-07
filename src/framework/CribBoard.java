package src.framework;
/* the controller class for a game of cribbage
this keeps track of score and decides when players will play and count
*/

import src.algorithm.HighFourCribAlgorithm;
import src.algorithm.RandomPeggingAlgorithm;
import src.card.Card;
import src.card.CribHand;
import src.card.Rank;
import src.card.CribDeck;

public class CribBoard{
	//players
	private CribPlayer p1;
	private CribPlayer p2;
	
	//score keeper
	private CribScore score;
	
	//the deck
	private CribDeck deck;
	
	//which player is dealing
	private CribPlayer dealer;
	private CribPlayer cutter; 
	
	private int gamePoint= 121;
	
	public CribBoard(CribPlayer p1, CribPlayer p2){
		this.p1= p1;
		this.p2= p2;
		
		this.score= new CribScore(p1,p2);
	}
	
	//play a game and return the winner
	public CribPlayer playGame(){
		dealer= p1;
		cutter= p2;
		
		//"try" to find a winner
		try{
			//a GameOverException will break this loop
			while(true){
				//shuffle cards
				deck= new CribDeck();
				
				//deal hands
				dealer.setHand(deck.dealHand());
				cutter.setHand(deck.dealHand());
				
				//play hand
				playFullHand();
				
				//finally, switch dealer
				switchDeal();
			}
		}
		//when the game ends, "catch" the winner
		catch(GameOverException gameOver){
			return gameOver.getWinner();
		}
	}
	
	//plays a full hand
	//precondition: players have cards dealt
	private void playFullHand() throws GameOverException{
		//get crib
		Card[] crib= createCrib();
		
		//cut card
		Card cut= deck.cut();
		//nobs
		if(cut.rank == Rank.JACK){
			score.score(dealer,2);
		}
		
		PegTable peg= new PegTable(cutter,dealer,score);
		peg.doPegging();
		
		//make and score each of the three hands
		//the cutter's hand
		CribHand scoring= new CribHand(cutter.getHand(),cut);
		score.score(cutter,scoring.getScore());
		
		//the dealers hand
		scoring= new CribHand(dealer.getHand(),cut);
		score.score(dealer,scoring.getScore());
		
		//the crib
		scoring= new CribHand(crib,cut);
		score.score(dealer,scoring.getScore());
	}
	
	private Card[] createCrib(){
		Card[] h1= dealer.getCrib(true);
		Card[] h2= cutter.getCrib(false);
		
		Card[] crib= new Card[h1.length+h2.length];
		for(int i=0;i<h1.length;i++){
			crib[i]= h1[i];
		}
		for(int i=0;i<h2.length;i++){
			crib[h1.length+i]= h2[i];
		}
		
		return crib;
	}
	
	private void switchDeal(){
		if(dealer == p1){
			dealer= p2;
			cutter= p1;
		}
		else{
			dealer= p1;
			cutter= p2;
		}
	}
}
