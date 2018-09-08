package src.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import src.algorithm.*;
import src.framework.CribPlayer;

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

    private Text redWins;
    private Text blueWins;

    private TextField redScore;
    private TextField blueScore;

    private HashMap<String, CribAlgorithm> cribAlgMap;
    private HashMap<String, PeggingAlgorithm> pegAlgMap;

    public ControlPanel(){
        super();
        setHgap(20);
        setVgap(20);

        setupDealerRadio();
        setupScoreFields();
        setupAlgorithmMaps();
        setupAlgorithmChoices();

        setupWinTexts();

        setupLabelCol(0);
        setupRedCol(1);
        setupBlueCol(2);
    }

    private void setupWinTexts(){
        this.redWins= new Text("0.00%");
        this.blueWins= new Text("0.00%");
    }

    private void setupScoreFields(){
        this.redScore= new TextField("0");
        this.blueScore= new TextField("0");

        redScore.setPrefColumnCount(2);
        blueScore.setPrefColumnCount(2);
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
        cribAlgMap.put("High-Six",new HighSixCribAlgorithm());
        cribAlgMap.put("EV",new ExpectedValueCribAlgorithm());

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
        add(new Text("Score:"),col,4);
        add(new Text("Wins:"),col,5);
    }

    private void setupRedCol(int col){
        add(new Text("Red"),col,0);
        add(redDealer,col,1);
        add(redCribChoices,col,2);
        add(redPegChoices,col,3);
        add(redScore,col,4);
        add(redWins,col,5);
    }

    private void setupBlueCol(int col){
        add(new Text("Blue"),col,0);
        add(blueDealer,col,1);
        add(blueCribChoices,col,2);
        add(bluePegChoices,col,3);
        add(blueScore,col,4);
        add(blueWins,col,5);
    }

    private CribPlayer getRedPlayer(){
        CribAlgorithm cribAlg= cribAlgMap.get(redCribChoices.getValue());
        PeggingAlgorithm pegAlg= pegAlgMap.get(redPegChoices.getValue());

        return new CribPlayer(cribAlg,pegAlg);
    }

    private CribPlayer getBluePlayer(){
        CribAlgorithm cribAlg= cribAlgMap.get(blueCribChoices.getValue());
        PeggingAlgorithm pegAlg= pegAlgMap.get(bluePegChoices.getValue());

        return new CribPlayer(cribAlg,pegAlg);
    }

    public CribPlayer getDealer(){
        if(dealerRadio.getSelectedToggle() == redDealer){
            return getRedPlayer();
        }

        return getBluePlayer();
    }

    public CribPlayer getCutter(){
        if(dealerRadio.getSelectedToggle() == blueDealer){
            return getRedPlayer();
        }

        return getBluePlayer();
    }

    public int getDealerScore(){
        if(dealerRadio.getSelectedToggle() == redDealer){
            return Integer.valueOf(redScore.getText());
        }

        return Integer.valueOf(blueScore.getText());
    }

    public int getCutterScore(){
        if(dealerRadio.getSelectedToggle() == blueDealer){
            return Integer.valueOf(redScore.getText());
        }

        return Integer.valueOf(blueScore.getText());
    }

    public void setDealerWinsText(String text){
        if(dealerRadio.getSelectedToggle() == redDealer){
            redWins.setText(text);
        }
        else{
            blueWins.setText(text);
        }
    }

    public void setCutterWinsText(String text){
        if(dealerRadio.getSelectedToggle() == blueDealer){
            redWins.setText(text);
        }
        else{
            blueWins.setText(text);
        }
    }
}
