package src.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class CribBoardCanvas extends Canvas {
    GraphicsContext gc;

    CribBoardCanvas(){
        super(200,200);
        gc= getGraphicsContext2D();
        drawSomething();
    }

    private void drawSomething(){
        gc.fillRect(50,50,50,50);
    }
}
