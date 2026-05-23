package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.DBConnect;
import model.Lesson;

public class LessonDAO {

    // CREATE LESSON
    public boolean addLesson(Lesson lesson) {

        String sql =
            "INSERT INTO lessons "
          + "(lesson_code, lesson_name, teacher_id, schedule, room) "
          + "VALUES (?, ?, ?, ?, ?)";

        try {

            Connection conn = DBConnect.connect();

            PreparedStatement pstmt =
                    conn.prepareStatement(sql);

            pstmt.setString(1, lesson.getLessonCode());
            pstmt.setString(2, lesson.getLessonName());
            pstmt.setString(3, lesson.getTeacherId());
            pstmt.setString(4, lesson.getSchedule());
            pstmt.setString(5, lesson.getRoom());

            pstmt.executeUpdate();

            System.out.println("Lesson created successfully!");

            return true;

        } catch (Exception e) {

            System.out.println("Add lesson error: " + e.getMessage());
            return false;
        }
    }

    // READ ALL LESSONS
    public ArrayList<Lesson> getAllLessons() {

        ArrayList<Lesson> lessons = new ArrayList<>();

        String sql = "SELECT * FROM lessons";

        try {

            Connection conn = DBConnect.connect();

            PreparedStatement pstmt =
                    conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Lesson lesson = new Lesson();

                lesson.setId(rs.getInt("id"));
                lesson.setLessonCode(rs.getString("lesson_code"));
                lesson.setLessonName(rs.getString("lesson_name"));
                lesson.setTeacherId(rs.getString("teacher_id"));
                lesson.setSchedule(rs.getString("schedule"));
                lesson.setRoom(rs.getString("room"));

                lessons.add(lesson);
            }

        } catch (Exception e) {

            System.out.println("Read lesson error: " + e.getMessage());
        }

        return lessons;
    }

    // DELETE LESSON
    public boolean deleteLesson(int id) {

        String sql = "DELETE FROM lessons WHERE id=?";

        try {

            Connection conn = DBConnect.connect();

            PreparedStatement pstmt =
                    conn.prepareStatement(sql);

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            System.out.println("Lesson deleted!");

            return true;

        } catch (Exception e) {

            System.out.println("Delete lesson error: " + e.getMessage());
            return false;
        }
    }
}