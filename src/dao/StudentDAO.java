package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;

import database.DBConnect;
import model.Student;

public class StudentDAO {
	
	// ADD STUDENT - CREATE
    public boolean addStudent(Student student) {
    
        String sql = "INSERT INTO students "
                   + "(student_id, full_name, course, year_level, email) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try {

            Connection conn = DBConnect.connect();

            PreparedStatement pstmt =
                    conn.prepareStatement(sql);
            
            pstmt.setString(1, student.getStudentId());
            pstmt.setString(2, student.getFullName());
            pstmt.setString(3, student.getCourse());
            pstmt.setString(4, student.getYearLevel());
            pstmt.setString(5, student.getEmail());

            pstmt.executeUpdate();

            System.out.println("Student added successfully!");

            return true;

        } catch (Exception e) {

            System.out.println("Add student error: "
                                + e.getMessage());

            return false;
        }
    }
    
    public ArrayList<Student> getAllStudents(){
    	ArrayList<Student> students = new ArrayList<>();
    	
    	String sql = "SELECT * FROM students";
    	
    	try {
    		Connection conn = DBConnect.connect();
    		
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		
    		ResultSet rs = pstmt.executeQuery();
    		
    		while(rs.next()) {
    			Student student = new Student();
    			
    			student.setId(rs.getInt("id"));

    			student.setStudentId(rs.getString("student_id"));
    			
    			student.setFullName(rs.getString("full_name"));
    			
    			student.setCourse(rs.getString("course"));
    			
    			student.setYearLevel(rs.getString("year_level"));
    			
    			student.setEmail(rs.getString("email"));
    			
    			students.add(student);
    		}
    	} catch (Exception e){
    		System.out.println("Read error: " + e.getMessage());
    	}
    	
    	return students;
    }
    
    public boolean updateStudent(Student student) {
    	String sql = "UPDATE students SET "
                + "full_name=?, "
                + "course=?, "
                + "year_level=?, "
                + "email=? "
                + "WHERE student_id=?";
    	try {

            Connection conn = DBConnect.connect();

            PreparedStatement pstmt =
                    conn.prepareStatement(sql);

            pstmt.setString(1, student.getFullName());
            pstmt.setString(2, student.getCourse());
            pstmt.setString(3, student.getYearLevel());
            pstmt.setString(4, student.getEmail());
            pstmt.setString(5, student.getStudentId());

            pstmt.executeUpdate();

            System.out.println("Student updated!");

            return true;

        } catch(Exception e) {

            System.out.println("Update error: " + e.getMessage());

            return false;
        }
    	
    }
    
    public boolean deleteStudent(String studentId) {
		String sql = "DELETE FROM students WHERE student_id=?";
		
		try {
			Connection conn = DBConnect.connect();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, studentId);
			
			pstmt.executeUpdate();
			
			System.out.println("Student deleted successfully!");
			
			return true;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return false;
		}
	}
    
}
