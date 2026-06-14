package ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AttendanceUI {

    public VBox getView() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));

        Label title = new Label("Daily Attendance Tracking");
        title.setFont(Font.font("System", FontWeight.BOLD, 24));

        VBox card = new VBox(15);
        card.getStyleClass().add("card");
        VBox.setVgrow(card, Priority.ALWAYS);

        HBox topControls = new HBox(15);
        ComboBox<String> classDropdown = new ComboBox<>();
        classDropdown.setPromptText("Select Class");
        
        DatePicker datePicker = new DatePicker();
        
        Button btnStart = new Button("Load Session");
        btnStart.getStyleClass().add("primary-button");

        topControls.getChildren().addAll(classDropdown, datePicker, btnStart);

     // Table setup
        TableView table = new TableView();
        VBox.setVgrow(table, Priority.ALWAYS);

        TableColumn<String, String> colStudId = new TableColumn<>("STUDENT ID");
        TableColumn<String, String> colFullName = new TableColumn<>("FULL NAME");
        TableColumn<String, String> colTime = new TableColumn<>("TIMESTAMP");
        TableColumn<String, String> colStatus = new TableColumn<>("STATUS");

        // Responsive Column Widths
        colStudId.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        colFullName.prefWidthProperty().bind(table.widthProperty().multiply(0.40));
        colTime.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        colStatus.prefWidthProperty().bind(table.widthProperty().multiply(0.19));

        table.getColumns().addAll(colStudId, colFullName, colTime, colStatus);

        HBox bottomControls = new HBox(10);
        Button btnPresent = new Button("Mark Present");
        btnPresent.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-weight: bold;");
        
        Button btnAbsent = new Button("Mark Absent");
        btnAbsent.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-weight: bold;");

        Button btnEnd = new Button("End Session & Save");
        btnEnd.getStyleClass().add("primary-button");

        bottomControls.getChildren().addAll(btnPresent, btnAbsent, new Region(), btnEnd);
        HBox.setHgrow(bottomControls.getChildren().get(2), Priority.ALWAYS); // Push End button to right

        card.getChildren().addAll(topControls, table, bottomControls);
        layout.getChildren().addAll(title, card);

        return layout;
    }
}