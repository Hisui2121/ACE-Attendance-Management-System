package model;

public class Student extends Person {

    private String studentId;
    private String course;
    private String yearLevel;

    // EMPTY CONSTRUCTOR
    public Student() {
        super();
    }

    // CONSTRUCTOR WITHOUT DB ID
    public Student(String studentId,
                   String fullName,
                   String course,
                   String yearLevel,
                   String email) {
        super(fullName, email);
        this.studentId = studentId;
        this.course = course;
        this.yearLevel = yearLevel;
    }

    // FULL CONSTRUCTOR
    public Student(int id,
                   String studentId,
                   String fullName,
                   String course,
                   String yearLevel,
                   String email) {
        super(id, fullName, email);
        this.studentId = studentId;
        this.course = course;
        this.yearLevel = yearLevel;
    }

    // =========================
    // GETTERS & SETTERS
    // =========================

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(String yearLevel) {
        this.yearLevel = yearLevel;
    }

    @Override
    public String getDescription() {
        return "Student: " + getFullName() +
               " [" + studentId + "] - " + course +
               " " + yearLevel + " (" + getEmail() + ")";
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", studentId='" + studentId + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", course='" + course + '\'' +
                ", yearLevel='" + yearLevel + '\'' +
                '}';
    }
}