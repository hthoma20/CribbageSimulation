package src.gui;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        HBox root= new HBox();
        root.setSpacing(20);

        ControlPanel controlPanel= new ControlPanel();
        RunBox runBox= new RunBox(controlPanel);

        root.getChildren().add(controlPanel);
        root.getChildren().add(runBox);

        //draw borders
        controlPanel.setStyle("-fx-border-color: black;"+
                              "-fx-border-width: 3;");

        Scene scene= new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Cribbage Simulator");
        stage.show();
    }
}
