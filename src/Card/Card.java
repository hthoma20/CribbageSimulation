package src.Card;

import java.util.ArrayList;

public class Card
{
	public final Rank rank;
	public final Suit suit;
	
	public Card(Rank rank, Suit suit){
		this.rank= rank;
		this.suit= suit;
	}
	
	//parses a card from a string
	public Card(String str){
		str= str.toUpperCase();
		
		char suit= str.charAt(str.length()-1);
		switch(suit){
			case 'C':
				this.suit= Suit.CLUBS;
				break;
			case 'D':
				this.suit= Suit.DIAMONDS;
				break;
			case 'H':
				this.suit= Suit.HEARTS;
				break;
			case 'S':
				this.suit= Suit.SPADES;
				break;
			default:
				this.suit= Suit.CLUBS;
				System.err.println("Invalid card string");
		}
		
		String rank= str.substring(0,str.length()-1);
		switch(rank){
			case "A":
				this.rank= Rank.ACE;
				break;
			case "2":
				this.rank= Rank.TWO;
				break;
			case "3":
				this.rank= Rank.THREE;
				break;
			case "4":
				this.rank= Rank.FOUR;
				break;
			case "5":
				this.rank= Rank.FIVE;
				break;
			case "6":
				this.rank= Rank.SIX;
				break;
			case "7":
				this.rank= Rank.SEVEN;
				break;
			case "8":
				this.rank= Rank.EIGHT;
				break;
			case "9":
				this.rank= Rank.NINE;
				break;
			case "10":
				this.rank= Rank.TEN;
				break;
			case "J":
				this.rank= Rank.JACK;
				break;
			case "Q":
				this.rank= Rank.QUEEN;
				break;
			case "K":
				this.rank= Rank.KING;
				break;
			default:
				this.rank= Rank.ACE;
				System.err.println("Invalid card string");
		}
	}
	
	public int compareTo(Card c){
		return this.rank.compareTo(c.rank);
	}
	
	//returns the cribbage value of this card
	public int value(){
		return rank.val;
	}
	
	public String toString(){
		return rank.toString() + " of " + suit.toString();
	}
	
	public static ArrayList<Card> cardArrayToList(Card[] cards){
		ArrayList<Card> list= new ArrayList<Card>(cards.length);
		for(Card c : cards){
			list.add(c);
		}
		return list;
	}
	
	public static Card[] cardListToArray(ArrayList<Card> cards){
		Card[] ar= new Card[cards.size()];
		cards.toArray(ar);
		
		return ar;
	}

	public static ArrayList<Card> copyCardList(ArrayList<Card> cards){
		ArrayList<Card> copy= new ArrayList<Card>(cards.size());
		for(Card c : cards){
			copy.add(c);
		}

		return copy;
	}
}
