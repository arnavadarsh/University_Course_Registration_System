package ERP;

import java.util.*;

class Student extends User {
    private int semester; // Current semester of the student
    private String name; // Name of the student
    private int credits; // Total credits earned by the student
    private final List<Course> courses; // List of courses taken by the student
    private Map<String, Double> grades; // Mapping of course codes to grades
    private List<Complaint> complaints; // List of complaints submitted by the student
    private List<Course> registeredCourses; // List of courses the student is currently registered in

    // Constructor to initialize a new Student object with email and password
    public Student(String email, String password) {
        super(email, password); // Call to the parent User class constructor
        this.semester = 1; // Default semester
        this.courses = new ArrayList<>(); // Initialize courses list
        this.grades = new HashMap<>(); // Initialize grades map
        this.complaints = new ArrayList<>(); // Initialize complaints list
        this.registeredCourses = new ArrayList<>(); // Initialize registered courses list
    }

    // Overloaded constructor for initializing with more attributes
    public Student(String email, String password, List<Complaint> complaints, List<Course> courses, int credits, Map<String, Double> grades, String name, List<Course> registeredCourses, int semester) {
        super(email, password);
        this.complaints = complaints;
        this.courses = courses;
        this.credits = credits;
        this.grades = grades;
        this.name = name;
        this.registeredCourses = registeredCourses;
        this.semester = semester;
    }

    // Overloaded constructor with default registered courses
    public Student(String email, String password, List<Complaint> complaints, List<Course> courses, int credits, Map<String, Double> grades, String name, List<Course> registeredCourses) {
        super(email, password);
        this.complaints = complaints;
        this.courses = courses;
        this.credits = credits;
        this.grades = grades;
        this.name = name;
        this.registeredCourses = registeredCourses;
    }

    // Method to represent the Student object as a string
    @Override
    public String toString() {
        return "Student{" +
                "complaints=" + complaints +
                ", semester=" + semester +
                ", name='" + name + '\'' +
                ", credits=" + credits +
                ", courses=" + courses +
                ", grades=" + grades +
                ", registeredCourses=" + registeredCourses +
                '}';
    }

    // Overloaded constructor to initialize with only semester
    public Student(String email, String password, int semester) {
        super(email, password);
        this.semester = semester; // Set semester
        this.courses = new ArrayList<>();
        this.grades = new HashMap<>();
        this.complaints = new ArrayList<>();
        this.registeredCourses = new ArrayList<>();
    }

    // Getter for the student's name
    public String getName() {
        return name;
    }

    // Setter for the student's name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for the student's grades
    public Map<String, Double> getGrades() {
        return grades;
    }

    // Setter for the student's grades
    public void setGrades(Map<String, Double> grades) {
        this.grades = grades;
    }

    // Setter for the student's complaints
    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    // Getter for the courses taken by the student
    public List<Course> getCourses() {
        return courses;
    }

    // Getter for the current semester
    public int getSemester() {
        return semester;
    }

    // Setter for the current semester
    public void setSemester(int semester) {
        this.semester = semester;
    }

    // Getter for the total credits earned by the student
    public int getCredits() {
        return credits;
    }

    // Setter for the total credits
    public void setCredits(int credits) {
        this.credits = credits;
    }

    // Getter for the list of complaints submitted by the student
    public List<Complaint> getComplaints() {
        return complaints;
    }

    // Method to add a complaint to the student's complaint list
    public void addComplaint(Complaint complaint) {
        this.complaints.add(complaint);
    }

    // Getter for the list of registered courses
    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    // Setter for the registered courses
    public void setRegisteredCourses(List<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }
    public void printCourses(List<Course> courses,int currentSemester) {
        for (Course course : courses) {
            // Check if the course is available in the current semester
            if (courseIsAvailableInCurrentSemester(course, currentSemester)) {
                System.out.println(course); // Print course details
            }
        }

    }
    // Method to view available courses for the current semester
    public void viewCourses(List<Course> courses, int currentSemester) {
        String y="";
        if(currentSemester==1)
            y="st";
        else if(currentSemester==2)
            y="nd";
        else if(currentSemester==3)
            y="rd";
        else
            y="th";

        System.out.println("Available Courses for the " + currentSemester + y+" Semester ");
        printCourses(courses,currentSemester);

    }



    // Method to register for a course
    public void registerCourse(Course course, int currentSemester) throws FullCourseException {
        // Check if the course is available in the current semester
        if (course.getSemester() != currentSemester) {
            System.out.println("Course is not available in this semester.");
            return;
        }

        // Check if prerequisites are completed
        if (!isPrerequisiteCompleted(course)) {
            System.out.println("You have not completed the prerequisites for this course.");
            return;
        }

        // Check if registering for the course exceeds the credit limit
        if (!canRegisterForCourse(course)) {
            System.out.println("You cannot register for this course as it will exceed your credit limit.");
            return;
        }
        if (course.getEnrolledStudents().size() >= course.getEnrollmentLimit()) {
            throw new FullCourseException("Failed to enroll in the course: " + course.getCourseCode() + ". Enrollment limit reached.");
        }


        registeredCourses.add(course); // Add course to registered courses
        credits += course.getCredits(); // Update total credits

        // Attempt to enroll in the course
        if (course.enrollStudent(this)) {
            System.out.println("Successfully registered for the course: " + course.getCourseCode());
        } else if(course.getEnrolledStudents().size()==course.getEnrollmentLimit()) {
            System.out.println("Failed to enroll in the course: " + course.getCourseCode() + " as enrollment limit is reached");
        }
        else{
            System.out.println("Failed to enroll in the course: " + course.getCourseCode());
        }
    }

