package database;

import java.sql.Connection;
import java.sql.Statement;

public class DBInitialize {

    public static void initialize() {

        try {

            Connection conn = DBConnect.connect();
            Statement stmt = conn.createStatement();
            
            // STUDENTS TABLE
            
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS students ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "student_id TEXT UNIQUE NOT NULL,"
                + "full_name TEXT NOT NULL,"
                + "course TEXT,"
                + "year_level TEXT,"
                + "email TEXT"
                + ");"
            );

            
            // TEACHERS TABLE
            
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS teachers ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "teacher_id TEXT UNIQUE NOT NULL,"
                + "full_name TEXT NOT NULL,"
                + "email TEXT"
                + ");"
            );

            
            // CLASSES TABLE (NEW CORE TABLE)
            
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS classes ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "class_code TEXT UNIQUE NOT NULL,"
                + "class_name TEXT NOT NULL,"
                + "teacher_id TEXT NOT NULL,"
                + "schedule TEXT,"
                + "room TEXT,"
                + "FOREIGN KEY (teacher_id) REFERENCES teachers(teacher_id)"
                + ");"
            );

            // ENROLLMENTS TABLE
            
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS enrollments ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "student_id TEXT NOT NULL,"
                + "class_id INTEGER NOT NULL,"
                + "FOREIGN KEY (student_id) REFERENCES students(student_id),"
                + "FOREIGN KEY (class_id) REFERENCES classes(id)"
                + ");"
            );

            
            // ATTENDANCE SESSIONS
            
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS attendance_sessions ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "class_id INTEGER NOT NULL,"
                + "session_date TEXT NOT NULL,"
                + "created_by TEXT,"
                + "FOREIGN KEY (class_id) REFERENCES classes(id)"
                + ");"
            );

            
            // ATTENDANCE RECORDS
            
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS attendance_records ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "session_id INTEGER NOT NULL,"
                + "student_id TEXT NOT NULL,"
                + "status TEXT NOT NULL,"
                + "FOREIGN KEY (session_id) REFERENCES attendance_sessions(id),"
                + "FOREIGN KEY (student_id) REFERENCES students(student_id)"
                + ");"
            );

            System.out.println("Database initialized successfully!");

        } catch (Exception e) {

            System.out.println("DB Init Error: " + e.getMessage());
        }
    }
}