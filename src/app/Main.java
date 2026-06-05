package app;

import javafx.application.Application;
import javafx.stage.Stage;
import database.DBInitialize;
import ui.DashboardUI; // Import ang bagong UI file mo

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // I-call ang bagong DashboardUI class
            DashboardUI dashboard = new DashboardUI();
            
            primaryStage.setTitle("Prescent Attendance System");
            
            // Kunin ang Scene mula sa DashboardUI at i-set sa Stage
            primaryStage.setScene(dashboard.getMainScene());
            
            primaryStage.show();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // I-initialize ang database bago mag-load ang UI
        DBInitialize.initialize();
        
        // I-launch ang JavaFX application
        launch(args);
    }
}