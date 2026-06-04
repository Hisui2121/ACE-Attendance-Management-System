package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.DBConnect;
import model.AttendanceRecord;

public class AttendanceRecordDAO {

    // =====================================
    // GENERATE ATTENDANCE SHEET
    // =====================================
    public boolean generateAttendanceSheet(
            int sessionId,
            int classId) {

        String enrollmentQuery =
            "SELECT student_id FROM enrollments "
          + "WHERE class_id = ?";

        String insertQuery =
            "INSERT INTO attendance_records "
          + "(session_id, student_id, status) "
          + "VALUES (?, ?, ?)";

        try {

            Connection conn = DBConnect.connect();

            // GET ENROLLED STUDENTS
            PreparedStatement getStudents =
                    conn.prepareStatement(enrollmentQuery);

            getStudents.setInt(1, classId);

            ResultSet rs =
                    getStudents.executeQuery();

            // CREATE DEFAULT RECORDS
            while (rs.next()) {

                String studentId =
                        rs.getString("student_id");
                
                System.out.println("Loaded student: " + studentId);

                PreparedStatement insertRecord =
                        conn.prepareStatement(insertQuery);

                insertRecord.setInt(1, sessionId);

                insertRecord.setString(2, studentId);

                // DEFAULT STATUS
                insertRecord.setString(3, "Pending");

                insertRecord.executeUpdate();
            }

            System.out.println(
                "Attendance sheet generated!"
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                "Generate sheet error: "
                + e.getMessage()
            );

            return false;
        }
    }

    // =====================================
    // UPDATE ATTENDANCE STATUS
    // =====================================
    public boolean updateAttendanceStatus(
            int sessionId,
            String studentId,
            String status) {

        String sql =
            "UPDATE attendance_records "
          + "SET status=? "
          + "WHERE session_id=? "
          + "AND student_id=?";

        try {

            Connection conn = DBConnect.connect();

            PreparedStatement pstmt =
                    conn.prepareStatement(sql);

            pstmt.setString(1, status);

            pstmt.setInt(2, sessionId);

            pstmt.setString(3, studentId);

            pstmt.executeUpdate();

            return true;

        } catch (Exception e) {

            System.out.println(
                "Update attendance error: "
                + e.getMessage()
            );

            return false;
        }
    }

    // =====================================
    // VIEW ATTENDANCE SHEET
    // =====================================
    public ArrayList<AttendanceRecord>
    getAttendanceSheet(int sessionId) {
	
	    ArrayList<AttendanceRecord> list =
	            new ArrayList<>();
	
	    String sql =
	        "SELECT ar.id, "
	      + "ar.session_id, "
	      + "ar.student_id, "
	      + "s.full_name, "
	      + "ar.status "
	      + "FROM attendance_records ar "
	      + "JOIN students s "
	      + "ON ar.student_id = s.student_id "
	      + "WHERE ar.session_id=?";
	
	    try {
	
	        Connection conn = DBConnect.connect();
	
	        PreparedStatement pstmt =
	                conn.prepareStatement(sql);
	
	        pstmt.setInt(1, sessionId);
	
	        ResultSet rs =
	                pstmt.executeQuery();
	
	        while (rs.next()) {
	
	            AttendanceRecord record =
	                    new AttendanceRecord();
	
	            record.setId(
	                rs.getInt("id")
	            );
	
	            record.setSessionId(
	                rs.getInt("session_id")
	            );
	
	            record.setStudentId(
	                rs.getString("student_id")
	            );
	
	            // NEW
	            record.setFullName(
	                rs.getString("full_name")
	            );
	
	            record.setStatus(
	                rs.getString("status")
	            );
	
	            list.add(record);
	        }
	
	    } catch (Exception e) {
	
	        System.out.println(
	            "Get sheet error: "
	            + e.getMessage()
	        );
	    }
	
	    return list;
	}
    
    public void viewAttendanceSheet(int sessionId) {

        String sql =
            "SELECT ar.student_id, "
          + "s.full_name, "
          + "ar.status "
          + "FROM attendance_records ar "
          + "JOIN students s "
          + "ON ar.student_id = s.student_id "
          + "WHERE ar.session_id = ?";

        try {

            Connection conn = DBConnect.connect();

            PreparedStatement pstmt =
                    conn.prepareStatement(sql);

            pstmt.setInt(1, sessionId);

            ResultSet rs =
                    pstmt.executeQuery();

            System.out.println(
                "\n===== ATTENDANCE SHEET ====="
            );

            System.out.println(
                "Session ID: " + sessionId
            );

            System.out.println(
                "----------------------------------------"
            );

            while (rs.next()) {

                String studentId =
                        rs.getString("student_id");

                String fullName =
                        rs.getString("full_name");

                String status =
                        rs.getString("status");

                System.out.println(
                        studentId + " | "
                      + fullName + " | "
                      + status
                );
            }

        } catch (Exception e) {

            System.out.println(
                "View sheet error: "
                + e.getMessage()
            );
        }
    }
    
    public ArrayList<AttendanceRecord> getAttendanceSheetWithDetails(int sessionId) {

        ArrayList<AttendanceRecord> list = new ArrayList<>();

        String sql =
            "SELECT ar.student_id, s.full_name, ar.status " +
            "FROM attendance_records ar " +
            "JOIN students s ON ar.student_id = s.student_id " +
            "WHERE ar.session_id = ?";

        try {
            Connection conn = DBConnect.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, sessionId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                AttendanceRecord record = new AttendanceRecord();
                record.setStudentId(rs.getString("student_id"));
                record.setFullName(rs.getString("full_name"));
                record.setStatus(rs.getString("status"));
                list.add(record);
            }

        } catch (Exception e) {
            System.out.println("Get sheet error: " + e.getMessage());
        }

        return list;
    }
}