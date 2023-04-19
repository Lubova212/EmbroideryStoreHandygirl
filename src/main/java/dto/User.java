package dto;

public class User {

    private int id;
    private String userName;
    private String password;
    private String fullName;
    private String email;
    private float balance;

    public User(String userName, String password, String fullName, String email, float balance) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.balance = balance;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName() {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance;
    }
}
