package ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ClassUI {

    public VBox getView() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));

        Label title = new Label("Classes Management");
        title.setFont(Font.font("System", FontWeight.BOLD, 24));
        Label subtitle = new Label("Organize academic schedules and assign faculty members.");
        subtitle.setStyle("-fx-text-fill: #64748B;");

        VBox headerBox = new VBox(5);
        headerBox.getChildren().addAll(title, subtitle);

        VBox card = new VBox(15);
        card.getStyleClass().add("card");
        VBox.setVgrow(card, Priority.ALWAYS);

     // Table setup
        TableView table = new TableView();
        VBox.setVgrow(table, Priority.ALWAYS);

        TableColumn<String, String> colCode = new TableColumn<>("CODE");
        TableColumn<String, String> colClassName = new TableColumn<>("CLASS NAME");
        TableColumn<String, String> colTeacher = new TableColumn<>("TEACHER ID");
        TableColumn<String, String> colSched = new TableColumn<>("SCHEDULE");
        TableColumn<String, String> colRoom = new TableColumn<>("ROOM");

        // Responsive Column Widths
        colCode.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        colClassName.prefWidthProperty().bind(table.widthProperty().multiply(0.30));
        colTeacher.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        colSched.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        colRoom.prefWidthProperty().bind(table.widthProperty().multiply(0.14));

        table.getColumns().addAll(colCode, colClassName, colTeacher, colSched, colRoom);

        // Controls
        HBox controls = new HBox(10);
        Button btnAdd = new Button("+ Add Class");
        btnAdd.getStyleClass().add("primary-button");
        
        Button btnDelete = new Button("Delete Selected");
        btnDelete.getStyleClass().add("secondary-button");

        // Spacer to push buttons to the right (optional design choice)
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        controls.getChildren().addAll(spacer, btnDelete, btnAdd);

        card.getChildren().addAll(controls, table);
        layout.getChildren().addAll(headerBox, card);

        return layout;
    }
}