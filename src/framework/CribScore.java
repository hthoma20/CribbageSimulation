package src.framework;

public class CribScore{
	private CribPlayer p1;
	private CribPlayer p2;
	
	private int score1;
	private int score2;
	
	private int gamePoint= 121;
	
	public CribScore(CribPlayer p1, CribPlayer p2){
		this.p1= p1;
		this.p2= p2;
		
		score1= 0;
		score2= 0;
	}
	
	public void score(CribPlayer player, int score) throws GameOverException{
		if(player == p1) score1+= score;
		else if(player == p2) score2+= score;
		
		checkAndThrow();
	}
	
	public void score(Pegger pegger, int score){
		score((CribPlayer)pegger,score);
	}
	
	//checks for game over and throws a gameoverException if needed
	private void checkAndThrow() throws GameOverException{
		if(score1 >= gamePoint){
			throw(new GameOverException(p1));
		}
		if(score2 >= gamePoint){
			throw(new GameOverException(p2));
		}
	}
}
