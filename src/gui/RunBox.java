package src.gui;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import src.framework.CribPlayer;
import src.framework.SimulationConfig;
import src.framework.SimulationResult;
import src.framework.SimulationRunner;


public class RunBox extends VBox {
    private ControlPanel controls;

    private Button runButton;
    private TextField numSimsField;

    private ProgressBar simProgressBar;

    public RunBox(ControlPanel controls){
        setAlignment(Pos.CENTER);
        setSpacing(15);
        this.controls= controls;

        setupRunButton();
        setupSimsField();
        setupSimProgressBar();
    }

    private void setupRunButton(){
        this.runButton= new Button("Run Simulation");
        runButton.setOnAction(event -> runButtonPushed());

        add(runButton);
    }

    private void setupSimsField(){
        VBox simsBox= new VBox();

        this.numSimsField= new TextField("10000");
        numSimsField.setPrefColumnCount(5);
        simsBox.getChildren().add(new Text("Simulations:"));
        simsBox.getChildren().add(numSimsField);

        add(simsBox);
    }

    private void setupSimProgressBar(){
        this.simProgressBar= new ProgressBar(0);
        add(simProgressBar);
    }

    private void runButtonPushed() {
        int numSims= Integer.valueOf(numSimsField.getText());

        SimulationConfig config=
                new SimulationConfig(controls.getDealer(),controls.getCutter(),
                        controls.getDealerScore(),controls.getCutterScore(),
                        numSims);

        SimulationRunner runner= new SimulationRunner(config);

        runner.setOnSucceeded(event -> simulationFinished(event));

        simProgressBar.progressProperty().bind(runner.progressProperty());
        Thread simThread= new Thread(runner);
        //simThread.setDaemon(true);
        simThread.start();
    }

    private void simulationFinished(WorkerStateEvent event){
        SimulationRunner runner= (SimulationRunner)event.getSource();

        SimulationResult result= runner.getResult();

        controls.setDealerWinsText(getPercentString(result.getDealerPercent(),2));
        controls.setCutterWinsText(getPercentString(result.getCutterPercent(),2));
    }

    private void add(Node node){
        getChildren().add(node);
    }

    private String getPercentString(double percent, int precision){
        percent*= 100;
        String percentString= String.valueOf(percent);
        int decIndex= percentString.indexOf(".");
        if(decIndex + precision < percentString.length()){
            percentString= percentString.substring(0,decIndex+precision+1);
        }

        return percentString+"%";
    }
}
