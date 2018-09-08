package src.gui;

import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class PegAlgorithmDefinitions extends Stage {
    public PegAlgorithmDefinitions(){
        super();
        setTitle("Pegging Algorithms");

        TextFlow flow= new TextFlow();

        Scene scene= new Scene(flow);

        setScene(scene);

        Text randomHeader= new Text("Random:\n");
        Text randomDefinition= new Text("Randomly selects a valid play.\n\n");

        Text highPlayHeader= new Text("High Play:\n");
        Text highPlayDefinition= new Text("Plays the card which will give it the highest score.\n" +
                "Does not look further ahead than the current play.\n");


        flow.getChildren().add(randomHeader);
        flow.getChildren().add(randomDefinition);
        flow.getChildren().add(highPlayHeader);
        flow.getChildren().add(highPlayDefinition);
    }
}
