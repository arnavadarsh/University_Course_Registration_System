package ERP;



public class Feedback<T> {
    private T feedbackInfo;   // Feedback can be numeric or text-based
    private String studentName;

    public Feedback(String studentName, T feedbackInfo) {
        this.studentName = studentName;
        this.feedbackInfo = feedbackInfo;
    }

    public T getFeedbackInfo() {
        return feedbackInfo;
    }

    public String getStudentName() {
        return studentName;
    }

    @Override
    public String toString() {
        if (feedbackInfo instanceof Number) {
            return String.format("Rating by "+studentName+":" +feedbackInfo);
        } else {
            return String.format("Rating by "+studentName+":" +feedbackInfo);
        }
    }
}