package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DashboardUI {

    public Scene getMainScene() {
        
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);

        Label title = new Label("Prescent Attendance System");
        title.setFont(new Font("Arial", 24));

        Button btnStudent = new Button("1. Student Management");
        Button btnClass = new Button("2. Class Management");
        Button btnAttendance = new Button("3. Attendance Management");
        Button btnEnrollment = new Button("4. Enrollment Management");
        Button btnReports = new Button("5. Reports");

        btnStudent.setPrefWidth(250);
        btnClass.setPrefWidth(250);
        btnAttendance.setPrefWidth(250);
        btnEnrollment.setPrefWidth(250);
        btnReports.setPrefWidth(250);

        // ==========================================
        // ADDING ACTIONS TO BUTTONS (EVENTS)
        // ==========================================
        btnStudent.setOnAction(e -> openStudentModule());
        btnClass.setOnAction(e -> openClassModule());
        btnAttendance.setOnAction(e -> openAttendanceModule());
        btnEnrollment.setOnAction(e -> openEnrollmentModule());
        btnReports.setOnAction(e -> openReportModule());

        root.getChildren().addAll(
            title, 
            btnStudent, 
            btnClass, 
            btnAttendance, 
            btnEnrollment,
            btnReports
        );

        return new Scene(root, 600, 500);
    }

    private void openStudentModule() {
        StudentUI studentUI = new StudentUI();
        Stage stage = new Stage(); 
        stage.setTitle("Student Management");
        stage.setScene(studentUI.getScene());
        stage.show();
    }

    private void openClassModule() {
        ClassUI classUI = new ClassUI();
        Stage stage = new Stage();
        stage.setTitle("Class Management");
        stage.setScene(classUI.getScene());
        stage.show();
    }
    
    private void openAttendanceModule() {
        AttendanceUI attendanceUI = new AttendanceUI();
        Stage stage = new Stage(); 
        stage.setTitle("Attendance Management");
        stage.setScene(attendanceUI.getScene());
        stage.show();
    }
    
    private void openEnrollmentModule() {
        EnrollmentUI enrollmentUI = new EnrollmentUI();
        Stage stage = new Stage();
        stage.setTitle("Enrollment Management");
        stage.setScene(enrollmentUI.getScene());
        stage.show();
    }
    
    private void openReportModule() {
        ReportUI reportUI = new ReportUI();
        Stage stage = new Stage();
        stage.setTitle("Report Generation");
        stage.setScene(reportUI.getScene());
        stage.show();
    }
}