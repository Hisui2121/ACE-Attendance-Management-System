package model;

/**
 * PRACTICAL USAGE EXAMPLES - OOP Principles in Real Scenarios
 * 
 * These examples show how to use the enhanced Person class hierarchy
 * in the Attendance System with OOP principles
 */
public class PersonUsageExamples {

    public static void main(String[] args) {
        System.out.println("========== PRACTICAL OOP USAGE EXAMPLES ==========\n");

        // Example 1: Creating instances
        exampleOne_CreatingInstances();

        // Example 2: Processing polymorphically
        exampleTwo_PolymorphicProcessing();

        // Example 3: Type-specific operations
        exampleThree_TypeSpecificOperations();

        // Example 4: Polymorphic method calls
        exampleFour_PolymorphicMethodCalls();
    }

    /**
     * Example 1: CREATING INSTANCES
     * Demonstrates inheritance and constructors
     */
    public static void exampleOne_CreatingInstances() {
        System.out.println("--- Example 1: Creating Instances ---\n");

        // Create a Student (inherits from Person)
        Student student = new Student(
            1,
            "STU-2024-001",
            "Alice Johnson",
            "Information Technology",
            "2nd Year",
            "alice@university.edu"
        );
        student.setPhoneNumber("09123456789");
        student.setAddress("123 Student Lane");

        // Create an Instructor (inherits from Person)
        Instructor instructor = new Instructor(
            2,
            "EMP-2024-001",
            "Prof. Robert Wilson",
            "Information Technology",
            "Software Engineering",
            "robert@university.edu",
            "09987654321",
            "456 Faculty Road",
            10
        );

        System.out.println("Student created: " + student.getFullName());
        System.out.println("Instructor created: " + instructor.getFullName());
        System.out.println();
    }

    /**
     * Example 2: POLYMORPHIC PROCESSING
     * Process different types uniformly through Person reference
     * This is DYNAMIC BINDING in action
     */
    public static void exampleTwo_PolymorphicProcessing() {
        System.out.println("--- Example 2: Polymorphic Processing ---\n");

        // Create different person types
        Student student = new Student(
            10,
            "STU-2024-002",
            "Bob Smith",
            "Computer Science",
            "3rd Year",
            "bob@university.edu"
        );

        Instructor instructor = new Instructor(
            11,
            "EMP-2024-002",
            "Dr. Sarah Lee",
            "Computer Science",
            "Data Science",
            "sarah@university.edu",
            "09555666777",
            "789 Academic Ave",
            7
        );

        // POLYMORPHISM: Store different types in Person array
        Person[] systemUsers = {student, instructor};

        // Process all users the same way
        // But each one behaves differently (DYNAMIC BINDING)
        for (Person user : systemUsers) {
            System.out.println("Name: " + user.getFullName());
            System.out.println("Role: " + user.getRole());
            System.out.println("Description: " + user.getDescription());
            System.out.println();
        }
    }

    /**
     * Example 3: TYPE-SPECIFIC OPERATIONS
     * Perform operations specific to each type while maintaining polymorphism
     */
    public static void exampleThree_TypeSpecificOperations() {
        System.out.println("--- Example 3: Type-Specific Operations ---\n");

        Person[] people = {
            new Student(20, "STU-2024-003", "Charlie Brown", "Engineering", "1st Year", "charlie@university.edu"),
            new Instructor(21, "EMP-2024-003", "Prof. Emily Davis", "Engineering", "Mechanical Engineering", "emily@university.edu", "09111222333", "101 Campus Road", 5)
        };

        for (Person person : people) {
            // Polymorphic call
            System.out.println(person.getFullName() + " is a " + person.getRole());

            // Type checking and type-specific operations
            if (person instanceof Student) {
                Student s = (Student) person;  // Casting
                System.out.println("  → Enrolled in: " + s.getCourse());
                System.out.println("  → Year Level: " + s.getYearLevel());
                // Could add attendance tracking, grade calculation, etc.

            } else if (person instanceof Instructor) {
                Instructor i = (Instructor) person;  // Casting
                System.out.println("  → Department: " + i.getDepartment());
                System.out.println("  → Specialization: " + i.getSpecialization());
                System.out.println("  → Experience: " + i.getYearsOfExperience() + " years");
                // Could add course assignment, salary calculation, etc.
            }
            System.out.println();
        }
    }

    /**
     * Example 4: POLYMORPHIC METHOD CALLS
     * Calling overridden methods through Person reference
     * Demonstrates METHOD OVERRIDING and DYNAMIC BINDING
     */
    public static void exampleFour_PolymorphicMethodCalls() {
        System.out.println("--- Example 4: Polymorphic Method Calls ---\n");

        Student student = new Student(
            30,
            "STU-2024-004",
            "Diana Martinez",
            "Business Administration",
            "4th Year",
            "diana@university.edu"
        );
        student.setPhoneNumber("09444555666");
        student.setAddress("999 Scholar Street");

        Instructor instructor = new Instructor(
            31,
            "EMP-2024-004",
            "Assoc. Prof. Frank Taylor",
            "Business Administration",
            "Marketing",
            "frank@university.edu",
            "09777888999",
            "202 Senior Avenue",
            12
        );

        // Process through Person reference
        System.out.println("=== Processing Student ===");
        processPerson(student);

        System.out.println("\n=== Processing Instructor ===");
        processPerson(instructor);
    }

    /**
     * POLYMORPHIC METHOD
     * Accepts Person reference but actually works with subclass types
     * This is the power of polymorphism!
     * 
     * @param person Any Person subclass (Student, Instructor, etc.)
     */
    public static void processPerson(Person person) {
        // These calls use DYNAMIC BINDING:
        // The actual method called depends on runtime type

        System.out.println("Email: " + person.getEmail());
        System.out.println("Role: " + person.getRole());        // Returns STUDENT or INSTRUCTOR
        System.out.println("Description: " + person.getDescription());  // Format depends on type

        // Overridden getInfo() provides type-specific information
        System.out.println("\nDetailed Info:");
        System.out.println(person.getInfo());
    }

    /**
     * REGISTRY PATTERN - Polymorphism Example
     * Simulate an attendance system processing different user types
     */
    public static void registryPatternExample() {
        System.out.println("\n--- Registry Pattern Example ---\n");

        // Create a registry of people
        java.util.List<Person> registry = new java.util.ArrayList<>();

        // Add different types
        registry.add(new Student(40, "STU-2024-005", "Eva Green", "Science", "2nd Year", "eva@university.edu"));
        registry.add(new Instructor(41, "EMP-2024-005", "Dr. Henry White", "Science", "Chemistry", "henry@university.edu", "09123123123", "303 Lab Road", 15));
        registry.add(new Student(42, "STU-2024-006", "George Black", "Arts", "3rd Year", "george@university.edu"));

        // Process all uniformly
        System.out.println("System Registry:");
        for (Person person : registry) {
            System.out.println("✓ " + person.getDescription());
        }

        // Filter and process by type
        System.out.println("\nStudents only:");
        registry.stream()
                .filter(p -> p instanceof Student)
                .forEach(p -> System.out.println("  • " + p.getFullName()));

        System.out.println("\nInstructors only:");
        registry.stream()
                .filter(p -> p instanceof Instructor)
                .forEach(p -> System.out.println("  • " + p.getFullName()));
    }
}
