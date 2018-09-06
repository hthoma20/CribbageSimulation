package src.framework;

public class GameOverException extends RuntimeException{
	private CribPlayer winner;
	
	public GameOverException(CribPlayer winner){
		super();
		this.winner= winner;
	}
	
	public CribPlayer getWinner(){
		return winner;
	}
}
