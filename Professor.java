package ERP;

import java.util.*;

public class Professor extends User {
    private final List<ERP.Course> courses; // List of courses taught by the professor

    // Constructor to initialize Professor object with email and password
    public Professor(String email, String password) {
        super(email, password); // Call to the parent User class constructor
        this.courses = new ArrayList<>(); // Initialize the courses list
    }

    // Override to return the professor's email
    @Override
    public String getEmail() {
        return super.getEmail(); // Get email from the parent class
    }
    public void viewCourseFeedback(Course course) {
        System.out.println("Feedback for course: " + course.getCourseCode());
        course.displayFeedback();
    }

    // Method to view students enrolled in courses taught by the professor
    public void viewEnrolledStudents(Professor professor, List<ERP.Course> courses) {
        boolean foundCourses = false; // Flag to check if any courses are found

        for (ERP.Course course : courses) {
            if (course.getProfessor().equals(professor)) { // Check if the professor teaches the course
                foundCourses = true; // Set flag to true
                System.out.println("Course: " + course.getTitle() + " (" + course.getCourseCode() + ")");

                List<Student> enrolledStudents = course.getEnrolledStudents(); // Get the list of enrolled students

                // Check if there are no enrolled students
                if (enrolledStudents.isEmpty()) {
                    System.out.println("No students are currently enrolled in this course.");
                } else {
                    System.out.println("Enrolled Students:");
                    for (Student student : enrolledStudents) {
                        // Display each enrolled student's name and email
                        System.out.println("Name: " + student.getName() + ", Email: " + student.getEmail());
                    }
                }
                System.out.println(); // Print a new line for better readability
            }
        }

        // If no courses were found for the professor
        if (!foundCourses) {
            System.out.println("The professor is not teaching any courses currently.");
        }
    }

    // Method to update course details based on user input
    public void update_course_details(List<ERP.Course> allCourses) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Course Code to Update: ");
        String codeToUpdate = scanner.nextLine(); // Get the course code to update
        ERP.Course courseToUpdate = findCourseByCode(codeToUpdate, allCourses); // Find the course by code

