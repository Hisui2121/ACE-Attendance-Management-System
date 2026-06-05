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

import dao.ClassDAO;
import model.Class;
import java.util.ArrayList;

public class ClassUI {

    private ObservableList<model.Class> classList = FXCollections.observableArrayList();
    private ClassDAO classDAO = new ClassDAO();

    public Scene getScene() {
        BorderPane mainPane = new BorderPane();
        mainPane.setPadding(new Insets(15));

        Label title = new Label("Class Management");
        title.setFont(new Font("Arial", 20));
        BorderPane.setAlignment(title, Pos.CENTER);
        mainPane.setTop(title);

        // --- TABLE VIEW ---
        TableView<model.Class> table = new TableView<>();
        table.setItems(classList);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        BorderPane.setMargin(table, new Insets(15, 0, 15, 0));

     // Inalis natin ang colId
        TableColumn<model.Class, String> colCode = new TableColumn<>("Class Code");
        colCode.setCellValueFactory(new PropertyValueFactory<>("classCode"));
        colCode.setPrefWidth(100);

        TableColumn<model.Class, String> colName = new TableColumn<>("Class Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("className"));
        colName.setPrefWidth(220);

        TableColumn<model.Class, String> colTeacher = new TableColumn<>("Professor Name");
        colTeacher.setCellValueFactory(new PropertyValueFactory<>("teacherId")); 
        colTeacher.setPrefWidth(150);

        TableColumn<model.Class, String> colSched = new TableColumn<>("Schedule");
        colSched.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        colSched.setPrefWidth(150);

        TableColumn<model.Class, String> colRoom = new TableColumn<>("Room");
        colRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
        colRoom.setPrefWidth(80);

     // I-add ang mga ito lang sa table
        table.getColumns().addAll(colCode, colName, colTeacher, colSched, colRoom);
        
        // Inalis natin ang "ID" column dahil hindi naman ito kailangan makita ng user
        mainPane.setCenter(table);

        refreshClassList();

        // --- INPUT FORM ---
        VBox formPane = new VBox(10);
        formPane.setAlignment(Pos.CENTER);

        HBox inputRow1 = new HBox(10);
        inputRow1.setAlignment(Pos.CENTER);
        TextField codeInput = new TextField();
        codeInput.setPromptText("Class Code");
        TextField nameInput = new TextField();
        nameInput.setPromptText("Class Name");
        TextField teacherInput = new TextField();
        teacherInput.setPromptText("Professor Name"); // Pinalitan din ang prompt dito
        inputRow1.getChildren().addAll(codeInput, nameInput, teacherInput);

        HBox inputRow2 = new HBox(10);
        inputRow2.setAlignment(Pos.CENTER);
        TextField scheduleInput = new TextField();
        scheduleInput.setPromptText("Schedule");
        TextField roomInput = new TextField();
        roomInput.setPromptText("Room");
        inputRow2.getChildren().addAll(scheduleInput, roomInput);

        HBox buttonRow = new HBox(10);
        buttonRow.setAlignment(Pos.CENTER);
        Button btnAdd = new Button("Add Class");
        Button btnDelete = new Button("Delete Selected");
        buttonRow.getChildren().addAll(btnAdd, btnDelete);

        formPane.getChildren().addAll(inputRow1, inputRow2, buttonRow);
        mainPane.setBottom(formPane);

        // --- EVENT HANDLERS ---
        btnAdd.setOnAction(e -> {
            if (codeInput.getText().trim().isEmpty() || nameInput.getText().trim().isEmpty()) return;

            Class newClass = new Class(
                codeInput.getText(), nameInput.getText(), 
                teacherInput.getText(), scheduleInput.getText(), roomInput.getText()
            );

            if (classDAO.addClass(newClass)) {
                codeInput.clear(); nameInput.clear(); teacherInput.clear();
                scheduleInput.clear(); roomInput.clear();
                refreshClassList();
            }
        });

        btnDelete.setOnAction(e -> {
            for (model.Class c : table.getSelectionModel().getSelectedItems()) {
                classDAO.deleteClass(c.getId());
            }
            refreshClassList();
        });

        return new Scene(mainPane, 750, 500);
    }

    private void refreshClassList() {
        classList.clear();
        classList.addAll(classDAO.getAllClasses());
    }
}