package src.framework;

public class SimulationRunner implements Runnable {
    private SimulationConfig config;
    private SimulationResult result;

    private boolean isRunning= false;

    public SimulationRunner(SimulationConfig config){
        this.config= config;
        this.result= null;
    }

    public void run(){
        isRunning= true;

        int dealerWins= 0;
        int cutterWins= 0;

        CribBoard board= new CribBoard(config.getDealer(),config.getCutter());

        for(int i=0;i<config.getNumSims();i++){
            CribPlayer winner= board.playGame();
            if(winner == config.getDealer()){
                dealerWins++;
            }
            else{
                cutterWins++;
            }
        }

        this.result= new SimulationResult(dealerWins,cutterWins);

        isRunning= false;
    }

    public void setConfig(SimulationConfig config){
        checkAndThrow();

        this.config= config;
    }

    public SimulationResult getResult(){
        checkAndThrow();

        return result;
    }

    public boolean isRunning(){
        return isRunning;
    }

    private void checkAndThrow(){
        if(isRunning){
            throw new RuntimeException("Simulation running");
        }
    }
}
