package ERP;

import java.util.*;

class Course {
    private final String courseCode; // Unique code for the course
    private String title; // Title of the course
    private Professor professor; // Professor teaching the course
    private TeachingAssistant teachingAssistant; // Teaching Assistant for the course
    private int credits; // Number of credits for the course
    private final List<Course> prerequisites; // List of prerequisite courses
    private String timings; // Class timings
    private final List<Student> enrolledStudents; // List of students enrolled in the course
    private String syllabus; // Course syllabus
    private int enrollmentLimit; // Maximum number of students allowed to enroll
    private String officeHours; // Professor's office hours
    private int semester; // Semester in which the course is offered
    private List<Feedback<?>> feedbacks;



    // Constructor to initialize a Course object
    public Course(String courseCode, String title, Professor professor, int credits, int semester, List<Course> prerequisites, String timings) {
        this.courseCode = courseCode; // Set the course code
        this.title = title; // Set the course title
        this.professor = professor; // Assign the professor
        this.credits = credits; // Set the number of credits
        this.prerequisites = prerequisites != null ? prerequisites : new ArrayList<>(); // Initialize prerequisites list
        this.timings = timings; // Set class timings
        this.enrolledStudents = new ArrayList<>(); // Initialize the list of enrolled students
        this.semester = semester; // Set the semester for the course
        this.syllabus = "Not defined"; // Default syllabus
        this.enrollmentLimit = 50; // Default enrollment limit
        this.officeHours = "Not set"; // Default office hours
        this.teachingAssistant = null; // Initialize without a teaching assistant
        this.feedbacks = new ArrayList<>();

    }

    // Getter and setter for the Teaching Assistant
    public TeachingAssistant getTeachingAssistant() {
        return teachingAssistant; // Return the teaching assistant
    }

    public List<Feedback<?>> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback<?>> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public void setTeachingAssistant(TeachingAssistant teachingAssistant) {
        this.teachingAssistant = teachingAssistant; // Assign a teaching assistant to the course
    }

    // Getters and Setters for various properties
    public int getSemester() {
        return semester; // Return the semester
    }

    public void setSemester(int semester) {
        this.semester = semester; // Update the semester
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents; // Return the list of enrolled students
    }

    public String getCourseCode() {
        return courseCode; // Return the course code
    }

    public String getTitle() {
        return title; // Return the course title
    }

    public void setTitle(String title) {
        this.title = title; // Update the course title
    }

    public Professor getProfessor() {
        return professor; // Return the assigned professor
    }

    public void setProfessor(Professor professor) {
        this.professor = professor; // Update the professor
    }

    public int getCredits() {
        return credits; // Return the number of credits
    }

    public void setCredits(int credits) {
        this.credits = credits; // Update the number of credits
    }

    public List<Course> getPrerequisites() {
        return prerequisites; // Return the list of prerequisites
    }

    // Method to set prerequisites based on a string input
    public void setPrerequisites(String prerequisitesStr, List<Course> allCourses) {
        this.prerequisites.clear(); // Clear current prerequisites
        if (prerequisitesStr != null && !prerequisitesStr.isEmpty()) {
            String[] courseCodes = prerequisitesStr.split(","); // Split the input string
            for (String code : courseCodes) {
                code = code.trim(); // Trim whitespace
                for (Course course : allCourses) {
                    // Find matching course and add to prerequisites
                    if (course.getCourseCode().equalsIgnoreCase(code)) {
                        this.prerequisites.add(course);
                        break; // Stop searching once found
                    }
                }
            }
        }
    }

    public String getTimings() {
        return timings; // Return the class timings
    }

    public void setTimings(String timings) {
        this.timings = timings; // Update class timings
    }

    public String getSyllabus() {
        return syllabus; // Return the syllabus
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus; // Update the syllabus
    }

    public int getEnrollmentLimit() {
        return enrollmentLimit; // Return the enrollment limit
    }

    public void setEnrollmentLimit(int enrollmentLimit) {
        this.enrollmentLimit = enrollmentLimit; // Update the enrollment limit
    }

    public String getOfficeHours() {
        return officeHours; // Return the office hours
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours; // Update the office hours
    }

    // Method to enroll a student in the course
    public boolean enrollStudent(Student student) {
        // Check if there's space and the student isn't already enrolled
        if (enrolledStudents.size() < enrollmentLimit && !enrolledStudents.contains(student)) {
            enrolledStudents.add(student); // Add student to the list
            return true; // Enrollment successful
        }
        return false; // Enrollment failed (either limit reached or student already enrolled)
    }

    // Overridden toString method for easy representation of the course
    @Override
    public String toString() {
        return "Course{" +
                "courseCode='" + courseCode + '\'' +
                ", title='" + title + '\'' +
                ", professor=" + professor +
                ", teachingAssistant=" + (teachingAssistant != null ? teachingAssistant.getEmail() : "None") +
                ", credits=" + credits +
                ", prerequisites=" + prerequisitesToString() +
                ", timings='" + timings + '\'' +
                ", syllabus='" + syllabus + '\'' +
                ", enrollmentLimit=" + enrollmentLimit +
                ", officeHours='" + officeHours + '\'' +
                ", semester=" + semester +
                '}';
    }

    // Helper method to format prerequisites for output
    private String prerequisitesToString() {
        if (prerequisites.isEmpty()) {
            return "None"; // No prerequisites
        }
        StringBuilder prereqStr = new StringBuilder(); // StringBuilder for efficient string concatenation
        for (Course prereq : prerequisites) {
            prereqStr.append(prereq.getTitle()).append(", "); // Append each prerequisite title
        }
        return prereqStr.substring(0, prereqStr.length() - 2); // Return formatted string without trailing comma
    }
    public void courseFeedback(Feedback<?> feedback) {
        feedbacks.add(feedback);
    }

    // Method to display all feedbacks
    public void displayFeedback() {
        if (feedbacks.isEmpty()) {
            System.out.println("No feedback available for this course.");
        } else {
            for (Feedback<?> feedback : feedbacks) {
                System.out.println(feedback);
            }
        }
    }
}
