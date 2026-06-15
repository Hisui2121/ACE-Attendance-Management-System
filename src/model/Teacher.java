package model;

/**
 * Represents a teacher/professor in the system.
 *
 * OOP CONCEPTS DEMONSTRATED:
 * - Inheritance      : extends Person, inheriting fullName and email.
 * - Method Overriding: overrides getPersonId(), getRole(), getDisplayInfo(),
 *                      and toString() from Person.
 * - Dynamic Binding  : when Teacher is referenced as a Person, Java
 *                      automatically calls these overridden versions at runtime.
 */
public class Teacher extends Person {

    private int    id;
    private String teacherId;

    // =========================================================
    // CONSTRUCTORS
    // =========================================================
    public Teacher() {
        super();
    }

    public Teacher(int id, String teacherId, String fullName, String email) {
        super(fullName, email);   // calls Person(fullName, email)
        this.id        = id;
        this.teacherId = teacherId;
    }

    // =========================================================
    // OVERRIDDEN ABSTRACT METHODS (Method Overriding)
    // =========================================================

    /**
     * @Override getPersonId() from Person
     * Dynamic binding: Person ref → returns teacherId at runtime.
     */
    @Override
    public String getPersonId() {
        return teacherId;
    }

    /**
     * @Override getRole() from Person
     */
    @Override
    public String getRole() {
        return "Teacher";
    }

    /**
     * @Override getDisplayInfo() from Person
     * Teacher-specific format — no course/year, but shows teacher ID clearly.
     */
    @Override
    public String getDisplayInfo() {
        return "[Teacher] " + fullName
             + " | Teacher ID: " + teacherId
             + " | Email: "      + email;
    }

    // =========================================================
    // TEACHER-SPECIFIC GETTERS & SETTERS
    // =========================================================
    public int    getId()                        { return id; }
    public void   setId(int id)                  { this.id = id; }

    public String getTeacherId()                 { return teacherId; }
    public void   setTeacherId(String tid)       { this.teacherId = tid; }

    // =========================================================
    // toString (method overriding)
    // =========================================================
    @Override
    public String toString() {
        if (fullName != null && teacherId != null)
            return fullName + " (" + teacherId + ")";
        return teacherId != null ? teacherId : "";
    }
}