// Sa loob ng iyong Main.java
package app;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.LoginUI;
import ui.DashboardUI;

public class Main extends Application {
    private Stage window;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("ACE University Portal");
        
        // Load Login First
        LoginUI login = new LoginUI();
        window.setScene(login.getScene(this)); // Pinasa natin 'this' para ma-access ang loadDashboard
        window.centerOnScreen();
        window.show();
    }

    public void loadDashboard() {
        DashboardUI dashboard = new DashboardUI();
        window.setScene(dashboard.getScene());
        window.centerOnScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}