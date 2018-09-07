package src.framework;

public class SimulationResult {
    private int dealerWins;
    private int cutterWins;

    public SimulationResult(int dealerWins, int cutterWins){
        this.dealerWins= dealerWins;
        this.cutterWins= cutterWins;
    }

    public SimulationResult(){
        this(0,0);
    }

    public void dealerWin(){
        dealerWins++;
    }

    public void cutterWin(){
        cutterWins++;
    }

    public int getDealerWins(){
        return dealerWins;
    }

    public int getCutterWins(){
        return cutterWins;
    }

    public double getDealerPercent(){
        return (double)dealerWins/(dealerWins+cutterWins);
    }

    public double getCutterPercent(){
        return (double)cutterWins/(dealerWins+cutterWins);
    }
}
