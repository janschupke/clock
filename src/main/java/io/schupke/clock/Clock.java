package io.schupke.clock;

public class Clock {
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();

        try {
            mainWindow.render();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 
