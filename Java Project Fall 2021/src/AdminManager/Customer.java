package AdminManager;

public class Customer extends Person implements User{
	
    private String username;
    private String password;

    public Customer(String name, String surname, String dateOfBirth, String username, String password){
        super(name, surname, dateOfBirth);
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String customerUserName) {
        this.username = customerUserName;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String customerPassword) {
        this.password = customerPassword;
    }

    @Override
    public String toString() {
        return "This is a Customer";
    }

    @Override
    public void getOwnInformation() {
        System.out.println(this.getName() + " " + this.getSurname() + " " + this.getDateOfBirth() + " " +
                this.getPassword() + " " + this.getUsername());
    }
}