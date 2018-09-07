package src.card;

import src.card.Card;
import src.card.Rank;
import src.card.Suit;

import java.util.ArrayList;

public class CribDeck
{
	private ArrayList<Card> cards= null;

	//creates a shuffled crib deck
	public CribDeck(){
		this(true);
	}
	
	//creates a crib deck
	//choose whether to shuffle the deck
	public CribDeck(boolean shuffle){
		initDeck();
		if(shuffle) shuffle();
	}
	
	//initialize the deck
	private void initDeck(){
		this.cards= new ArrayList<Card>(52);
		
		for(Suit s : Suit.values()){
			for(Rank r : Rank.values()){
				cards.add(new Card(r,s));
			}
		}
	}
	
	private void shuffle(){
		//go to each position and swap with a random position
		for(int i=0;i<cards.size();i++){
			int pos= (int)(Math.random()*cards.size());
			
			Card temp= cards.get(i);
			cards.set(i,cards.get(pos));
			cards.set(pos,temp);
		}
	}
	
	//deals a six-card hand
	public Card[] dealHand(){
		return dealHand(6);
	}
	
	//deals a hand of n cards
	public Card[] dealHand(int n){
		Card[] hand= new Card[n];
		for(int i=0;i<n;i++){
			hand[i]= cards.remove(cards.size()-1);
		}
		
		return hand;
	}
	
	//returns a single card
	public Card cut(){
		return dealHand(1)[0];
	}
	
	public void print(){
		for(Card c : cards){
			System.out.println(c.toString());
		}
	}
}