        if (courseToUpdate != null) { // Check if the course exists
            // Prompt for new course details and update accordingly
            System.out.print("Enter the new course title (leave blank to retain the current title): ");
            String newTitle = scanner.nextLine();
            System.out.print("Enter the new number of credits (leave blank to keep the current credits): ");
            String newCredits = scanner.nextLine();
            System.out.print("Enter the new course timings (leave blank to maintain the current timings): ");
            String newTimings = scanner.nextLine();
            System.out.print("Enter the new syllabus details (leave blank to preserve the existing syllabus): ");
            String newSyllabus = scanner.nextLine();
            System.out.print("Enter the new prerequisites (comma-separated course codes, leave blank to keep the current prerequisites): ");
            String newPrerequisites = scanner.nextLine();
            System.out.print("Enter the new enrollment limit (leave blank to keep the current limit): ");
            String newEnrollmentLimit = scanner.nextLine();
            System.out.print("Enter the new office hours (leave blank to maintain the current office hours): ");
            String newOfficeHours = scanner.nextLine();

            // Update course details if new values are provided
            if (!newTitle.isEmpty()) courseToUpdate.setTitle(newTitle);
            if (!newCredits.isEmpty()) courseToUpdate.setCredits(Integer.parseInt(newCredits));
            if (!newTimings.isEmpty()) courseToUpdate.setTimings(newTimings);
            if (!newSyllabus.isEmpty()) courseToUpdate.setSyllabus(newSyllabus);
            if (!newPrerequisites.isEmpty())
                courseToUpdate.setPrerequisites(newPrerequisites, allCourses);
            if (!newEnrollmentLimit.isEmpty())
                courseToUpdate.setEnrollmentLimit(Integer.parseInt(newEnrollmentLimit));
            if (!newOfficeHours.isEmpty()) courseToUpdate.setOfficeHours(newOfficeHours);

            System.out.println("Course updated successfully."); // Confirmation message
        } else {
            System.out.println("Course not found."); // Error message if course doesn't exist
        }
    }

    // Method to manage courses (update details or exit)
    public void manageCourses(List<ERP.Course> allCourses) {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true; // Flag to control the loop

        while (keepRunning) {
            System.out.println("Manage Courses:\n1. Update Course Details\n2. Exit\nChoose an option: ");

            int choice = scanner.nextInt(); // Get user choice
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    update_course_details(allCourses); // Update course details
                    break;

                case 2:
                    keepRunning = false; // Exit the loop
                    System.out.println("Exiting course management.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again."); // Error message for invalid choice
            }
        }
    }

    // Helper method to find a course by its code
    private ERP.Course findCourseByCode(String code, List<ERP.Course> courses) {
        for (ERP.Course course : courses) {
            if (course.getCourseCode().equals(code)) { // Check for matching course code
                return course; // Return the found course
            }
        }
        return null; // Return null if not found
    }

    // Getter for the list of courses taught by the professor
    public List<ERP.Course> getCourses() {
        return new ArrayList<>(courses); // Return a new list to prevent external modification
    }

    public static void update_student(Professor loggedInProfessor, List<Course> allCourses) {
        Scanner scanner = new Scanner(System.in);

        // Extract the courses managed by the professor
        List<Course> professorCourses = new ArrayList<>();
        for (Course course : allCourses) {
            if (course.getProfessor().equals(loggedInProfessor)) {
                professorCourses.add(course); // Add the course to the list managed by the professor
            }
        }

        if (professorCourses.isEmpty()) {
            System.out.println("You are not managing any courses.");
            return;
        }

        System.out.println("Courses you are managing:");
        for (Course course : professorCourses) {
            System.out.println(course.getCourseCode() + ": " + course.getTitle());
        }

        System.out.print("Enter the course code to update/view grades: ");
        String courseCode = scanner.nextLine();
        Course selectedCourse = null;

        // Find the selected course
        for (Course course : professorCourses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                selectedCourse = course;
                break;
            }
        }

        if (selectedCourse == null) {
            System.out.println("Invalid course code.");
            return;
        }

        // Get the students registered in the selected course
        List<Student> registeredStudents = selectedCourse.getEnrolledStudents();

        if (registeredStudents.isEmpty()) {
            System.out.println("No students registered in this course.");
            return;
        }

        System.out.println("Students registered in the course:");
        for (Student student : registeredStudents) {
            System.out.println(student.getName() + " (" + student.getEmail() + ")");
        }

        // Choose a student to update/view grades
        System.out.print("Enter the student's email to update/view grades: ");
        String studentEmail = scanner.nextLine();
        Student studentToUpdate = null;

        // Find the student to update
        for (Student student : registeredStudents) {
            if (student.getEmail().equalsIgnoreCase(studentEmail)) {
                studentToUpdate = student;
                break;
            }
        }

        if (studentToUpdate == null) {
            System.out.println("Student not found in this course.");
            return;
        }

        // Update the grades
        System.out.print("Would you like to update the grades? (Type 'yes' to update or 'no' to skip): ");
        String updateGrades = scanner.nextLine();

        if (updateGrades.equalsIgnoreCase("yes")) {
            System.out.println("Updating the grades for the selected student...");
            Map<String, Double> grades = studentToUpdate.getGrades();
            System.out.print("Enter new grade for " + selectedCourse.getTitle() + " (leave blank to maintain the current grade): ");
            String newGradeStr = scanner.nextLine();
            if (!newGradeStr.isEmpty()) {
                try {
                    double newGrade = Double.parseDouble(newGradeStr);
                    grades.put(selectedCourse.getCourseCode(), newGrade); // Update the grade
                    System.out.println("Grade for " + selectedCourse.getTitle() + " updated to " + newGrade);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input for grade. Please enter a valid number.");
                }
            }
            studentToUpdate.setGrades(grades); // Ensure the student has the updated grades
        }

        // View the grades
        System.out.print("Would you like to view the grades? (Type 'yes' to view or 'no' to skip): ");
        String viewGrades = scanner.nextLine();

        if (viewGrades.equalsIgnoreCase("yes")) {
            // Get the student's grades map
            Map<String, Double> Viewgrades = studentToUpdate.getGrades();
            String courseTitle = selectedCourse.getTitle(); // Get course title
            String CourseCode = selectedCourse.getCourseCode(); // Get course code

            // Check if the student has a grade for the course
            if (Viewgrades.containsKey(CourseCode)) {
                double grade = Viewgrades.get(CourseCode); // Retrieve grade
                System.out.println("Course: " + courseTitle + " (" + CourseCode + ") - Grade: " + grade);
            } else {
                System.out.println("Course: " + courseTitle + " (" + CourseCode + ") - Grade not yet assigned.");
            }
        }
    }
    public void viewAssignedCourses(List<Course> allCourses) {
        List<Course> assignedCourses = new ArrayList<>();

        for (Course course : allCourses) {
            if (course.getProfessor().equals(this)) {
                assignedCourses.add(course);
            }
        }

        if (assignedCourses.isEmpty()) {
            System.out.println("The professor is not assigned to any courses.");
        } else {
            System.out.println("Courses assigned to Professor " + getEmail() + ":");
            for (Course course : assignedCourses) {
                System.out.println("Course Code: " + course.getCourseCode() +
                        ", Title: " + course.getTitle() +
                        ", Credits: " + course.getCredits() +
                        ", Enrollment Limit: " + course.getEnrollmentLimit());
            }
        }
    }
    // Method to assign a Teaching Assistant to a course
    public void assignTeachingAssistant(List<ERP.Course> allCourses, List<TeachingAssistant> teachingAssistants) {
        Scanner scanner = new Scanner(System.in);
        List<Course> assignedCourses = new ArrayList<>();
        for(Course course : allCourses) {
            if(course.getProfessor().equals(this)) {
                assignedCourses.add(course);
            }
        }
        // Display the list of courses the professor teaches

        if(assignedCourses.isEmpty()) {
            System.out.println("No courses assigned to any teaching assistant.");
        }
        else {
            System.out.println("Courses you are managing:");
            List<Course> professorCourses = getCourses(); // Assuming this method is already implemented
            for (Course course : assignedCourses) {
                System.out.println(course.getCourseCode() + ": " + course.getTitle());
            }

            System.out.print("Enter the course code to assign a TA: ");
            String courseCode = scanner.nextLine();

            // Find the selected course
            Course selectedCourse = null;
            for (Course course : assignedCourses) {
                if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                    selectedCourse = course;
                    break;
                }
            }

            if (selectedCourse == null) {
                System.out.println("Invalid course code.");
                return;
            }

            // Display available TAs
            System.out.println("Available Teaching Assistants:");
            for (TeachingAssistant ta : teachingAssistants) {
                System.out.println(ta.getEmail());
            }

            System.out.print("Enter the email of the TA to assign: ");
            String taEmail = scanner.nextLine();

            // Find the selected TA
            TeachingAssistant selectedTA = null;
            for (TeachingAssistant ta : teachingAssistants) {
                if (ta.getEmail().equalsIgnoreCase(taEmail)) {
                    selectedTA = ta;
                    break;
                }
            }

            if (selectedTA == null) {
                System.out.println("Invalid TA email.");
                return;
            }

            // Assign the TA to the course
            selectedCourse.setTeachingAssistant(selectedTA);
            selectedTA.assignCourse(selectedCourse);
            System.out.println("Teaching Assistant " + selectedTA.getEmail() + " has been assigned to " + selectedCourse.getTitle() + ".");
        }
        }



}

