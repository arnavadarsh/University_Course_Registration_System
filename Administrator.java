package ERP;

import java.util.*;

class Administrator extends User {

    // List of professors managed by the administrator
    List<Professor> professors = new ArrayList<>();
    // Static list to manage complaints
    private static final List<Complaint> complaints = new ArrayList<>();
    // Map to hold student grades
    private Map<String, Double> grades;

    // Constructor for the Administrator class
    public Administrator(String email) {
        super(email, "fixed_password"); // Call to User constructor
    }

    // Helper method to find a course by its code
    private Course findCourseByCode(String code, List<Course> courses) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(code)) {
                return course; // Return the found course
            }
        }
        return null; // Return null if not found
    }

    // Method to print the student records management menu
    public static void print_student_record() {
        System.out.println("\nStudent Records Management:");
        System.out.println("1. View All Students");
        System.out.println("2. Add New Student");
        System.out.println("3. Remove Student");
        System.out.println("4. Update Student Record");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    // Method to handle student complaints
    public void handleComplaints(List<Complaint> complaints) {
        Scanner scanner = new Scanner(System.in);

        // Check if there are complaints to handle
        if (complaints.isEmpty()) {
            System.out.println("No complaints to handle.");
            return;
        }

        // Loop to handle complaints
        while (true) {
            System.out.println("Complaints:");
            for (int i = 0; i < complaints.size(); i++) {
                Complaint complaint = complaints.get(i);
                System.out.println((i + 1) + ". " + complaint);
            }

            // Prompt user to select a complaint to handle
            System.out.println("Enter the number of the complaint to handle (0 to exit):");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 0) {
                break; // Exit the loop
            }

            if (choice < 1 || choice > complaints.size()) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            Complaint selectedComplaint = complaints.get(choice - 1);
            System.out.println("Selected Complaint: " + selectedComplaint);

            // Options to mark the complaint as resolved or return to the list
            System.out.println("1. Mark as Resolved");
            System.out.println("2. Return to Complaints List");

            int action = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (action == 2) {
                // Return to the complaint list
            } else if (action == 1) {
                handleResolveComplaint(selectedComplaint); // Resolve the complaint
            } else {
                System.out.println("Invalid action. Try again.");
            }
        }
    }

    // Helper method to list all students
    private static void all_student(List<Student> students) {
        System.out.println("Student Records:");
        for (Student student : students) {
            System.out.println("Email: " + student.getEmail() + ", Semester: " + student.getSemester());
            // Additional details can be added if needed
        }
    }

    // Helper method to add a new student
    private static void add_new_student(List<Student> students) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Student Password: ");
        String password = scanner.nextLine();

        // Create a new student with a default semester of 1
        Student newStudent = new Student(email, password);
        students.add(newStudent); // Add the student to the list
        System.out.println("New student added: " + newStudent.getEmail());
    }

    // Helper method to remove a student by email
    public static void remove_student(List<Student> students) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student Email to Remove: ");
        String emailToRemove = scanner.nextLine();
        Student studentToRemove = null;

        // Find the student to remove
        for (Student student : students) {
            if (student.getEmail().equals(emailToRemove)) {
                studentToRemove = student;
                break;
            }
        }

        if (studentToRemove != null) {
            students.remove(studentToRemove); // Remove the student from the list
            System.out.println("Removed student: " + studentToRemove.getEmail());
        } else {
            System.out.println("Student not found.");
        }
    }

    // Helper method to update a student's record
    public static void update_student(List<Student> students) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student Email to Update: ");
        String emailToUpdate = scanner.nextLine();
        Student studentToUpdate = null;

        // Find the student to update
        for (Student student : students) {
            if (student.getEmail().equals(emailToUpdate)) {
                studentToUpdate = student;
                break;
            }
        }

        // If the student is found, allow updates
        if (studentToUpdate != null) {
            // Update personal information
            System.out.print("Enter New Name (leave blank to maintain the current name): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                studentToUpdate.setName(newName); // Update name if provided
            }

            System.out.print("Enter New Email (leave blank to maintain the current email): ");
            String newEmail = scanner.nextLine();
            if (!newEmail.isEmpty()) {
                studentToUpdate.setEmail(newEmail); // Update email if provided
            }

            // Update academic information
            System.out.print("Enter New Semester (leave blank to maintain the current semester): ");
            String newSemesterStr = scanner.nextLine();
            if (!newSemesterStr.isEmpty()) {
                try {
                    int newSemester = Integer.parseInt(newSemesterStr);
                    studentToUpdate.setSemester(newSemester); // Update semester
                    studentToUpdate.setTotalCredits(0); // Reset total credits
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input for semester. Please enter a valid integer.");
                }
            }

            // Prompt to update grades
            System.out.print("Would you like to update the grades? (Type 'yes' to update or 'no' to skip): ");
            String updateGrades = scanner.nextLine();
            if (updateGrades.equalsIgnoreCase("yes")) {
                System.out.println("Updating the grades for the selected student...");
                Map<String, Double> grades = studentToUpdate.getGrades();
                for (Course course : studentToUpdate.getRegisteredCourses()) {
                    System.out.print("Enter new grade for " + course.getTitle() + " (leave blank to maintain the current grades): ");
                    String newGradeStr = scanner.nextLine();
                    if (!newGradeStr.isEmpty()) {
                        try {
                            double newGrade = Double.parseDouble(newGradeStr);
                            grades.put(course.getCourseCode(), newGrade); // Update the grade
                            System.out.println("Grade for " + course.getTitle() + " updated to " + newGrade);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input for grade. Please enter a valid number.");
                        }
                    }
                }
                studentToUpdate.setGrades(grades); // Ensure the student has the updated grades
            }

            System.out.println("Student record updated: " + studentToUpdate.getEmail() +
                    ", Name: " + studentToUpdate.getName() +
                    ", Semester: " + studentToUpdate.getSemester());
        } else {
            System.out.println("Student not found.");
        }
    }

    // Method to manage student records
    public void manageStudentRecords(List<Student> students) {
        Scanner scanner = new Scanner(System.in);
        print_student_record(); // Print the menu

        while (true) {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // View all students
                    all_student(students);
                    break;

                case 2:
                    // Add a new student
                    add_new_student(students);
                    break;

                case 3:
                    // Remove a student
                    remove_student(students);
                    break;

                case 4:
                    // Update a student record
                    update_student(students);
                    break;

                case 5:
                    // Exit the management
                    System.out.println("Exiting Student Records Management.");
                    return; // Exit the method

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Helper method to find a student by email
    private Student findStudentByEmail(String email, List<Student> students) {
        for (Student student : students) {
            if (student.getEmail().equals(email)) {
                return student; // Return the found student
            }
        }
        return null; // Return null if not found
    }

    // Helper method to view all courses
    private static void view_all_courses(List<Course> courses) {
        System.out.println("Course Catalog:");
        for (Course course : courses) {
            System.out.println(course); // Print each course
        }
    }

    // Helper method to add a new course
    private static void add_new_courses(List<Course> courses) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Semester: ");
        int semester = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Course Code: ");
        String code = scanner.nextLine();
        System.out.print("Enter Course Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Course Credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Course Timings: ");
        String timings = scanner.nextLine();

        // Handle prerequisites
        System.out.print("Enter number of prerequisites (0 if none): ");
        int numPrerequisites = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<Course> prerequisites = new ArrayList<>();
        for (int i = 0; i < numPrerequisites; i++) {
            System.out.print("Enter prerequisite course code: ");
            String prereqCode = scanner.nextLine();
            // Find the prerequisite course by code
            Course prerequisiteCourse = null;
            for (Course c : courses) {
                if (c.getCourseCode().equals(prereqCode)) {
                    prerequisiteCourse = c;
                    break; // Prerequisite found
                }
            }
            if (prerequisiteCourse != null) {
                prerequisites.add(prerequisiteCourse); // Add to prerequisites
            } else {
                System.out.println("Prerequisite course not found: " + prereqCode);
            }
        }

        // Add the new course to the catalog
        Course newCourse = new Course(code, title, null, credits, semester, prerequisites, timings);
        courses.add(newCourse);
        System.out.println("Course added successfully.");
    }

    // Helper method to remove a course by code
    private static void remove_course(List<Course> courses) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Course Code to Remove: ");
        String codeToRemove = scanner.nextLine();
        Course courseToRemove = null;

        // Find the course to remove
        for (Course course : courses) {
            if (course.getCourseCode().equals(codeToRemove)) {
                courseToRemove = course;
                break; // Course found
            }
        }

        if (courseToRemove != null) {
            courses.remove(courseToRemove); // Remove the course
            System.out.println("Removed course: " + courseToRemove);
        } else {
            System.out.println("Course not found.");
        }
    }

    // Helper method to update course details
    private static void update_course(List<Course> courses) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Course Code to Update: ");
        String codeToUpdate = scanner.nextLine();
        Course courseToUpdate = null;

        // Find the course to update
        for (Course course : courses) {
            if (course.getCourseCode().equals(codeToUpdate)) {
                courseToUpdate = course;
                break; // Course found
            }
        }

        if (courseToUpdate != null) {
            // Update course details
            System.out.print("Enter New Title (leave blank to maintain the current Title): ");
            String newTitle = scanner.nextLine();
            if (!newTitle.isEmpty()) {
                courseToUpdate.setTitle(newTitle); // Update title if provided
            }

            System.out.print("Enter New Credits (leave blank to maintain the current Credits): ");
            String newCreditsStr = scanner.nextLine();
            if (!newCreditsStr.isEmpty()) {
                int newCredits = Integer.parseInt(newCreditsStr);
                courseToUpdate.setCredits(newCredits); // Update credits
            }

            System.out.print("Enter New Timings (leave blank to maintain the current Timings): ");
            String newTimings = scanner.nextLine();
            if (!newTimings.isEmpty()) {
                courseToUpdate.setTimings(newTimings); // Update timings
            }

            System.out.println("Course updated: " + courseToUpdate);
        } else {
            System.out.println("Course not found.");
        }
    }

    // Method to manage the course catalog
    public void manageCatalog(List<Course> courses) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCourse Catalog Management:");
            System.out.println("1. View All Courses");
            System.out.println("2. Add New Course");
            System.out.println("3. Remove Course");
            System.out.println("4. Update Course");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // View all courses
                    view_all_courses(courses);
                    break;

                case 2:
                    // Add a new course
                    add_new_courses(courses);
                    break;

                case 3:
                    // Remove a course
                    remove_course(courses);
                    break;

                case 4:
                    // Update a course
                    update_course(courses);
                    break;

                case 5:
                    // Exit the management interface
                    System.out.println("Exiting the Course Catalog Management interface.");
                    return; // Exit the method

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Method to assign professors to courses
    public void assignProfessors(List<Professor> professors, List<Course> courses) {
        Scanner scanner = new Scanner(System.in);

        // Check if there are professors and courses available
        if (professors.isEmpty()) {
            System.out.println("No professors available to assign.");
            return;
        }

        if (courses.isEmpty()) {
            System.out.println("No courses available to assign professors.");
            return;
        }

        // Loop through each course to assign professors
        for (Course course : courses) {
            System.out.println("Assign a professor to " + course.getTitle());

            // Display available professors
            System.out.println("Available Professors:");
            int i = 0;
            while (i < professors.size()) {
                System.out.println((i + 1) + ". " + professors.get(i).getEmail());
                i++;
            }

            // Prompt user to select a professor
            System.out.print("Enter the number of the professor to assign: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Validate and assign the selected professor
            if (choice >= 1 && choice <= professors.size()) {
                Professor selectedProfessor = professors.get(choice - 1);
                course.setProfessor(selectedProfessor); // Assign the professor
                System.out.println("Assigned Professor: " + selectedProfessor.getEmail() + " to " + course.getTitle());
            } else {
                System.out.println("Invalid choice. No professor assigned.");
            }
        }
    }

    // Helper method to resolve a complaint
    private void handleResolveComplaint(Complaint complaint) {
        // Display the complaint being resolved
        System.out.println("Resolving complaint: " + complaint.getDescription());

        // Logic to mark the complaint as resolved
        complaint.setStatus("Resolved");
        System.out.println("Complaint resolved.");
    }
}
