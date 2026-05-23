package model;

public class Attendance {
	private int attendanceId;
    private int studentId;
    private String attendanceDate;
    private String status;

    public Attendance() {

    }
    
    //     
    public Attendance(int studentId,
                      String attendanceDate,
                      String status) {

        this.studentId = studentId;
        this.attendanceDate = attendanceDate;
        this.status = status;
    }

    // THE GETTERS & SETTERS

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
