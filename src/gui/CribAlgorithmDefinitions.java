package src.gui;

import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class CribAlgorithmDefinitions extends Stage {
    public CribAlgorithmDefinitions(){
        super();
        setTitle("Crib Selection Algorithms");

        TextFlow flow= new TextFlow();

        Scene scene= new Scene(flow);

        setScene(scene);

        Text randomHeader= new Text("Random:\n");
        Text randomDefinition= new Text("Chooses two random cards to put in the crib.\n\n");

        Text highFourHeader= new Text("High-Four:\n");
        Text highFourDefinition= new Text("Finds the four cards that give it the highest score.\n" +
                "Puts the other two in the crib.\n" +
                "If two or more four-card groups have the same score, one is randomly selected.\n\n");

        Text highSixHeader= new Text("High-Six:\n");
        Text highSixDefinition= new Text("Alteration of high-four which also considers the two it puts in the crib.\n" +
                "The score of these two cards are counted toward the best hand is the player is dealer,\n" +
                "and counted against otherwise.\n\n");

        Text evHeader= new Text("EV:\n");
        Text evDefinition= new Text("Alteration of high-six which also considers possible cuts.\n" +
                "Looks at each possible cut to determine the cards it should keep in order to\n" +
                "maximize the expected value of its hand.\n\n");

        flow.getChildren().add(randomHeader);
        flow.getChildren().add(randomDefinition);
        flow.getChildren().add(highFourHeader);
        flow.getChildren().add(highFourDefinition);
        flow.getChildren().add(highSixHeader);
        flow.getChildren().add(highSixDefinition);
        flow.getChildren().add(evHeader);
        flow.getChildren().add(evDefinition);
    }
}