    // Method to check if the student has completed the prerequisites for a course
    private boolean isPrerequisiteCompleted(Course course) {
        List<Course> prerequisites = course.getPrerequisites();
        List<Course> completedCourses = getCompletedCourses(); // Get completed courses
        return registeredCourses.containsAll(prerequisites); // Check if all prerequisites are met
    }

    // Method to check if the student can register for a course based on credit limit
    private boolean canRegisterForCourse(Course course) {
        int totalCredits = getTotalCredits(); // Get current total credits
        return (totalCredits + course.getCredits()) <= 20; // Check against credit limit
    }

    // Method to view the student's schedule
    public void viewSchedule() {
        System.out.println("Weekly Schedule:");
        for (Course course : registeredCourses) {
            // Print course title, timings, and professor's email
            System.out.println(course.getTitle() + " - " + course.getTimings() + " | Professor: " + course.getProfessor().getEmail());
        }
    }

    // Method to track academic progress (SGPA/CGPA calculation)
    public void trackProgress() {
        if (grades.isEmpty()) {
            System.out.println("No grades available to calculate SGPA/CGPA.");
            return;
        }
        boolean x=true;
        double totalCredits = 0;
        double totalPoints = 0;
        for (Course course : registeredCourses) {
            if (grades.get(course.getCourseCode()) == null) {
                x = false; // If no grade is found for the course, set x to false
                break; // Exit the loop if we find a missing grade
            }
        }





        if(x==true) {// Calculate total credits and points
            for (Course course : registeredCourses) {
                if (grades.containsKey(course.getCourseCode())) {
                    double grade = grades.get(course.getCourseCode());
                    totalCredits += course.getCredits();
                    totalPoints += grade * course.getCredits();
                }
            }

            double SGPA = totalPoints / totalCredits; // Calculate SGPA
            double CGPA = totalPoints / totalCredits; // Calculate CGPA (assumed same as SGPA)

            System.out.printf("SGPA: %.2f ", SGPA);
            System.out.printf("CGPA: %.2f ", CGPA);
        }
        else{
            System.out.println("No grades available since all courses grades are not alloted yet");
        }
    }

    // Method to drop a registered course
    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course); // Remove course from registered list
            credits -= course.getCredits(); // Update total credits
            System.out.println("Dropped " + course.getTitle());
        } else {
            System.out.println("Not registered for " + course.getTitle());
        }
    }

    // Method to submit a complaint
    public void submitComplaint(String description) {
        Complaint complaint = new Complaint(description); // Create new complaint
        complaints.add(complaint); // Add to complaints list
        System.out.println("Complaint submitted: " + description);
    }

    // Method to view all complaints submitted by the student
    public void viewComplaints() {
        if (complaints.isEmpty()) {
            System.out.println("No complaints submitted.");
        } else {
            System.out.println("Submitted Complaints:");
            for (Complaint complaint : complaints) {
                // Print each complaint's description and status
                System.out.println("Complaint: " + complaint.getDescription() + ", Status: " + complaint.getStatus());
            }
        }
    }
    void invalidRating(int rating)
    {

            System.out.println("Invalid rating. Please enter a value between 1 and 5.");
    }
    public void submitRating(Course course, int rating) {
        if (rating <=0 || rating >=6) {
            invalidRating(rating);
        } else {
            Feedback<Integer> feedback = new Feedback<>(this.name, rating);
            course.courseFeedback(feedback);
            System.out.println("Rating submitted successfully.");
        }
    }

    public void submitFeedback(Course course, String comment) {
        Feedback<String> feedback = new Feedback<>(this.name, comment);
        course.courseFeedback(feedback);
        System.out.println("Comment submitted successfully.");
    }

    // Method to assign a grade for a course
    public void assignGrade(Course course, double grade) {
        if (registeredCourses.contains(course)) {
            grades.put(course.getCourseCode(), grade); // Add grade to grades map
            System.out.println("Grade assigned for " + course.getTitle() + ": " + grade);
        } else {
            System.out.println("Cannot assign grade. Not registered for " + course.getTitle());
        }
    }

    // Method to calculate total credits earned from registered courses
    private int getTotalCredits() {
        int totalCredits = 0;
        for (Course course : registeredCourses) {
            totalCredits += course.getCredits(); // Sum up credits from registered courses
        }
        return totalCredits; // Return total credits
    }

    // Setter for total credits (not commonly used)
    public void setTotalCredits(int totalCredits) {
        this.credits = totalCredits;
    }

    // Method to get a list of completed courses based on grades
    private List<Course> getCompletedCourses() {
        List<Course> completedCourses = new ArrayList<>();
        for (Map.Entry<String, Double> entry : grades.entrySet()) {
            String courseCode = entry.getKey();
            if (entry.getValue() != null) {
                Course course = findCourseByCode(courseCode, courses); // Find course by code
                if (course != null) {
                    completedCourses.add(course); // Add to completed courses
                }
            }
        }
        return completedCourses; // Return completed courses
    }

    // Static method to find a course by its code
    private static Course findCourseByCode(String code, List<Course> courses) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(code)) {
                return course; // Return the found course
            }
        }
        return null; // Return null if not found
    }

    // Method to check if a course is available in the current semester
    private boolean courseIsAvailableInCurrentSemester(Course course, int currentSemester) {
        return course.getSemester() == currentSemester; // Check course availability
    }
}
