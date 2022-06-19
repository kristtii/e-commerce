package AdminManager;

public class Seller extends Person implements User{
	
    private String username;
    private String password;

    public Seller(String name, String surname, String dateOfBirth, String username, String password) {
        super(name, surname, dateOfBirth);
        
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String sellerUserName) {
        this.username = sellerUserName;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String sellerPassword) {
        this.password = sellerPassword;
    }

    @Override
    public String toString() {
        return "This is a Seller";
    }

    @Override
    public void getOwnInformation() {
        System.out.println(this.getName() + " " + this.getSurname() + " " + this.getDateOfBirth() + " " +
                this.getPassword() + " " + this.getUsername());
    }
}