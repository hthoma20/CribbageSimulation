package src.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import src.algorithm.*;

import java.util.HashMap;
import java.util.Observable;
import java.util.Set;

public class ControlPanel extends GridPane {
    private ToggleGroup dealerRadio;
    private RadioButton redDealer;
    private RadioButton blueDealer;

    private ComboBox<String> redPegChoices;
    private ComboBox<String> redCribChoices;
    private ComboBox<String> bluePegChoices;
    private ComboBox<String> blueCribChoices;

    private Button runButton;

    private Text redWins;
    private Text blueWins;

    private HashMap<String, CribAlgorithm> cribAlgMap;
    private HashMap<String, PeggingAlgorithm> pegAlgMap;

    public ControlPanel(){
        super();
        setHgap(20);
        setVgap(20);

        setupDealerRadio();
        setupAlgorithmMaps();
        setupAlgorithmChoices();

        setupWinTexts();

        setupLabelCol(0);
        setupRedCol(1);
        setupBlueCol(2);
        setupRunButtonCol(3);
    }

    private void setupRunButtonCol(int col) {
        this.runButton= new Button("Run Simulation");

        add(runButton,col,0,1,REMAINING);
    }

    private void setupWinTexts(){
        this.redWins= new Text("0.00%");
        this.blueWins= new Text("0.00%");
    }

    private void setupAlgorithmChoices() {
        ObservableList<String> peggingStrings= getPeggingStrings();
        ObservableList<String> cribStrings= getCribStrings();

        this.redPegChoices= new ComboBox<>(peggingStrings);
        this.bluePegChoices= new ComboBox<>(peggingStrings);
        this.redCribChoices= new ComboBox<>(cribStrings);
        this.blueCribChoices= new ComboBox<>(cribStrings);

        redPegChoices.setValue(peggingStrings.get(0));
        bluePegChoices.setValue(peggingStrings.get(0));
        redCribChoices.setValue(cribStrings.get(0));
        blueCribChoices.setValue(cribStrings.get(0));
    }

    private void setupAlgorithmMaps() {
        this.cribAlgMap= new HashMap<>();
        cribAlgMap.put("Random",new RandomCribAlgorithm());
        cribAlgMap.put("High-Four",new HighFourCribAlgorithm());

        this.pegAlgMap= new HashMap<>();
        pegAlgMap.put("Random",new RandomPeggingAlgorithm());
        pegAlgMap.put("High Play",new HighPlayPeggingAlgorithm());
    }

    private ObservableList<String> getCribStrings(){
        Set<String> strings= cribAlgMap.keySet();
        return FXCollections.observableArrayList(strings);
    }

    private ObservableList<String> getPeggingStrings(){
        Set<String> strings= pegAlgMap.keySet();
        return FXCollections.observableArrayList(strings);
    }

    private void setupDealerRadio(){
        this.dealerRadio= new ToggleGroup();
        this.redDealer= new RadioButton();
        this.blueDealer= new RadioButton();

        redDealer.setToggleGroup(dealerRadio);
        blueDealer.setToggleGroup(dealerRadio);

        dealerRadio.selectToggle(redDealer);
    }

    private void setupLabelCol(int col){
        add(new Text("Player:"),col,0);
        add(new Text("Dealer:"),col,1);
        add(new Text("Crib Selection Algorithm:"),col,2);
        add(new Text("Pegging Algorithm:"),col,3);
        add(new Text("Wins:"),col,4);
    }

    private void setupRedCol(int col){
        add(new Text("Red"),col,0);
        add(redDealer,col,1);
        add(redCribChoices,col,2);
        add(redPegChoices,col,3);
        add(redWins,col,4);
    }

    private void setupBlueCol(int col){
        add(new Text("Blue"),col,0);
        add(blueDealer,col,1);
        add(blueCribChoices,col,2);
        add(bluePegChoices,col,3);
        add(blueWins,col,4);
    }
}
