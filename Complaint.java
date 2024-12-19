package ERP;

public class Complaint {
    private final String description; // Description of the complaint
    private String status; // Current status of the complaint (e.g., Pending, Resolved)
    private boolean resolved; // Indicates whether the complaint has been resolved

    // Constructor to initialize a Complaint with a description
    public Complaint(String description) {
        this.description = description; // Set the description
        this.status = "Pending"; // Default status when a complaint is created
    }

    // Method to set a new status for the complaint
    public void setStatus(String status) {
        this.status = status; // Update the status
    }

    // Method to get the current status of the complaint
    public String getStatus() {
        return status; // Return the current status
    }

    // Method to get the description of the complaint
    public String getDescription() {
        return description; // Return the description
    }

    // Method to update the status of the complaint
    public void updateStatus(String newStatus) {
        this.status = newStatus; // Update the status to the new value
    }

    // Overridden toString method for easy representation of the complaint
    @Override
    public String toString() {
        return "Complaint: " + description + ", Status: " + status; // Format output
    }

    // Method to set the resolved status (currently empty)
    public void setResolved(boolean resolved) {
        // Future implementation can go here if needed
    }

    // Method to check if the complaint is resolved
    public boolean isResolved() {
        return resolved; // Return the resolved status
    }
}
