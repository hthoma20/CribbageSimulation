package src.Card;

public enum Rank
{
    ACE(1,"A"),
    TWO(2,"2"),
    THREE(3,"3"),
    FOUR(4,"4"),
    FIVE(5,"5"),
    SIX(6,"6"),
    SEVEN(7,"7"),
    EIGHT(8,"8"),
    NINE(9,"9"),
    TEN(10,"10"),
    JACK(10,"J"),
    QUEEN(10,"Q"),
    KING(10,"K");
    
    public final int val; //the cribbage value of the Rank
    private String str;
	
	Rank(int val, String str){
		this.val= val;
		this.str= str;
	}
    
    public String toString(){
        return str;
    }
}
