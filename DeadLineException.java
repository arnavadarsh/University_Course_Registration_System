package ERP;

public class DeadLineException extends Exception{
    public DeadLineException(String courseName){
        super("Cannnot drop "+ courseName+" : The deadline to withdraw has already passed..");
    }
}
