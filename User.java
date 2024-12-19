package ERP;

// User class implements the ERP_APP interface, representing a user in the ERP system
public class User implements ERP_APP {
    protected String email; // User's email address
    protected String password; // User's password

    // Constructor to initialize a User with only an email
    public User(String email) {
        this.email = email; // Set the user's email
    }

    // Constructor to initialize a User with an email and a password
    public User(String email, String password) {
        this.email = email; // Set the user's email
        this.password = password; // Set the user's password
    }

    // Override toString method for a string representation of the User object
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' + // Display user's email
                '}';
    }

    // Implement login method from ERP_APP interface
    // Checks if the provided email and password match the user's credentials
    @Override
    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password); // Return true if login is successful
    }

    // Implement logout method from ERP_APP interface
    // Prints a message indicating that the user has logged out
    @Override
    public void logout() {
        System.out.println(email + " logged out."); // Log out message
    }

    // Getter for the user's email
    @Override
    public String getEmail() {
        return email; // Return user's email
    }

    // Setter for the user's email
    @Override
    public void setEmail(String email) {
        this.email = email; // Update user's email
    }

    // Getter for the user's password
    @Override
    public String getPassword() {
        return password; // Return user's password
    }

    // Setter for the user's password
    @Override
    public void setPassword(String password) {
        this.password = password; // Update user's password
    }
}
