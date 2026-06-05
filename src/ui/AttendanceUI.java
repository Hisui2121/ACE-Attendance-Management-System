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

import dao.AttendanceSessionDAO;
import dao.AttendanceRecordDAO;
import dao.ClassDAO;
import model.AttendanceSession;
import model.AttendanceRecord;
import java.time.LocalDate;
import java.util.ArrayList;

public class AttendanceUI {

    // --- STATIC VARIABLES (Eto ang magic word para hindi ma-reset kapag kinlose ang window) ---
    private static int currentSessionId = -1;
    private static String currentSessionText = "Active Session ID: None";

    private ObservableList<AttendanceDisplay> attendanceObservableList = FXCollections.observableArrayList();
    private AttendanceSessionDAO sessionDAO = new AttendanceSessionDAO();
    private AttendanceRecordDAO recordDAO = new AttendanceRecordDAO();
    private ClassDAO classDAO = new ClassDAO();
    
    // Naka-set agad kung ano yung huling text
    private Label lblSessionInfo = new Label(currentSessionText);

    public Scene getScene() {
        BorderPane mainPane = new BorderPane();
        mainPane.setPadding(new Insets(15));

        // --- 1. TOP PANE: SESSION CONTROLS ---
        VBox topPane = new VBox(10);
        topPane.setAlignment(Pos.CENTER);
        
        Label title = new Label("Daily Attendance Tracking");
        title.setFont(new Font("Arial", 20));
        
        HBox sessionControls = new HBox(10);
        sessionControls.setAlignment(Pos.CENTER);
        
        ComboBox<String> classDropdown = new ComboBox<>();
        classDropdown.setPromptText("Select a Class");
        
        ArrayList<model.Class> classes = classDAO.getAllClasses();
        for (model.Class c : classes) {
            classDropdown.getItems().add(c.getClassCode() + " | " + c.getClassName());
        }
        
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());

        Button btnLoad = new Button("Start Session");
        btnLoad.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        
        Button btnEndSession = new Button("End Session");
        btnEndSession.setStyle("-fx-background-color: #607D8B; -fx-text-fill: white;");
        
        sessionControls.getChildren().addAll(classDropdown, datePicker, btnLoad, btnEndSession);
        
        lblSessionInfo.setStyle("-fx-font-weight: bold; -fx-text-fill: #1565C0;");
        topPane.getChildren().addAll(title, sessionControls, lblSessionInfo);
        mainPane.setTop(topPane);

        // --- 2. CENTER PANE: TABLE VIEW ---
        TableView<AttendanceDisplay> table = new TableView<>();
        table.setItems(attendanceObservableList);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        BorderPane.setMargin(table, new Insets(15, 0, 15, 0));
        
        TableColumn<AttendanceDisplay, String> colId = new TableColumn<>("Student ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colId.setPrefWidth(120);

        TableColumn<AttendanceDisplay, String> colName = new TableColumn<>("Full Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colName.setPrefWidth(250);

        TableColumn<AttendanceDisplay, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStatus.setPrefWidth(150);
        
        table.getColumns().addAll(colId, colName, colStatus);
        mainPane.setCenter(table);

        // --- AUTO-LOAD KAPAG BINUKSAN ANG WINDOW ---
        // Kung may nakabukas pang session (hindi -1), i-load agad ang data sa table
        refreshAttendanceTable();

        // --- 3. BOTTOM PANE: MARKING CONTROLS ---
        VBox bottomPane = new VBox(10);
        bottomPane.setAlignment(Pos.CENTER);
        
        HBox markingControls = new HBox(10);
        markingControls.setAlignment(Pos.CENTER);
        Button btnPresent = new Button("Mark Present");
        Button btnAbsent = new Button("Mark Absent");
        Button btnLate = new Button("Mark Late");
        
        btnPresent.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnAbsent.setStyle("-fx-background-color: #F44336; -fx-text-fill: white;");
        btnLate.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");
        markingControls.getChildren().addAll(btnPresent, btnAbsent, btnLate);

        bottomPane.getChildren().addAll(markingControls);
        mainPane.setBottom(bottomPane);

        // --- 4. EVENT HANDLERS ---
        btnLoad.setOnAction(e -> {
            if (currentSessionId != -1) {
                showAlert("Active Session", "Please End the current session first before starting a new one.");
                return;
            }
            if (classDropdown.getValue() == null) {
                showAlert("Input Error", "Please select a class from the dropdown!");
                return;
            }
            
            String classCode = classDropdown.getValue().split(" \\| ")[0];
            String date = datePicker.getValue().toString();
            int classId = classDAO.getClassIdByCode(classCode);
            
            if (classId != -1) {
                AttendanceSession session = new AttendanceSession(classId, date, "admin");
                currentSessionId = sessionDAO.createSession(session);

                if (currentSessionId != -1) {
                    // I-update ang static text para maalala ng system
                    currentSessionText = "Active Session ID: " + currentSessionId + " (" + classCode + " on " + date + ")";
                    lblSessionInfo.setText(currentSessionText);
                    
                    recordDAO.generateAttendanceSheet(currentSessionId, classId);
                    refreshAttendanceTable();
                } else {
                    showAlert("Database Error", "Failed to create session.");
                }
            }
        });

        btnEndSession.setOnAction(e -> {
            if (currentSessionId != -1) {
                // I-reset ang STATIC variables para malinis
                currentSessionId = -1;
                currentSessionText = "Active Session ID: None";
                lblSessionInfo.setText(currentSessionText);
                attendanceObservableList.clear();
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Session Ended");
                alert.setHeaderText(null);
                alert.setContentText("The attendance session has been successfully closed and saved.");
                alert.showAndWait();
            } else {
                showAlert("No Active Session", "There is no active session to end.");
            }
        });

        btnPresent.setOnAction(e -> updateSelectedStatus(table, "Present"));
        btnAbsent.setOnAction(e -> updateSelectedStatus(table, "Absent"));
        btnLate.setOnAction(e -> updateSelectedStatus(table, "Late"));

        return new Scene(mainPane, 650, 550);
    }

    private void updateSelectedStatus(TableView<AttendanceDisplay> table, String status) {
        if (currentSessionId == -1) return; 

        for (AttendanceDisplay item : table.getSelectionModel().getSelectedItems()) {
            if (item != null) {
                recordDAO.updateAttendanceStatus(currentSessionId, item.getStudentId(), status);
            }
        }
        refreshAttendanceTable();
    }

    private void refreshAttendanceTable() {
        attendanceObservableList.clear();
        if (currentSessionId != -1) {
            ArrayList<AttendanceRecord> records = recordDAO.getAttendanceSheet(currentSessionId);
            for (AttendanceRecord r : records) {
                attendanceObservableList.add(new AttendanceDisplay(r.getStudentId(), r.getFullName(), r.getStatus()));
            }
        }
    }
    
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // --- INNER CLASS PARA SA TABLE VIEW DATA MODEL ---
    public static class AttendanceDisplay {
        private String studentId;
        private String fullName;
        private String status;

        public AttendanceDisplay(String studentId, String fullName, String status) {
            this.studentId = studentId;
            this.fullName = fullName;
            this.status = status;
        }

        public String getStudentId() { return studentId; }
        public String getFullName() { return fullName; }
        public String getStatus() { return status; }
    }
}