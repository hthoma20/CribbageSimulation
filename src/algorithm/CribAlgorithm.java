package src.algorithm;

import src.card.Card;

public interface CribAlgorithm{
	public Card[] makeCrib(Card[] hand, boolean isDealer);
}
