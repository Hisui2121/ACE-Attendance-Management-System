package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.DBConnect;
import model.Enrollment;

public class EnrollmentDAO {

    // ENROLL STUDENT TO LESSON
    public boolean enrollStudent(Enrollment enrollment) {

    	String sql =
    		    "INSERT INTO enrollments (student_id, class_id) "
    		  + "VALUES (?, ?)";

        try {

            Connection conn = DBConnect.connect();

            PreparedStatement pstmt =
                    conn.prepareStatement(sql);

            pstmt.setString(1, enrollment.getStudentId());
            pstmt.setInt(2, enrollment.getClassId());

            pstmt.executeUpdate();

            System.out.println("Student enrolled successfully!");

            return true;

        } catch (Exception e) {

            System.out.println("Enroll error: " + e.getMessage());
            return false;
        }
    }

    // VIEW ALL ENROLLMENTS
    public ArrayList<Enrollment> getAllEnrollments() {

        ArrayList<Enrollment> list = new ArrayList<>();

        String sql = "SELECT * FROM enrollments";

        try {

            Connection conn = DBConnect.connect();

            PreparedStatement pstmt =
                    conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Enrollment e = new Enrollment();

                e.setId(rs.getInt("id"));
                e.setStudentId(rs.getString("student_id"));
                e.setClassId(rs.getInt("class_id"));

                list.add(e);
            }

        } catch (Exception e) {

            System.out.println("Read enroll error: " + e.getMessage());
        }

        return list;
    }

    // REMOVE ENROLLMENT
    public boolean removeEnrollment(int id) {

        String sql = "DELETE FROM enrollments WHERE id=?";

        try {

            Connection conn = DBConnect.connect();

            PreparedStatement pstmt =
                    conn.prepareStatement(sql);

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            System.out.println("Enrollment removed!");

            return true;

        } catch (Exception e) {

            System.out.println("Delete enroll error: " + e.getMessage());
            return false;
        }
    }
}