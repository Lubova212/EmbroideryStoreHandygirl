package dto;

public class Admin {

    private int id;
    private String userName;
    private String fullName;

    public Admin(String userName, String fullName) {
        this.userName = userName;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'';
    }
}
