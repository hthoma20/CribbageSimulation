package src.framework;

import javafx.concurrent.Task;

public class SimulationRunner extends Task<Integer> {
    private SimulationConfig config;
    private SimulationResult result;

    private boolean isRunning= false;

    public SimulationRunner(SimulationConfig config){
        this.config= config;
        this.result= null;
    }

    public Integer call(){
        isRunning= true;

        int dealerWins= 0;
        int cutterWins= 0;

        CribBoard board=
                new CribBoard(config.getDealer(),config.getCutter(),
                              config.getDealerScore(),config.getCutterScore());

        for(int i=0;i<config.getNumSims();i++){
            CribPlayer winner= board.playGame();
            if(winner == config.getDealer()){
                dealerWins++;
            }
            else{
                cutterWins++;
            }

            updateProgress(i,config.getNumSims());
        }

        this.result= new SimulationResult(dealerWins,cutterWins);

        isRunning= false;

        return 0;
    }

    public void setConfig(SimulationConfig config){
        checkAndThrow();

        this.config= config;
    }

    public SimulationResult getResult(){
        checkAndThrow();

        return result;
    }

    private void checkAndThrow(){
        if(isRunning){
            throw new RuntimeException("Simulation running");
        }
    }
}
