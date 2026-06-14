package ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
// import database.DBConnect;
// import net.sf.jasperreports.engine.*;
// import net.sf.jasperreports.view.JasperViewer;

public class ReportUI {

    public VBox getView() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));

        Label title = new Label("Report Generation");
        title.setFont(Font.font("System", FontWeight.BOLD, 24));

        VBox card = new VBox(20);
        card.getStyleClass().add("card");

        Label subtitle = new Label("Available Templates");
        subtitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        ComboBox<String> reportType = new ComboBox<>();
        reportType.getItems().addAll("Attendance Summary", "Student Masterlist");
        reportType.setValue("Attendance Summary");
        reportType.setPrefWidth(300);

        HBox buttons = new HBox(15);
        Button btnView = new Button("View Report");
        btnView.getStyleClass().add("secondary-button");

        Button btnDownload = new Button("Download PDF");
        btnDownload.getStyleClass().add("primary-button");

        buttons.getChildren().addAll(btnView, btnDownload);

        card.getChildren().addAll(subtitle, new Label("Select Report Type:"), reportType, buttons);
        layout.getChildren().addAll(title, card);

        // TODO: Insert your JasperReports try-catch blocks here for btnView and btnDownload logic

        return layout;
    }
}