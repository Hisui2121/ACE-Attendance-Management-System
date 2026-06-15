package model;

/**
 * INHERITANCE PRINCIPLE: Instructor inherits from Person (base class)
 * Demonstrates polymorphism alongside Student class
 * 
 * POLYMORPHISM: Both Instructor and Student override same abstract methods
 * but provide different implementations based on their role
 */
public class Instructor extends Person {
    private String employeeId;
    private String department;
    private String specialization;
    private int yearsOfExperience;

    // Constructors
    public Instructor() {
        super();
    }

    public Instructor(String employeeId,
                     String fullName,
                     String department,
                     String specialization,
                     String email) {
        super(fullName, email);
        this.employeeId = employeeId;
        this.department = department;
        this.specialization = specialization;
    }

    public Instructor(int id,
                     String employeeId,
                     String fullName,
                     String department,
                     String specialization,
                     String email,
                     String phoneNumber,
                     String address,
                     int yearsOfExperience) {
        super(id, fullName, email, phoneNumber, address);
        this.employeeId = employeeId;
        this.department = department;
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
    }

    // Getters & Setters
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    /**
     * POLYMORPHISM & METHOD OVERRIDING:
     * Overrides abstract method from Person class
     * Different implementation than Student class for same method
     * This demonstrates DYNAMIC BINDING - runtime determines which method is called
     */
    @Override
    public String getDescription() {
        return "Instructor: " + getFullName() +
               " [" + employeeId + "] - " + specialization +
               " in " + department + " (" + getEmail() + ")";
    }

    /**
     * POLYMORPHISM & METHOD OVERRIDING:
     * Implements the abstract getRole() method from Person
     * Returns different role than Student class
     */
    @Override
    public String getRole() {
        return "INSTRUCTOR";
    }

    /**
     * POLYMORPHISM: Overrides getInfo() from Person class
     * Provides instructor-specific information display
     */
    @Override
    public String getInfo() {
        return super.getInfo() +
               "\nEmployee ID: " + employeeId +
               "\nDepartment: " + department +
               "\nSpecialization: " + specialization +
               "\nYears of Experience: " + yearsOfExperience;
    }

    /**
     * Demonstrates POLYMORPHISM - method specific to Instructor
     * Can be called polymorphically through Person reference if needed
     */
    public boolean isExperienced() {
        return yearsOfExperience >= 5;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + getId() +
                ", employeeId='" + employeeId + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", department='" + department + '\'' +
                ", specialization='" + specialization + '\'' +
                ", yearsOfExperience=" + yearsOfExperience +
                '}';
    }
}
