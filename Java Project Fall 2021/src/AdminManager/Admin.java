package AdminManager;

public class Admin extends Person{

    private String username;
    private String password;

    public Admin(String adminUserName, String adminPassword, String name, String surname, String dateOfBirth){
        super(name, surname, dateOfBirth);
        
        this.username = adminUserName;
        this.password = adminPassword;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String adminUserName) {
        this.username = adminUserName;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String adminPassword) {
        this.password = adminPassword;
    }

    @Override
    public String toString() {
        return "This is a Admin";
    }
}