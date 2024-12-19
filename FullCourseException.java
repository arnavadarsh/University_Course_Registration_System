package ERP;

public class FullCourseException extends Exception{
    public FullCourseException(String courseName){
        super("Cannot register for "+ courseName+" : Course enrollment limit has reached.");
    }
}
