package src.card;

public enum Suit
{
    HEARTS("Hearts"),
    CLUBS("Clubs"),
    DIAMONDS("Diamonds"),
    SPADES("Spades");
    
    private String str;
    
    Suit(String str){
        this.str= str;
    }
    
    public String toString(){
        return str;
    }
}
