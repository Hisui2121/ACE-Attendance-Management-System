package ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
// Import your model and DAO here
// import model.Student;
// import dao.StudentDAO;

public class StudentUI {

    public VBox getView() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));

        Label title = new Label("Student Directory");
        title.setFont(Font.font("System", FontWeight.BOLD, 24));

        VBox card = new VBox(15);
        card.getStyleClass().add("card");
        VBox.setVgrow(card, Priority.ALWAYS); // Hahayaan nitong lumaki ang card

     // Table setup
        TableView table = new TableView();
        VBox.setVgrow(table, Priority.ALWAYS);

        TableColumn<String, String> colId = new TableColumn<>("STUDENT ID");
        TableColumn<String, String> colName = new TableColumn<>("FULL NAME");
        TableColumn<String, String> colCourse = new TableColumn<>("COURSE / PROGRAM");
        TableColumn<String, String> colYear = new TableColumn<>("YEAR LEVEL");
        TableColumn<String, String> colStatus = new TableColumn<>("STATUS");

        // Responsive Column Widths
        colId.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        colName.prefWidthProperty().bind(table.widthProperty().multiply(0.30));
        colCourse.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        colYear.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        colStatus.prefWidthProperty().bind(table.widthProperty().multiply(0.14));

        table.getColumns().addAll(colId, colName, colCourse, colYear, colStatus);

        // Controls
        HBox controls = new HBox(10);
        Button btnAdd = new Button("Add Student");
        btnAdd.getStyleClass().add("primary-button");
        
        Button btnDelete = new Button("Delete Selected");
        btnDelete.getStyleClass().add("secondary-button");

        controls.getChildren().addAll(btnAdd, btnDelete);

        card.getChildren().addAll(table, controls);
        layout.getChildren().addAll(title, card);

        return layout;
    }
}