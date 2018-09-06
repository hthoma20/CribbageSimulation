package src.gui;

import javax.swing.*;

public class MainFrame {
    private JFrame window;

    public static void main(String[] args){
        MainFrame frame= new MainFrame();
        frame.showWindow();
    }

    public MainFrame(){
        createWindow();
    }

    private void createWindow(){
        this.window= new JFrame("Cribbage Simulation");
        this.window.setSize(500,500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showWindow(){
        window.setVisible(true);
    }
}
