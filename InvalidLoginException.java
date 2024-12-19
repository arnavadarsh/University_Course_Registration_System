package ERP;

public class InvalidLoginException extends Exception {
    public InvalidLoginException(String user) {
        super("Invalid attempt to login: " + user);
    }
}
