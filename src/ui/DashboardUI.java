package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DashboardUI {
    private BorderPane mainLayout;
    private StackPane contentArea;
    private VBox sidebar;
    private Button currentActiveBtn; // Para ma-track ang dilaw na highlight

    public Scene getScene() {
        mainLayout = new BorderPane();
        mainLayout.getStyleClass().add("root");
        
        // --- TOP BAR (Optional, pang pa-ganda) ---
        HBox topBar = new HBox();
        topBar.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #E2E8F0; -fx-border-width: 0 0 1 0; -fx-padding: 15 30;");
        Label breadcrumb = new Label("Dashboard / Overview");
        breadcrumb.setStyle("-fx-font-weight: bold; -fx-text-fill: #1E293B;");
        topBar.getChildren().add(breadcrumb);
        mainLayout.setTop(topBar);

        // --- SIDEBAR ---
        sidebar = new VBox(5);
        sidebar.getStyleClass().add("sidebar");
        sidebar.setPrefWidth(220);
        sidebar.setPadding(new Insets(20, 15, 20, 15));

        Label brand = new Label("ACE Admin");
        brand.setFont(Font.font("System", FontWeight.BOLD, 20));
        brand.setStyle("-fx-text-fill: #1E293B; -fx-padding: 0 0 30 5;");

        Button btnDashboard = createSidebarBtn("Dashboard");
        Button btnStudent = createSidebarBtn("Student Directory");
        Button btnClass = createSidebarBtn("Class Schedules");
        Button btnEnroll = createSidebarBtn("Enrollments");
        Button btnAttendance = createSidebarBtn("Attendance Log");
        Button btnReport = createSidebarBtn("Reports");

        sidebar.getChildren().addAll(brand, btnDashboard, btnStudent, btnClass, btnEnroll, btnAttendance, btnReport);
        mainLayout.setLeft(sidebar);

        // --- CONTENT AREA ---
        contentArea = new StackPane();
        contentArea.setPadding(new Insets(30));
        mainLayout.setCenter(contentArea);

        // --- NAVIGATION LOGIC ---
        btnDashboard.setOnAction(e -> { setActiveBtn(btnDashboard); switchContent(getStatsView()); });
        btnStudent.setOnAction(e -> { setActiveBtn(btnStudent); switchContent(new StudentUI().getView()); });
        btnClass.setOnAction(e -> { setActiveBtn(btnClass); switchContent(new ClassUI().getView()); });
        btnEnroll.setOnAction(e -> { setActiveBtn(btnEnroll); switchContent(new EnrollmentUI().getView()); });
        btnAttendance.setOnAction(e -> { setActiveBtn(btnAttendance); switchContent(new AttendanceUI().getView()); });
        btnReport.setOnAction(e -> { setActiveBtn(btnReport); switchContent(new ReportUI().getView()); });

        // Default Load
        setActiveBtn(btnDashboard);
        switchContent(getStatsView());

        Scene scene = new Scene(mainLayout, 1200, 750);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        return scene;
    }

    private Button createSidebarBtn(String text) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.getStyleClass().add("sidebar-button");
        return btn;
    }

    private void setActiveBtn(Button btn) {
        if(currentActiveBtn != null) {
            currentActiveBtn.getStyleClass().remove("sidebar-button-active");
            currentActiveBtn.getStyleClass().add("sidebar-button");
        }
        btn.getStyleClass().remove("sidebar-button");
        btn.getStyleClass().add("sidebar-button-active");
        currentActiveBtn = btn;
    }

    private void switchContent(Pane view) {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(view);
    }

    // --- THE STATS DASHBOARD (Eksaktong gaya ng reference) ---
    private VBox getStatsView() {
        VBox layout = new VBox(25);

        // Header
        HBox header = new HBox();
        VBox texts = new VBox(5);
        Label title = new Label("Welcome back, Admin");
        title.setFont(Font.font("System", FontWeight.BOLD, 26));
        title.setStyle("-fx-text-fill: #1E293B;");
        Label subtitle = new Label("Here's an overview of ACE University's Academic Performance.");
        subtitle.setStyle("-fx-text-fill: #64748B;");
        texts.getChildren().addAll(title, subtitle);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button btnReport = new Button("+ Generate Term Report");
        btnReport.getStyleClass().add("primary-button");
        
        header.getChildren().addAll(texts, spacer, btnReport);
        header.setAlignment(Pos.CENTER_LEFT);

        // 4 Stat Cards Row
        HBox statsRow = new HBox(20);
        statsRow.getChildren().addAll(
            createStatCard("TOTAL STUDENTS", "2,842"),
            createStatCard("AVG. ATTENDANCE", "94.8%"),
            createStatCard("ACTIVE CLASSES", "124"),
            createStatCard("PENDING REPORTS", "18")
        );

        layout.getChildren().addAll(header, statsRow);
        return layout;
    }

    private VBox createStatCard(String titleText, String valueText) {
        VBox card = new VBox(10);
        card.getStyleClass().add("stat-card");
        HBox.setHgrow(card, Priority.ALWAYS); // Makes all cards equal width
        
        Label title = new Label(titleText);
        title.getStyleClass().add("stat-title");
        
        Label value = new Label(valueText);
        value.getStyleClass().add("stat-value");

        card.getChildren().addAll(title, value);
        return card;
    }
}