package model;

/**
 * Abstract base class representing any person in the system.
 *
 * OOP CONCEPTS DEMONSTRATED:
 * - Inheritance   : Student, Teacher, and User all extend this class,
 *                   inheriting fullName and email without duplicating code.
 * - Abstraction   : getPersonId() and getRole() are declared abstract,
 *                   forcing every subclass to provide its own implementation.
 * - Polymorphism  : Any code that holds a Person reference can call
 *                   getDisplayInfo() and getPersonId() without knowing
 *                   the concrete type at compile time (dynamic binding).
 */
public abstract class Person {

    // =========================================================
    // SHARED FIELDS — inherited by Student, Teacher, and User
    // =========================================================
    protected String fullName;
    protected String email;

    // =========================================================
    // CONSTRUCTORS
    // =========================================================
    public Person() {}

    public Person(String fullName, String email) {
        this.fullName = fullName;
        this.email    = email;
    }

    // =========================================================
    // CONCRETE GETTERS / SETTERS — shared by all subclasses
    // =========================================================
    public String getFullName()            { return fullName; }
    public void   setFullName(String name) { this.fullName = name; }

    public String getEmail()               { return email; }
    public void   setEmail(String email)   { this.email = email; }

    // =========================================================
    // ABSTRACT METHODS
    // Each subclass MUST override these (method overriding).
    // Calling them on a Person reference triggers dynamic binding —
    // Java resolves the correct implementation at runtime.
    // =========================================================

    /**
     * Returns the domain-specific ID string for this person.
     * Student → "2024-00123", Teacher → "T-001", User → username
     */
    public abstract String getPersonId();

    /**
     * Returns the role/type label for this person.
     * "Student", "Teacher", "Admin", etc.
     */
    public abstract String getRole();

    /**
     * Returns a formatted one-line summary.
     * Overriding this in each subclass demonstrates method overriding +
     * polymorphism — the same method call produces different output
     * depending on the runtime type.
     */
    public String getDisplayInfo() {
        return "[" + getRole() + "] " + fullName
             + " | ID: " + getPersonId()
             + " | Email: " + email;
    }

    // =========================================================
    // toString
    // =========================================================
    @Override
    public String toString() {
        return fullName != null ? fullName : getPersonId();
    }
}