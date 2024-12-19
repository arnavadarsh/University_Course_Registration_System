package ERP;

import java.util.*;

public class TeachingAssistant extends Student {
    private List<Course> Assistingcourses;


    public TeachingAssistant(String email, String password) {
        super(email, password);
        this.Assistingcourses = new ArrayList<>();
    }

    public TeachingAssistant(String email, String password, int semester) {
        super(email, password, semester);
        this.Assistingcourses = new ArrayList<>();
    }
    public void viewCourses(List<Course> courses) {
        System.out.println("Courses you are assisting:");
        for (Course c : Assistingcourses) {
            System.out.println(c.getCourseCode() + ": " + c.getTitle());
        }
    }
    public void studentsRegistered(List<Student> registeredStudents) {
        System.out.println("Students registered in the course:");
        for (Student student : registeredStudents) {
            System.out.println(student.getName() + " '" + student.getEmail() + "'");
        }
    }
    public void assignCourse(Course c) {
        Assistingcourses.add(c); }


    public List<Course> getAssistingcourses() {
        return this.Assistingcourses;
    }
    public void viewEnrolledStudents(List<Course> courses) {
        boolean found = false;

        for (Course c : Assistingcourses) {
            if (c.getTeachingAssistant().equals(this)) {
                found = true;
                System.out.println("Course: " + c.getTitle() + " '" + c.getCourseCode() + "'");

                List<Student> enrolledStudentsInCourse = c.getEnrolledStudents();

                if (enrolledStudentsInCourse.isEmpty()) {
                    System.out.println("No students are currently enrolled in this course.");
                } else {
                    System.out.println("Enrolled Students:");
                    for (Student student : enrolledStudentsInCourse) {

                        System.out.println("Name: " + student.getName() + ", Email: " + student.getEmail());
                    }
                }
                System.out.println();
            }
        }

        if (!found) {
            System.out.println("You are not assigned any course to assist currently.");
        }
    }

    private void updatingGrades(Scanner scanner,Student studentToUpdate,Course selectedCourse) {
        System.out.println("Updating the grades for the selected student");
        Map<String, Double> gradesOfStudent = studentToUpdate.getGrades();
        System.out.print("Enter new grade for " + selectedCourse.getTitle() + " (leave blank if you dont want to make changes): ");
        String newGradeString = scanner.nextLine();
        if (!newGradeString.isEmpty()) {
            try {
                double newGrade = Double.parseDouble(newGradeString);
                gradesOfStudent.put(selectedCourse.getCourseCode(), newGrade);
                System.out.println("Grade for " + selectedCourse.getTitle() + " updated to " + newGrade);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for grade. Please enter a valid number.");
            }
        }
        studentToUpdate.setGrades(gradesOfStudent);

    }
    private void viewingGrades(Student studentToUpdate,Course selectedCourse){
        Map<String, Double> Viewgrades = studentToUpdate.getGrades();
        String courseTitle = selectedCourse.getTitle();
        String CourseCode = selectedCourse.getCourseCode();

        if (Viewgrades.containsKey(CourseCode)) {
            double grade = Viewgrades.get(CourseCode);
            System.out.println("Course: " + courseTitle + " ' " + CourseCode + "' - Grade: " + grade);
        } else {
            System.out.println("Course: " + courseTitle + " '" + CourseCode + "' - Grade not yet assigned.");
        }

    }
    public void updateGrades(List<Course> courses) {
        Scanner scanner = new Scanner(System.in);
        viewCourses(courses);
        System.out.print("Enter the course code to update or view the grades: ");
        String courseCode = scanner.nextLine();
        Course selectedCourse = null;


        for (Course c : Assistingcourses) {
            if (c.getCourseCode().equalsIgnoreCase(courseCode)) {
                selectedCourse = c;
                break;
            }
        }

        if (selectedCourse == null) {
            System.out.println("Invalid course code.");
            return;
        }


        List<Student> registeredStudents = selectedCourse.getEnrolledStudents();

        if (registeredStudents.isEmpty()) {
            System.out.println("No students registered in this course.");
            return;
        }


        studentsRegistered(registeredStudents);


        System.out.print("Enter the student's email to update or to view grades: ");
        String studentEmail = scanner.nextLine();
        Student studentToUpdate = null;


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


        System.out.print("Would you like to update the grades? (Yes to view or No to skip): ");
        String updateGrades = scanner.nextLine();

        if (updateGrades.equalsIgnoreCase("yes")) {
            System.out.println("Updating the grades for the selected student");
            Map<String, Double> gradesOfStudent = studentToUpdate.getGrades();
            System.out.print("Enter new grade for " + selectedCourse.getTitle() + " (leave blank if you dont want to make changes): ");
            String newGradeString = scanner.nextLine();
            if (!newGradeString.isEmpty()) {
                try {
                    double newgrade = Double.parseDouble(newGradeString);
                    gradesOfStudent.put(selectedCourse.getCourseCode(), newgrade);
                    System.out.println("Grade for " + selectedCourse.getTitle() + " updated to " + newgrade);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input for grade. Please enter a valid number.");
                }
            }
            studentToUpdate.setGrades(gradesOfStudent);

        }


        System.out.print("Would you like to view the grades? (Yes to view or No to skip): ");
        String viewgrades = scanner.nextLine();

        if (viewgrades.equalsIgnoreCase("yes")) {

            Map<String, Double> Viewgrades = studentToUpdate.getGrades();
            String courseTitle = selectedCourse.getTitle();
            String Coursecode = selectedCourse.getCourseCode();

            if (Viewgrades.containsKey(Coursecode)) {
                double grade = Viewgrades.get(Coursecode);
                System.out.println("Course: " + courseTitle + " ' " + Coursecode + "' - Grade: " + grade);
            } else {
                System.out.println("Course: " + courseTitle + " '" + Coursecode + "' - Grade not yet assigned.");
            }
        }
    }

    public void setCoursesAssisting(List<Course> coursesAssisting) {

        this.Assistingcourses = coursesAssisting;
    }



}
