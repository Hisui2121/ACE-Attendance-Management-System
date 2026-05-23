package model;

public class AttendanceSession {

    private int id;
    private int classId;
    private String sessionDate;
    private String createdBy;

    public AttendanceSession() {

    }

    public AttendanceSession(int classId,
                             String sessionDate,
                             String createdBy) {

        this.classId = classId;
        this.sessionDate = sessionDate;
        this.createdBy = createdBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}