package ERP;

public interface ERP_APP {
    boolean login(String email, String password);
    void logout();
    String getEmail();
    void setEmail(String email);
    String getPassword();
    void setPassword(String password);
}