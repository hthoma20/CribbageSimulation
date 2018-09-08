package src.framework;

public class SimulationConfig {
    //the first dealer and other player
    private CribPlayer dealer;
    private CribPlayer cutter;

    private int cutterScore;
    private int dealerScore;

    private int numSims;

    public SimulationConfig(CribPlayer dealer, CribPlayer cutter, int dealerScore, int cutterScore, int numSims){
        this.dealer= dealer;
        this.cutter= cutter;
        this.numSims= numSims;
        this.dealerScore= dealerScore;
        this.cutterScore= cutterScore;
    }

    public SimulationConfig(CribPlayer dealer, CribPlayer cutter, int numSims){
        this(dealer,cutter,0,0,numSims);
    }

    public CribPlayer getDealer() {
        return dealer;
    }

    public CribPlayer getCutter() {
        return cutter;
    }

    public int getNumSims() {
        return numSims;
    }

    public int getCutterScore() {
        return cutterScore;
    }

    public int getDealerScore() {
        return dealerScore;
    }
}
