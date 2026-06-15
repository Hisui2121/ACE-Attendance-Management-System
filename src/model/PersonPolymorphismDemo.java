package model;

import java.util.ArrayList;
import java.util.List;

/**
 * DEMONSTRATION OF OOP PRINCIPLES:
 * 1. INHERITANCE: Person is parent class, Student & Instructor are children
 * 2. POLYMORPHISM: Same method (getDescription, getRole) called on different types
 * 3. DYNAMIC BINDING: Actual method called determined at runtime based on object type
 * 4. METHOD OVERRIDING: Subclasses override parent abstract methods with own implementation
 * 5. ENCAPSULATION: Private fields with public accessors
 * 6. ABSTRACTION: Abstract methods hide implementation details
 */
public class PersonPolymorphismDemo {

    public static void main(String[] args) {
        System.out.println("================== OOP PRINCIPLES DEMONSTRATION ==================\n");

        // 1. INHERITANCE - Create Student and Instructor objects
        Student student = new Student(
            1,
            "STU-001",
            "John Doe",
            "Computer Science",
            "3rd Year",
            "john.doe@university.edu"
        );
        student.setPhoneNumber("09123456789");
        student.setAddress("123 Main Street, City");

        Instructor instructor = new Instructor(
            2,
            "EMP-001",
            "Dr. Jane Smith",
            "Computer Science",
            "Artificial Intelligence",
            "jane.smith@university.edu",
            "09987654321",
            "456 Oak Avenue, City",
            8
        );

        // 2. POLYMORPHISM - Store different types in Person array/list
        System.out.println("--- 2. POLYMORPHISM: Different types in same collection ---\n");
        List<Person> people = new ArrayList<>();
        people.add(student);
        people.add(instructor);

        // 3. DYNAMIC BINDING - Actual method called determined at runtime
        System.out.println("--- 3. DYNAMIC BINDING: Method called based on runtime type ---\n");
        for (Person person : people) {
            // These calls use DYNAMIC BINDING:
            // - getRole() returns different value based on actual type
            // - getDescription() returns different format based on actual type
            System.out.println("Role: " + person.getRole());
            System.out.println("Description: " + person.getDescription());
            System.out.println();
        }

        // 4. METHOD OVERRIDING - Display overridden methods
        System.out.println("--- 4. METHOD OVERRIDING: getInfo() overridden by subclasses ---\n");
        System.out.println("=== Student Information ===");
        System.out.println(student.getInfo());
        System.out.println("\n=== Instructor Information ===");
        System.out.println(instructor.getInfo());

        // 5. displayDetails() - Polymorphic method
        System.out.println("\n--- 5. POLYMORPHIC BEHAVIOR: displayDetails() ---\n");
        for (Person person : people) {
            person.displayDetails();
            System.out.println();
        }

        // 6. Casting and type checking
        System.out.println("--- 6. TYPE CHECKING AND CASTING ---\n");
        for (Person person : people) {
            if (person instanceof Student) {
                Student s = (Student) person;
                System.out.println(s.getFullName() + " is studying " + s.getCourse());
            } else if (person instanceof Instructor) {
                Instructor i = (Instructor) person;
                System.out.println(i.getFullName() + " specializes in " + i.getSpecialization());
                System.out.println("Experienced: " + (i.isExperienced() ? "Yes" : "No"));
            }
        }

        // 7. Polymorphic array demonstration
        System.out.println("\n--- 7. POLYMORPHIC ARRAY DEMONSTRATION ---\n");
        demonstratePolymorphism(people);
    }

    /**
     * POLYMORPHISM DEMONSTRATION:
     * This method accepts List<Person> which can contain any subclass
     * Demonstrates how the same method can work with different types
     * through dynamic binding
     */
    public static void demonstratePolymorphism(List<Person> people) {
        System.out.println("Processing " + people.size() + " people:\n");
        for (int i = 0; i < people.size(); i++) {
            Person p = people.get(i);
            System.out.println((i + 1) + ". " + p.getDescription());
            System.out.println("   Role: " + p.getRole());
            System.out.println("   Email: " + p.getEmail());
            System.out.println();
        }
    }
}
