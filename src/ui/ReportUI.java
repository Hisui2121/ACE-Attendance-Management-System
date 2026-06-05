package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ReportUI {

    public Scene getScene() {
        VBox mainPane = new VBox(20);
        mainPane.setPadding(new Insets(30));
        mainPane.setAlignment(Pos.CENTER);

        Label title = new Label("Choose Report Type");
        title.setFont(new Font("Arial", 20));

        Button btnStudentReport = new Button("1. Student Masterlist Report");
        Button btnAttendanceReport = new Button("2. Attendance Session Report");

        btnStudentReport.setPrefWidth(250);
        btnAttendanceReport.setPrefWidth(250);

        // Buksan ang hiwalay na UI files
        btnStudentReport.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Student Report Generation");
            stage.setScene(new StudentReportUI().getScene());
            stage.show();
        });

        btnAttendanceReport.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Attendance Report Generation");
            stage.setScene(new AttendanceReportUI().getScene());
            stage.show();
        });

        mainPane.getChildren().addAll(title, btnStudentReport, btnAttendanceReport);
        return new Scene(mainPane, 350, 250);
    }
}