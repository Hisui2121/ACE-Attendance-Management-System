package model;

/**
 * INHERITANCE PRINCIPLE: Base class for all persons in the system
 * Demonstrates OOP concepts:
 * - ENCAPSULATION: private fields with public getters/setters
 * - ABSTRACTION: abstract method getDescription() for polymorphism
 * - INHERITANCE: serves as parent class for Student, Instructor, etc.
 * - POLYMORPHISM: subclasses override getDescription() and getRole()
 */
public abstract class Person {
    private int id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;

    // Constructors
    public Person() {
    }

    public Person(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    public Person(int id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public Person(int id, String fullName, String email, String phoneNumber, String address) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * POLYMORPHISM & DYNAMIC BINDING:
     * Abstract method to be overridden by subclasses.
     * At runtime, the actual type determines which implementation is called.
     * This is DYNAMIC BINDING (late binding).
     */
    public abstract String getDescription();

    /**
     * POLYMORPHISM & METHOD OVERRIDING:
     * Abstract method for role. Each subclass provides its own role implementation.
     */
    public abstract String getRole();

    /**
     * POLYMORPHISM: getInfo method can be overridden by subclasses
     * Provides a template for displaying person information
     */
    public String getInfo() {
        return "Name: " + fullName +
               "\nEmail: " + email +
               "\nPhone: " + (phoneNumber != null ? phoneNumber : "N/A") +
               "\nAddress: " + (address != null ? address : "N/A") +
               "\nRole: " + getRole();
    }

    /**
     * Demonstrates POLYMORPHISM - can be called on Person reference
     * but actual behavior depends on runtime type
     */
    public void displayDetails() {
        System.out.println("=== " + getRole() + " Details ===");
        System.out.println(getInfo());
        System.out.println("Description: " + getDescription());
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
