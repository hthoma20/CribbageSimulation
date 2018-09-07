package src.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    private ControlPanel controlPanel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Group root= new Group();

        controlPanel= new ControlPanel();

        root.getChildren().add(controlPanel);

        Scene scene= new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
