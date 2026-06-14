package ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EnrollmentUI {

    public VBox getView() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));

        Label title = new Label("Enrollment Management");
        title.setFont(Font.font("System", FontWeight.BOLD, 24));
        Label subtitle = new Label("Oversee student course registrations and academic status.");
        subtitle.setStyle("-fx-text-fill: #64748B;");

        VBox headerBox = new VBox(5);
        headerBox.getChildren().addAll(title, subtitle);

        VBox card = new VBox(15);
        card.getStyleClass().add("card");
        VBox.setVgrow(card, Priority.ALWAYS);

        // Inputs for Enrollment
        HBox inputRow = new HBox(15);
        ComboBox<String> studentCombo = new ComboBox<>();
        studentCombo.setPromptText("Select Student");
        studentCombo.setPrefWidth(200);

        ComboBox<String> classCombo = new ComboBox<>();
        classCombo.setPromptText("Select Class");
        classCombo.setPrefWidth(200);

        Button btnEnroll = new Button("+ New Enrollment");
        btnEnroll.getStyleClass().add("primary-button");

        inputRow.getChildren().addAll(studentCombo, classCombo, btnEnroll);

     // Table setup
        TableView table = new TableView();
        VBox.setVgrow(table, Priority.ALWAYS);

        TableColumn<String, String> colEnrId = new TableColumn<>("ENROLLMENT ID");
        TableColumn<String, String> colStudentName = new TableColumn<>("STUDENT NAME");
        TableColumn<String, String> colClassCode = new TableColumn<>("CLASS CODE");
        TableColumn<String, String> colDate = new TableColumn<>("DATE ENROLLED");
        TableColumn<String, String> colStatus = new TableColumn<>("STATUS");

        // Responsive Column Widths
        colEnrId.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        colStudentName.prefWidthProperty().bind(table.widthProperty().multiply(0.30));
        colClassCode.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        colDate.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        colStatus.prefWidthProperty().bind(table.widthProperty().multiply(0.14));

        table.getColumns().addAll(colEnrId, colStudentName, colClassCode, colDate, colStatus);

        // Bottom Controls
        HBox bottomControls = new HBox(10);
        Button btnRemove = new Button("Remove Selected");
        btnRemove.getStyleClass().add("secondary-button");
        bottomControls.getChildren().add(btnRemove);

        card.getChildren().addAll(inputRow, table, bottomControls);
        layout.getChildren().addAll(headerBox, card);

        return layout;
    }
}