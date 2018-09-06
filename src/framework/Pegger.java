package src.framework;

import src.Card.Card;

public interface Pegger{

	//plays a card onto the given peg table
	//and removes it from it's list of cards
	//returns null if there is no legal play
	public Card playCard(PegTable table);
	
	public boolean hasCards();
}
