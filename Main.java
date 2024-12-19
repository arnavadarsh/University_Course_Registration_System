package ERP;

import java.util.*;

public class Main {
    // Lists to store students, professors, courses, and complaints
    private static List<ERP.Complaint> complaints;
    private static Scanner sc;
    private static boolean login;

    // Method to set up initial data for students, professors, courses, and complaints
    private static void setup(List<Student> students, List<Professor> professors, List<ERP.Course> courses, List<ERP.Complaint> complaints, List<TeachingAssistant> teachingAssistants) {
        // Adding some sample students
        students.add(new Student("student24001@iiitd.ac.in", "123"));
        students.add(new Student("student23001@iiitd.ac.in", "123", 3));
        students.add(new Student("student22001@iiit.ac.in", "123", 5));
        students.add(new Student("student21001@iiit.ac.in", "123",7));

        // Adding some sample teaching assistants
        teachingAssistants.add(new TeachingAssistant("ta1@iiitd.ac.in", "123",3));
        teachingAssistants.add(new TeachingAssistant("ta2@iiitd.ac.in", "123",5));

        // Adding some sample professors
        professors.add(new Professor("professor1@iiitd.ac.in", "123"));
        professors.add(new Professor("professor2@iiitd.ac.in", "123"));
        professors.add(new Professor("professor3@iiitd.ac.in", "123"));
        professors.add(new Professor("professor4@iiitd.ac.in", "123"));
        professors.add(new Professor("professor5@iiitd.ac.in", "123"));

        // Creating courses and associating them with professors
        ERP.Course CSE101 = new ERP.Course("CSE101", "Introduction to Programming", professors.get(0), 4, 1, new ArrayList<>(), "Mon/Wed 10-11 AM");
        ERP.Course ECE101 = new ERP.Course("ECE101", "Digital Circuits", professors.get(1), 4, 1, new ArrayList<>(), "Tue/Thu 9-10 AM");
        ERP.Course DES101 = new ERP.Course("DES101", "Human Computer Interaction", professors.get(2), 4, 1, new ArrayList<>(), "Mon/Wed 2-3 PM");
        ERP.Course MAT101 = new ERP.Course("MAT101", "Linear Algebra", professors.get(3), 4, 1, new ArrayList<>(), "Tue/Thu 11-12 PM");
        ERP.Course COM101 = new ERP.Course("COM101", "Communication Skills", professors.get(4), 4, 1, new ArrayList<>(), "Mon/Wed 12-1 PM");

        // Semester 3 Courses (CSE and ECE)
        ERP.Course CSE201 = new ERP.Course("CSE201", "Advanced Programming", professors.get(0), 4, 3, List.of(), "Mon/Wed 3-4 PM");
        ERP.Course ECE201 = new ERP.Course("ECE201", "Embedded Logic Design", professors.get(1), 4, 3, List.of(), "Tue/Thu 10-11 AM");
        ERP.Course CSE202 = new ERP.Course("CSE202", "Operating System", professors.get(2), 4, 3, List.of(CSE201), "Mon/Wed 9-10 AM");
        ERP.Course ECE202 = new ERP.Course("ECE202", "Signals and Systems", professors.get(3), 4, 3, new ArrayList<>(), "Tue/Thu 2-3 PM");
        ERP.Course MAT201 = new ERP.Course("MAT201", "Multivariable Calculus", professors.get(4), 4, 3, new ArrayList<>(), "Mon/Wed 11-12 AM");
        ERP.Course CSE203 = new ERP.Course("CSE203", "Discrete Structures", professors.get(0), 4, 3, List.of(CSE101, CSE202), "Mon/Wed 3-4 PM");
        ERP.Course ECE203 = new ERP.Course("ECE203", "Circuit Theory and Devices", professors.get(1), 4, 3, List.of(ECE101), "Tue/Thu 10-11 AM");
        ERP.Course CSE204 = new ERP.Course("CSE204", "Discrete Mathematics", professors.get(2), 4, 3, List.of(CSE101), "Mon/Wed 9-10 AM");
        ERP.Course SSH201 = new ERP.Course("SSH201", "Money and Banking", professors.get(3), 4, 3, new ArrayList<>(), "Tue/Thu 2-3 PM");
        ERP.Course MAT202 = new ERP.Course("MAT202", "Real Analysis 1", professors.get(4), 4, 3, new ArrayList<>(), "Mon/Wed 11-12 AM");

        // Add courses to the list
        courses.add(CSE101);
        courses.add(ECE101);
        courses.add(MAT101);
        courses.add(DES101);
        courses.add(COM101);
        courses.add(CSE201);
        courses.add(CSE202);
        courses.add(CSE203);
        courses.add(CSE204);
        courses.add(ECE201);
        courses.add(ECE202);
        courses.add(ECE203);
        courses.add(SSH201);
        courses.add(MAT201);
        courses.add(MAT202);

        // Assign complaints to each student
        for (Student student : students) {
            student.setComplaints(complaints); // Ensure setComplaints is defined in the Student class
        }
    }


