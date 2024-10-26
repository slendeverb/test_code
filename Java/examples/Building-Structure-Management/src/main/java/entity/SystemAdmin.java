package entity;

public class SystemAdmin {
    private String email;
    private String password;

    public SystemAdmin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public SystemAdmin() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
