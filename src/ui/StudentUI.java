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

import dao.StudentDAO;
import model.Student;
import java.util.ArrayList;

public class StudentUI {

    private ObservableList<Student> studentList = FXCollections.observableArrayList();
    private StudentDAO studentDAO = new StudentDAO();

    public Scene getScene() {
        
        BorderPane mainPane = new BorderPane();
        mainPane.setPadding(new Insets(15));

        // --- 1. HEADER ---
        Label title = new Label("Student Management");
        title.setFont(new Font("Arial", 20));
        BorderPane.setAlignment(title, Pos.CENTER);
        mainPane.setTop(title);

        // --- 2. TABLE VIEW ---
        TableView<Student> table = new TableView<>();
        table.setItems(studentList);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); 
        BorderPane.setMargin(table, new Insets(10, 0, 10, 0));
        
        TableColumn<Student, String> colId = new TableColumn<>("Student ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colId.setPrefWidth(100);

        TableColumn<Student, String> colName = new TableColumn<>("Full Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colName.setPrefWidth(200);

        TableColumn<Student, String> colCourse = new TableColumn<>("Course");
        colCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        colCourse.setPrefWidth(100);

        TableColumn<Student, String> colYear = new TableColumn<>("Year Level");
        colYear.setCellValueFactory(new PropertyValueFactory<>("yearLevel"));
        colYear.setPrefWidth(80);

        TableColumn<Student, String> colEmail = new TableColumn<>("Email Address");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmail.setPrefWidth(150);

        table.getColumns().addAll(colId, colName, colCourse, colYear, colEmail);
        mainPane.setCenter(table);
        
        refreshStudentList();

        // --- 3. INPUT FORM ---
        VBox formPane = new VBox(10);
        formPane.setAlignment(Pos.CENTER);
        
        HBox inputRow1 = new HBox(10);
        inputRow1.setAlignment(Pos.CENTER);
        TextField idInput = new TextField();
        idInput.setPromptText("Student ID (e.g., S-001)");
        TextField nameInput = new TextField();
        nameInput.setPromptText("Full Name");
        inputRow1.getChildren().addAll(idInput, nameInput);

        HBox inputRow2 = new HBox(10);
        inputRow2.setAlignment(Pos.CENTER);
        TextField courseInput = new TextField();
        courseInput.setPromptText("Course");
        TextField yearInput = new TextField();
        yearInput.setPromptText("Year Level");
        TextField emailInput = new TextField();
        emailInput.setPromptText("Email Address");
        inputRow2.getChildren().addAll(courseInput, yearInput, emailInput);

        HBox buttonRow = new HBox(10);
        buttonRow.setAlignment(Pos.CENTER);
        Button btnAdd = new Button("Add Student");
        Button btnDelete = new Button("Delete Selected");
        buttonRow.getChildren().addAll(btnAdd, btnDelete);

        formPane.getChildren().addAll(inputRow1, inputRow2, buttonRow);
        mainPane.setBottom(formPane);

        // --- 4. DATABASE EVENT HANDLING ---
        btnAdd.setOnAction(e -> {
            if (idInput.getText().trim().isEmpty() || nameInput.getText().trim().isEmpty()) {
                System.out.println("Error: Student ID and Name cannot be empty!");
                return;
            }

            Student newStudent = new Student(
                idInput.getText(), nameInput.getText(), courseInput.getText(),
                yearInput.getText(), emailInput.getText()
            );

            if (studentDAO.addStudent(newStudent)) {
                idInput.clear(); nameInput.clear(); courseInput.clear();
                yearInput.clear(); emailInput.clear();
                refreshStudentList();
            }
        });

        btnDelete.setOnAction(e -> {
            ObservableList<Student> selectedItems = table.getSelectionModel().getSelectedItems();
            for (Student s : selectedItems) {
                studentDAO.deleteStudent(s.getStudentId());
            }
            refreshStudentList();
        });

        return new Scene(mainPane, 700, 500);
    }

    private void refreshStudentList() {
        studentList.clear(); 
        ArrayList<Student> dbStudents = studentDAO.getAllStudents();
        studentList.addAll(dbStudents); // Direktang naisasalin ang buong listahan dahil pareho silang objects
    }
}