    // Main entry point for the application
    public static void main(String[] args) {
        // Lists to hold users, courses, and complaints
        List<Student> students = new ArrayList<>();
        List<Professor> professors = new ArrayList<>();
        List<TeachingAssistant> teachingAssistants=new ArrayList<>();
        List<ERP.Course> courses = new ArrayList<>();
        List<ERP.Complaint> complaints = new ArrayList<>();

        int currentSemester = 1; // Track the current semester

        // Initialize data
        setup(students, professors, courses, complaints,teachingAssistants);

        // Create a scanner for user input
        Scanner sc = new Scanner(System.in);

        try {
            while (true) {
                // Display welcome menu
                System.out.println("\n=== Welcome to the University Course Registration System ===");
                System.out.println("1. Start the application");
                System.out.println("0. Exit the application");

                int check = sc.nextInt();
                sc.nextLine(); // Clear the buffer

                if (check == 0) {
                    System.out.println("Thank you for using the system. Goodbye!");
                    break; // Exit the application
                } else if (check == 1) {
                    // Prompt user for their role
                    System.out.print("Please specify your role: (Student/Professor/Administrator/Teaching Assistant). ");
                    String role = sc.nextLine().toLowerCase();

                    // Handle user based on role
                    if (role.equalsIgnoreCase("student")) {
                        handleStudentTask(sc, students, courses, currentSemester);
                    } else if (role.equalsIgnoreCase("professor")) {
                        handleProfessorTask(sc, professors, courses,teachingAssistants);
                    } else if (role.equalsIgnoreCase("administrator")) {
                        handleAdminTask(sc, students, professors, courses, complaints);
                    }
                    else if (role.equalsIgnoreCase("teaching assistant")) {
                        handleTeachingAssistantTask(sc,teachingAssistants,courses);}
                    else {
                        System.out.println("Invalid role entered. Please select either 'Student', 'Professor', or 'Administrator'.");
                    }
                } else {
                    System.out.println("Invalid choice. Please enter 0 to exit or 1 to start.");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        } finally {
            sc.close(); // Close scanner to avoid resource leaks
        }
    }

    // Method to handle professor login/signup and their operations
    private static void handleProfessorTask(Scanner scanner, List<Professor> professors, List<Course> courses,List<TeachingAssistant> teachingAssistants) {
        System.out.print("Would you like to (Login/Signup)? ");
        String login_Signup = scanner.nextLine().toLowerCase();

        if (login_Signup.equals("login")) {
            // Professor login
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            try {
                Professor logged_In_Professor = authenticateProfessorLogin(email, password, professors);

                boolean professor_Active = true;
                while (professor_Active) {
                    System.out.println("\nProfessor Menu:");
                    System.out.println("1. View All Available Courses");
                    System.out.println("2. Review Assigned Courses");
                    System.out.println("3. Manage Course Information");
                    System.out.println("4. View Students Enrolled in a Course");
                    System.out.println("5. Access and Manage Student Grades");
                    System.out.println("6. Assign a Teaching Assistant");
                    System.out.println("7. Review Student Feedback");
                    System.out.println("8. Logout");

                    int choice = -1;
                    if (scanner.hasNextInt()) {
                        choice = scanner.nextInt();
                        scanner.nextLine();
                    } else {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.nextLine();
                        continue;
                    }

                    if (choice == 1) {
                        System.out.println("Available Courses:");
                        courses.forEach(course -> System.out.println(course));
                    } else if (choice == 2) {
                        logged_In_Professor.viewAssignedCourses(courses);
                    } else if (choice == 3) {
                        logged_In_Professor.manageCourses(courses);
                    } else if (choice == 4) {
                        logged_In_Professor.viewEnrolledStudents(logged_In_Professor, courses);
                    } else if (choice == 5) {
                        logged_In_Professor.update_student(logged_In_Professor, courses);
                        break;
                    } else if (choice == 6) {
                        logged_In_Professor.assignTeachingAssistant(courses, teachingAssistants);
                        break;
                    } else if (choice == 7) {
                        // View feedback for courses assigned to the logged-in professor
                        List<Course> assignedCourses = new ArrayList<>();
                        for (Course course : courses) {
                            if (course.getProfessor().equals(logged_In_Professor)) {
                                assignedCourses.add(course);
                            }
                        }

                        if (assignedCourses.isEmpty()) {
                            System.out.println("No courses assigned to you for feedback viewing.");
                        } else {
                            System.out.print("Enter the course code for which you want to view feedback: ");
                            String courseCode = scanner.nextLine();

                            // Find the course with the specified course code
                            Course courseToView = null;
                            for (Course course : assignedCourses) {
                                if (course.getCourseCode().equals(courseCode)) {
                                    courseToView = course;
                                    break;
                                }
                            }
                            if (courseToView == null) {
                                System.out.println("Course not found or you are not assigned to this course.");
                            } else {
                                logged_In_Professor.viewCourseFeedback(courseToView); // View feedback for the specified course
                            }
                        }

                    } else if (choice == 8) {
                        professor_Active = false;
                        System.out.println("You have been logged out successfully.");
                    } else {
                        System.out.println("Invalid choice. Please select a valid option.");
                    }
                }
            }
            catch (InvalidLoginException e) {
                System.out.println(e.getMessage());
            }

        } else if (login_Signup.equals("signup")) {
            // Handle signup process
            System.out.print("Enter your full name: ");
            String name_Signup = scanner.nextLine();
            System.out.print("Enter a new email address: ");
            String email_Signup = scanner.nextLine().toLowerCase();
            String password_Signup;
            while (true) {
                System.out.println("Create your Password: ");
                password_Signup = scanner.nextLine().toLowerCase();
                System.out.println("Confirm your Password: ");
                String passwordSignupCheck = scanner.nextLine().toLowerCase();
                if (password_Signup.equals(passwordSignupCheck))
                    break;
                else
                    System.out.println("Passwords don't match. Please try again.");
            }

            professors.add(new Professor(email_Signup, password_Signup));
            System.out.println("Signup successful! Welcome aboard, " + name_Signup + "!");

            handleProfessorTask(scanner, professors, courses,teachingAssistants); // Continue with the next steps
        } else {
            System.out.println("Invalid option. Please enter 'Login' or 'Signup'.");
        }
    }
    private static Professor authenticateProfessorLogin(String email, String password, List<Professor> professors) throws InvalidLoginException {
        Professor logged_In_Professor = professors.stream()
                .filter(prof -> prof.getEmail().equals(email) && prof.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (logged_In_Professor == null) {
            throw new InvalidLoginException("Invalid credentials. Please check your email and password.");
        }
        return logged_In_Professor;
    }
    private static TeachingAssistant authenticateTeachingAssistantLogin(String email, String password, List<TeachingAssistant> teachingAssistants) throws InvalidLoginException {
        TeachingAssistant logged_In_TA = teachingAssistants.stream()
                .filter(ta -> ta.getEmail().equals(email) && ta.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (logged_In_TA == null) {
            throw new InvalidLoginException("Invalid credentials. Please check your email and password.");
        }
        return logged_In_TA;
    }
    private static void handleTeachingAssistantTask(Scanner scanner, List<TeachingAssistant> teachingAssistants, List<Course> courses) {
        System.out.print("Would you like to (Login/Signup)? ");
        String login_Signup = scanner.nextLine().toLowerCase();

        if (login_Signup.equals("login")) {
            // TA login
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            try {
                TeachingAssistant logged_In_TA = authenticateTeachingAssistantLogin(email, password, teachingAssistants);

                boolean ta_Active = true;


                while (ta_Active) {
                    System.out.println("\nTeaching Assistant Menu:");
                    System.out.println("1. View Assigned Course");
                    System.out.println("2. Update Student Grades");
                    System.out.println("3. View Available Courses");
                    System.out.println("4. Register for a Course");
                    System.out.println("5. View Your Schedule");
                    System.out.println("6. Track Academic Progress");
                    System.out.println("7. Drop a Course");
                    System.out.println("8. Submit a Complaint");
                    System.out.println("9. Review Submitted Complaints");
                    System.out.println("10. Logout");

                    int choice = -1;
                    if (scanner.hasNextInt()) {
                        choice = scanner.nextInt();
                        scanner.nextLine(); // Clear buffer
                    } else {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.nextLine();
                        continue;
                    }

                    switch (choice) {
                        case 1: // View Assigned Courses
                            for (int i = 0; i < logged_In_TA.getAssistingcourses().size(); i++) {
                                System.out.println("Assigned Course: " + logged_In_TA.getAssistingcourses().get(i).getCourseCode());
                            }
                            break;

                        case 2: // Update Grades
                            logged_In_TA.updateGrades(logged_In_TA.getAssistingcourses());
                            break;

                        case 3: // View Available Courses
                            logged_In_TA.viewCourses(courses, logged_In_TA.getSemester());
                            break;

                        case 4: // Register for a Course
                            System.out.print("Enter Course Code to Register: ");
                            String codeToRegister = scanner.nextLine();
                            Course selectedcourseToRegister = findCourseByCode(codeToRegister, courses); // Find course by code

                            if (selectedcourseToRegister != null) {
                                logged_In_TA.registerCourse(selectedcourseToRegister, logged_In_TA.getSemester()); // Register for course
                            } else {
                                System.out.println("Course not found.");
                            }
                            break;

                        case 5: // View Schedule
                            logged_In_TA.viewSchedule();
                            break;

                        case 6: // Track Academic Progress
                            logged_In_TA.trackProgress();
                            break;

                        case 7: // Drop a Course
                            System.out.print("Enter Course Code to Drop: ");
                            String codeToDrop = scanner.nextLine();
                            Course selectedCourseToDrop = findCourseByCode(codeToDrop, courses); // Find course to drop

                            if (selectedCourseToDrop != null) {
                                logged_In_TA.dropCourse(selectedCourseToDrop); // Drop the course
                            } else {
                                System.out.println("Course not found.");
                            }
                            break;

                        case 8: // Submit a Complaint
                            System.out.print("Enter complaint description: ");
                            String complaintDescription = scanner.nextLine();
                            logged_In_TA.submitComplaint(complaintDescription);
                            break;

                        case 9: // View Complaints
                            logged_In_TA.viewComplaints();
                            break;

                        case 10: // Logout
                            ta_Active = false;
                            System.out.println("You have been logged out successfully.");
                            break;

                        default:
                            System.out.println("Invalid choice. Please select a valid option.");
                            break;
                    }
                }
            }  catch (InvalidLoginException e) {
                System.out.println(e.getMessage());
            } catch (FullCourseException e) {
                throw new RuntimeException(e);
            }
        } else if (login_Signup.equals("signup")) {
            // Handle signup process
            System.out.print("Enter your full name: ");
            String name_Signup = scanner.nextLine();
            System.out.print("Enter a new email address: ");
            String email_Signup = scanner.nextLine().toLowerCase();
            String password_Signup;
            while (true) {
                System.out.println("Create your Password: ");
                password_Signup = scanner.nextLine();
                System.out.println("Confirm your Password: ");
                String passwordSignupCheck = scanner.nextLine();
                if (password_Signup.equals(passwordSignupCheck))
                    break;
                else
                    System.out.println("Passwords don't match. Please try again.");
            }

            teachingAssistants.add(new TeachingAssistant(email_Signup, password_Signup));
            System.out.println("Signup successful! Welcome aboard, " + name_Signup + "!");

            handleTeachingAssistantTask(scanner, teachingAssistants, courses); // Continue with the next steps
        } else {
            System.out.println("Invalid option. Please enter 'Login' or 'Signup'.");
        }
    }


    // Method to handle student login/signup and their operations
    private static void handleStudentTask(Scanner scanner, List<Student> students, List<Course> courses, int currentSemester) {
        System.out.print("Would you like to (Login/Signup)? ");
        String loginSignup = scanner.nextLine().toLowerCase();

        switch (loginSignup) {
            case "login":
                // Student login
                System.out.print("Enter your email: ");
                String email = scanner.nextLine();
                System.out.print("Enter your password: ");
                String password = scanner.nextLine();

                try {
                    // Find the logged-in student
                    Student logged_In_Student = null;

                    for (Student student : students) {
                        if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                            logged_In_Student = student;
                            break;
                        }
                    }
                    if (logged_In_Student == null) {
                        throw new InvalidLoginException("Invalid credentials. Please check your email and password.");
                    } else {
                        for (Student student : students) {
                            if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                                logged_In_Student = student;
                                break; // Exit loop on successful login
                            }
                        }


                        if (logged_In_Student != null) {
                            boolean student_Active = true; // Track if student is active
                            while (student_Active) {
                                // Display student menu
                                displayStudentMenu();

                                int choice = -1;
                                if (scanner.hasNextInt()) {
                                    choice = scanner.nextInt();
                                    scanner.nextLine(); // Clear buffer
                                } else {
                                    System.out.println("Invalid input. Please enter a number.");
                                    scanner.nextLine(); // Clear buffer on invalid input
                                    continue;
                                }

                                // Handle student menu choices
                                if (choice == 1) {
                                    System.out.println("Enter your semester");
                                    currentSemester = scanner.nextInt();
                                    scanner.nextLine(); // Clear buffer
                                    logged_In_Student.viewCourses(courses, currentSemester); // View courses based on semester
                                } else if (choice == 2) {
                                    System.out.print("Enter Course Code to Register: ");
                                    String codeToRegister = scanner.nextLine();
                                    Course selectedCourseToRegister = findCourseByCode(codeToRegister, courses); // Find course by code
                                    if (selectedCourseToRegister != null) {
                                        try {
                                            logged_In_Student.registerCourse(selectedCourseToRegister, logged_In_Student.getSemester());
                                        } catch (FullCourseException e) {
                                            System.out.println(e.getMessage()); // Handle course full exception
                                        }
                                    } else {
                                        System.out.println("Course not found.");
                                    }

                                } else if (choice == 3) {
                                    logged_In_Student.viewSchedule(); // View student's schedule
                                } else if (choice == 4) {
                                    logged_In_Student.trackProgress(); // Track academic progress
                                } else if (choice == 5) {
                                    System.out.print("Enter Course Code to Drop: ");
                                    String codeToDrop = scanner.nextLine();
                                    Course selectedCourseToDrop = findCourseByCode(codeToDrop, courses); // Find course to drop

                                    if (selectedCourseToDrop != null) {
                                        logged_In_Student.dropCourse(selectedCourseToDrop); // Drop the course
                                    } else {
                                        System.out.println("Course not found.");
                                    }
                                } else if (choice == 6) {
                                    System.out.print("Enter complaint description: ");
                                    String complaintDescription = scanner.nextLine();
                                    logged_In_Student.submitComplaint(complaintDescription);
                                    // Submit a complaint
                                } else if (choice == 7) {
                                    logged_In_Student.viewComplaints(); // View complaints
                                } else if (choice == 8) {
                                    System.out.print("Enter Course Code to Provide Feedback: ");
                                    String courseCod = scanner.nextLine();
                                    Course selectedCourse = findCourseByCode(courseCod, courses);

                                    if (selectedCourse != null) {
                                        if (logged_In_Student.getRegisteredCourses().contains(selectedCourse)) {
                                            System.out.print("Enter your rating (1-5): ");
                                            int rating = scanner.nextInt();
                                            scanner.nextLine(); // Consume newline

                                            // Validate the rating
                                            if (rating <= 0 || rating >= 6) {
                                                System.out.println("Invalid rating. Please enter a value between 1 and 5.");
                                            } else {
                                                // Rating is valid; proceed to submit the rating
                                                logged_In_Student.submitRating(selectedCourse, rating);

                                                System.out.print("Enter your feedback comment: ");
                                                String feedbackComment = scanner.nextLine();
                                                logged_In_Student.submitFeedback(selectedCourse, feedbackComment);

                                                System.out.println("Comment submitted successfully.");
                                            }
                                        } else {
                                            System.out.println("You are not registered in this course, so you cannot provide feedback.");
                                        }
                                    } else {
                                        System.out.println("Course not found.");
                                    }

                                } else if (choice == 9) {
                                    student_Active = false; // Logout
                                } else {
                                    System.out.println("Invalid choice. Please select a valid option.");
                                }
                            }
                        } else {
                            System.out.println("Invalid credentials. Please check your email and password.");
                        }
                    }
                }
                catch (InvalidLoginException e) {
                    System.out.println(e.getMessage());
                }



                break;

            case "signup":
                // Handle student signup
                System.out.println("Enter your name: ");
                String name_Signup = scanner.nextLine();
                System.out.println("Create your email address: ");
                String email_Signup = scanner.nextLine().toLowerCase();
                String password_Signup;
                while (true) {
                    System.out.println("Create your Password: ");
                    password_Signup = scanner.nextLine().toLowerCase();
                    System.out.println("Confirm your Password: ");
                    String passwordSignupCheck = scanner.nextLine().toLowerCase();
                    if (password_Signup.equals(passwordSignupCheck)) {
                        break; // Exit loop if passwords match
                    } else {
                        System.out.println("Passwords don't match. Please try again.");
                    }
                }

                students.add(new Student(email_Signup, password_Signup)); // Add new student
                System.out.println("Signup successful! Welcome aboard, " + name_Signup + "!");

                handleStudentTask(scanner, students, courses, currentSemester); // Handle subsequent operations
                break;

            default:
                System.out.println("Invalid input. Please try again.");
        }
    }

    // Constructor for Main class


    // Getter for complaints
    public static List<Complaint> getComplaints() {
        return complaints;
    }

    // Method to handle administrator login and their operations
    private static void handleAdminTask(Scanner scanner, List<Student> students, List<Professor> professors, List<ERP.Course> courses, List<ERP.Complaint> complaints) {
        String admin_Email = "admin@iiit.ac.in";
        String admin_Password = "adminpassword";

        // Admin login
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();


        if (email.equals(admin_Email) && password.equals(admin_Password)) {
            ERP.Administrator admin = new ERP.Administrator(email);

            boolean admin_Active = true; // Track if admin is active

            while (admin_Active) {
                displayAdMenu(); // Show admin menu

                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                // Handle admin menu choices
                if (choice == 1) {
                    admin.manageCatalog(courses); // Manage course catalog
                } else if (choice == 2) {
                    admin.manageStudentRecords(students); // Manage student records
                } else if (choice == 3) {
                    admin.assignProfessors(professors, courses); // Assign professors to courses
                } else if (choice == 4) {
                    admin.handleComplaints(complaints); // Handle complaints
                } else if (choice == 5) {
                    admin_Active = false; // Logout
                } else {
                    System.out.println("Invalid choice.");
                }
            }
        } else {
            System.out.println("Invalid credentials."); // Error message for failed login
        }
    }

    // Method to find a course by its code
    private static ERP.Course findCourseByCode(String code, List<ERP.Course> courses) {
        for (ERP.Course course : courses) {
            if (course.getCourseCode().equals(code)) {
                return course; // Return the found course
            }
        }
        return null; // Return null if course is not found
    }

    // Method to display the student menu
    private static void displayStudentMenu() {
        System.out.println("\nStudent Menu:");
        System.out.println("1. View Available Courses");
        System.out.println("2. Enroll in a Course");
        System.out.println("3. View Your Schedule");
        System.out.println("4. Track Academic Progress");
        System.out.println("5. Drop a Course");
        System.out.println("6. Submit a Complaint");
        System.out.println("7. Review Submitted Complaints");
        System.out.println("8. Provide Feedback");
        System.out.println("9. Logout");
    }

    // Method to display the administrator menu
    private static void displayAdMenu() {
        System.out.println("\nAdministrator Menu:");
        System.out.println("1. Manage Course Catalog");
        System.out.println("2. Manage Student Records");
        System.out.println("3. Assign Professors to Courses");
        System.out.println("4. Address Complaints");
        System.out.println("5. Logout");
    }
}
