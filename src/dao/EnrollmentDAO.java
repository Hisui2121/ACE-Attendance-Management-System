package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.DBConnect;
import model.Enrollment;

public class EnrollmentDAO {

    // ADD ENROLLMENT
    public boolean enrollStudent(Enrollment enrollment) {
        String sql = "INSERT INTO enrollments (student_id, class_id) VALUES (?, ?)";

        // Gumamit ng try-with-resources para mag-auto close ang connection!
        try (Connection conn = DBConnect.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, enrollment.getStudentId());
            pstmt.setInt(2, enrollment.getClassId());
            pstmt.executeUpdate();

            System.out.println("Student enrolled successfully!");
            return true;

        } catch (Exception e) {
            System.out.println("Enrollment error: " + e.getMessage());
            return false;
        }
    }

    // READ ENROLLMENTS
    public ArrayList<Enrollment> getAllEnrollments() {
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT * FROM enrollments";

        // Gumamit din ng try-with-resources dito para hindi ma-lock ang database matapos basahin
        try (Connection conn = DBConnect.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setId(rs.getInt("id"));
                enrollment.setStudentId(rs.getString("student_id"));
                enrollment.setClassId(rs.getInt("class_id"));
                
                enrollments.add(enrollment);
            }
        } catch (Exception e) {
            System.out.println("Read error: " + e.getMessage());
        }

        return enrollments;
    }

    // DELETE ENROLLMENT
    public boolean removeEnrollment(int id) {
        String sql = "DELETE FROM enrollments WHERE id=?";

        // try-with-resources din dito
        try (Connection conn = DBConnect.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("Enrollment removed successfully!");
            return true;

        } catch (Exception e) {
            System.out.println("Delete enroll error: " + e.getMessage());
            return false;
        }
    }
}