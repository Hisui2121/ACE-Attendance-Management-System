package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import dao.EnrollmentDAO;
import dao.ClassDAO;
import database.DBConnect;
import model.Enrollment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EnrollmentUI {

    // Gumamit tayo ng custom display class para sa malinis na table
    private ObservableList<EnrollmentDisplay> enrollmentList = FXCollections.observableArrayList();
    private EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    private ClassDAO classDAO = new ClassDAO();

    public Scene getScene() {
        BorderPane mainPane = new BorderPane();
        mainPane.setPadding(new Insets(15));

        Label title = new Label("Enrollment Management");
        title.setFont(new Font("Arial", 20));
        BorderPane.setAlignment(title, Pos.CENTER);
        mainPane.setTop(title);

        // --- TABLE VIEW ---
        TableView<EnrollmentDisplay> table = new TableView<>();
        table.setItems(enrollmentList);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        BorderPane.setMargin(table, new Insets(15, 0, 15, 0));

        // Column 1: Student (Pinagsamang ID at Pangalan)
        TableColumn<EnrollmentDisplay, String> colStudent = new TableColumn<>("Student");
        colStudent.setCellValueFactory(new PropertyValueFactory<>("studentInfo"));
        colStudent.setPrefWidth(250);

        // Column 2: Class (Pinagsamang Code at Name)
        TableColumn<EnrollmentDisplay, String> colClass = new TableColumn<>("Enrolled Subject");
        colClass.setCellValueFactory(new PropertyValueFactory<>("classInfo"));
        colClass.setPrefWidth(250);

        table.getColumns().addAll(colStudent, colClass);
        mainPane.setCenter(table);

        refreshEnrollmentList();

        // --- INPUT FORM ---
        VBox formPane = new VBox(10);
        formPane.setAlignment(Pos.CENTER);

        HBox inputRow = new HBox(10);
        inputRow.setAlignment(Pos.CENTER);
        TextField studentIdInput = new TextField();
        studentIdInput.setPromptText("Student ID");
        
        ComboBox<String> classDropdown = new ComboBox<>();
        classDropdown.setPromptText("Select a Class");
        classDropdown.setPrefWidth(250);
        
        ArrayList<model.Class> classes = classDAO.getAllClasses();
        for (model.Class c : classes) {
            classDropdown.getItems().add(c.getClassCode() + " | " + c.getClassName());
        }

        inputRow.getChildren().addAll(studentIdInput, classDropdown);

        HBox buttonRow = new HBox(10);
        buttonRow.setAlignment(Pos.CENTER);
        Button btnEnroll = new Button("Enroll Student");
        Button btnRemove = new Button("Remove Selected");
        buttonRow.getChildren().addAll(btnEnroll, btnRemove);

        formPane.getChildren().addAll(inputRow, buttonRow);
        mainPane.setBottom(formPane);

        // --- EVENT HANDLERS ---
        btnEnroll.setOnAction(e -> {
            if (studentIdInput.getText().trim().isEmpty() || classDropdown.getValue() == null) {
                showAlert("Input Error", "Student ID and Class selection cannot be empty!");
                return; 
            }

            String classCode = classDropdown.getValue().split(" \\| ")[0];
            int classId = classDAO.getClassIdByCode(classCode);

            if (classId != -1) {
                Enrollment enrollment = new Enrollment(studentIdInput.getText(), classId);
                if (enrollmentDAO.enrollStudent(enrollment)) {
                    studentIdInput.clear(); 
                    classDropdown.getSelectionModel().clearSelection();
                    refreshEnrollmentList();
                }
            } else {
                showAlert("Not Found", "Class code not found in the database!");
            }
        });

        btnRemove.setOnAction(e -> {
            for (EnrollmentDisplay en : table.getSelectionModel().getSelectedItems()) {
                enrollmentDAO.removeEnrollment(en.getEnrollId());
            }
            refreshEnrollmentList();
        });

        return new Scene(mainPane, 550, 450);
    }

    // Kinukuha at pinagsasama ang pangalan ng estudyante at klase
    private void refreshEnrollmentList() {
        enrollmentList.clear();
        String sql = "SELECT e.id, e.student_id, s.full_name, c.class_code, c.class_name " +
                     "FROM enrollments e " +
                     "JOIN students s ON e.student_id = s.student_id " +
                     "JOIN classes c ON e.class_id = c.id";

        try (Connection conn = DBConnect.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String student = rs.getString("student_id") + " - " + rs.getString("full_name");
                String subject = rs.getString("class_code") + " - " + rs.getString("class_name");
                
                enrollmentList.add(new EnrollmentDisplay(id, student, subject));
            }
        } catch (Exception e) {
            System.out.println("Error loading enrollments: " + e.getMessage());
        }
    }
    
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Custom Data Model para sa malinis na Enrollment Table
    public static class EnrollmentDisplay {
        private int enrollId;
        private String studentInfo;
        private String classInfo;

        public EnrollmentDisplay(int enrollId, String studentInfo, String classInfo) {
            this.enrollId = enrollId;
            this.studentInfo = studentInfo;
            this.classInfo = classInfo;
        }

        public int getEnrollId() { return enrollId; } // Gagamitin lang natin for deleting sa background
        public String getStudentInfo() { return studentInfo; }
        public String getClassInfo() { return classInfo; }
    }
}