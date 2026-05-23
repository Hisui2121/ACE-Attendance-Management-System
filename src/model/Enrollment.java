package model;

public class Enrollment {

    private int id;
    private String studentId;
    private int classId;

    public Enrollment() {

    }

    public Enrollment(String studentId, int classId) {
        this.studentId = studentId;
        this.classId = classId;
    }

    public Enrollment(int id, String studentId, int classId) {
        this.id = id;
        this.studentId = studentId;
        this.classId = classId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}