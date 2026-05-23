package model;

public class Lesson {

    private int id;
    private String lessonCode;
    private String lessonName;
    private String teacherId;
    private String schedule;
    private String room;

    public Lesson() {

    }

    // without DB id
    public Lesson(String lessonCode,
                  String lessonName,
                  String teacherId,
                  String schedule,
                  String room) {

        this.lessonCode = lessonCode;
        this.lessonName = lessonName;
        this.teacherId = teacherId;
        this.schedule = schedule;
        this.room = room;
    }

    // full constructor
    public Lesson(int id,
                  String lessonCode,
                  String lessonName,
                  String teacherId,
                  String schedule,
                  String room) {

        this.id = id;
        this.lessonCode = lessonCode;
        this.lessonName = lessonName;
        this.teacherId = teacherId;
        this.schedule = schedule;
        this.room = room;
    }

    // getters & setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getLessonCode() { return lessonCode; }

    public void setLessonCode(String lessonCode) { this.lessonCode = lessonCode; }

    public String getLessonName() { return lessonName; }

    public void setLessonName(String lessonName) { this.lessonName = lessonName; }

    public String getTeacherId() { return teacherId; }

    public void setTeacherId(String teacherId) { this.teacherId = teacherId; }

    public String getSchedule() { return schedule; }

    public void setSchedule(String schedule) { this.schedule = schedule; }

    public String getRoom() { return room; }

    public void setRoom(String room) { this.room = room; }
}