package src.framework;

public class SimulationConfig {
    //the first dealer and other player
    private CribPlayer dealer;
    private CribPlayer cutter;

    private int numSims;

    public SimulationConfig(CribPlayer dealer, CribPlayer cutter, int numSims){
        this.dealer= dealer;
        this.cutter= cutter;
        this.numSims= numSims;
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
}
