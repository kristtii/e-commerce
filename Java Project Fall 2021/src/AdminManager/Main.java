package AdminManager;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {

	static Admin admin;

	static ArrayList<Seller> sellers = new ArrayList<>();
	static ArrayList<Customer> customers = new ArrayList<>();
	static ArrayList<Product> products = new ArrayList<>();

	static String role;
	static String name;

	public static void main(String[] args) {
		admin = new Admin("Admin", "Admin", "Kristi", "Zoto", "11.04.2002");
		loadFromFile();
		chooseRole();
		if (role != null) {
			if (role.equals("Admin")) {
				adminMode();
			}
			else if (role.equals("Seller")){
				sellerMode();
			}
			else if (role.equals("Customer")) {
				customerMode();
			}
			saveToFile();
		}
	}

	public static void chooseRole() {
		String tempRole = JOptionPane.showInputDialog("What role do you want to be?\n1- Admin\n2- Seller\n3- Customer");

		String username = "";
		String password = "";

		if (tempRole != null) {
			if (tempRole.equals("1")) {
				username = JOptionPane.showInputDialog("Type in the predefined Admin username");
				password = JOptionPane.showInputDialog("Type in the predefined Admin password");

				if (username != null && password != null) {
					if (username.equals("Admin") && password.equals("Admin")) {
						role = "Admin";
					}
					else {
						System.err.println("Something went wrong, restart the program and try again");
					}
				}
				else {
					System.err.println("Something went wrong, restart the program and try again");
				}
			}
			else if (tempRole.equals("2")) {
				username = JOptionPane.showInputDialog("Type in a Seller's username");
				password = JOptionPane.showInputDialog("Type in a Seller's password");

				if (username != null && password != null) {

					for (int i = 0; i < sellers.size(); i++) {
						if (username.equals(sellers.get(i).getUsername()) && password.equals(sellers.get(i).getPassword())) {
							role = "Seller";
							name = sellers.get(i).getUsername();
							break;
						}
						else {
							System.err.println("Something went wrong, restart the program and try again");
						}
					}
				}
				else {
					System.err.println("Something went wrong, restart the program and try again");
				}
			}
			else if (tempRole.equals("3")) {

				String temp = JOptionPane.showInputDialog("What do you want to do?\n1 = Log into a customer\n2 = Create new Customer");
				if (temp.equals("1")) {
					username = JOptionPane.showInputDialog("Type in a Customer's username");
					password = JOptionPane.showInputDialog("Type in a Customer's password");

					if (username != null && password != null) {

						for (int i = 0; i < customers.size(); i++) {
							if (username.equals(customers.get(i).getUsername()) && password.equals(customers.get(i).getPassword())) {
								role = "Customer";
								name = customers.get(i).getUsername();
								break;
							} else {
								System.err.println("Something went wrong, restart the program and try again");
							}
						}
					}
				}
				else if (temp.equals("2")) {
					String tempName = JOptionPane.showInputDialog("What's the name of the new customer?");
					String tempSurname = JOptionPane.showInputDialog("What's the surname of the new customer?");
					String tempBirth = JOptionPane.showInputDialog("What's the date of birth of the new customer?");
					String tempUsername = JOptionPane.showInputDialog("What's the username of the new customer?");
					String tempPassword = JOptionPane.showInputDialog("What's the password of the new customer?");

					Customer newCustomer = new Customer(tempName, tempSurname, tempBirth, tempUsername, tempPassword);
					customers.add(newCustomer);
					role = "Customer";
				}
				else {
					System.err.println("Something went wrong, restart the program and try again");
				}
			}
			else {
				System.err.println("This role does not exist!");
			}
		}
	}

	public static void adminMode() {

		while(true) {
			String temp = JOptionPane.showInputDialog("Do you want to create a new Seller?\n1- yes\n2- no");

			if (temp != null) {
				if (temp.equals("1")) {
					String tempName = JOptionPane.showInputDialog("What's the name of the new seller?");
					String tempSurname = JOptionPane.showInputDialog("What's the surname of the new seller?");
					String tempBirth = JOptionPane.showInputDialog("What's the date of birth of the new seller?");
					String tempUsername = JOptionPane.showInputDialog("What's the username of the new seller?");
					String tempPassword = JOptionPane.showInputDialog("What's the password of the new seller?");

					Seller newSeller = new Seller(tempName, tempSurname, tempBirth, tempUsername, tempPassword);
					sellers.add(newSeller);
				} else {
					temp = JOptionPane.showInputDialog("Do you want to see a list of all entities?\n- yes\n- everything else ends session");
					if (temp != null) {
						if (temp.equals("yes")) {
							System.out.println("List of all sellers:");
							for (int i = 0; i < sellers.size(); i++) {
								System.out.println(sellers.get(i).getName() + ", " + sellers.get(i).getSurname() + ", " + sellers.get(i).getDateOfBirth()
										+ ", " + sellers.get(i).getUsername() + ", " + sellers.get(i).getPassword() + ", personID = " + sellers.get(i).getPersonID());
							}
							System.out.println("\nList of all customers:");
							for (int i = 0; i < customers.size(); i++) {
								System.out.println(customers.get(i).getName() + ", " + customers.get(i).getSurname() + ", " + customers.get(i).getDateOfBirth()
										+ ", " + customers.get(i).getUsername() + ", " + customers.get(i).getPassword() + ", personID = " + customers.get(i).getPersonID());
							}
							System.out.println("\nList of all products:");
							for (int i = 0; i < products.size(); i++) {
								System.out.println(products.get(i).getProductName() + ", " + products.get(i).getProductCategory() + ", " +
										 products.get(i).getProductDescription() + ", " + products.get(i).getProductPrice());
							}
						}
						else {
							break;
						}
					}
				}
			}
			else {
				break;
			}
		}
	}

	public static void sellerMode() {

		while (true) {

			String temp = JOptionPane.showInputDialog("What do you want to do?\n1 = Create product" +
					"\n2 = See list of all customers\n3 = See list of all Products\n4 = Get own Information\n5 = End session");
			if (temp.equals("1")) {
				String tempID = JOptionPane.showInputDialog("What's the name of the new product?");
				String tempCategory = JOptionPane.showInputDialog("What's the category of the new product?");
				String tempDescription = JOptionPane.showInputDialog("What's the description of the new product?");
				String tempPrice = JOptionPane.showInputDialog("What's the price of the new product?");

				try {
					Product newProduct = new Product(tempID, tempCategory, tempDescription, Double.parseDouble(tempPrice));
					products.add(newProduct);

				}
				catch (Exception e) {
					System.err.println("The format of price has to be a double");
				}

			}
			else if (temp.equals("2")) {
				System.out.println("\nList of all customers:");
				for (int i = 0; i < customers.size(); i++) {
					System.out.println(customers.get(i).getName() + ", " + customers.get(i).getSurname() + ", " + customers.get(i).getDateOfBirth()
							+ ", " + customers.get(i).getUsername() + ", " + customers.get(i).getPassword() + ", personID = " + customers.get(i).getPersonID());
				}
			}
			else if (temp.equals("3")) {
				System.out.println("\nList of all products:");
				for (int i = 0; i < products.size(); i++) {
					System.out.println(products.get(i).getProductName() + ", " + products.get(i).getProductCategory() + ", " +
							products.get(i).getProductDescription() + ", " + products.get(i).getProductPrice());
				}
			}
			else if (temp.equals("4")) {

				for (int i = 0; i < sellers.size(); i++) {
					if (sellers.get(i).getUsername().equals(name)) {
						sellers.get(i).getOwnInformation();
						break;
					}
				}
			}
			else if (temp.equals("5")) {
				break;
			}
		}
	}

	public static void customerMode() {

		while (true) {
			String temp = JOptionPane.showInputDialog("What do you want to do?\n1 - See list of all products\n2 - Get own information\n3 - End session");

			if (temp.equals("1")) {
				System.out.println("\nList of all products:");
				for (int i = 0; i < products.size(); i++) {
					System.out.println(products.get(i).getProductName() + ", " + products.get(i).getProductCategory() + ", " +
							products.get(i).getProductDescription() + ", " + products.get(i).getProductPrice());
				}
			}
			else if (temp.equals("2")) {

				for (int i = 0; i < customers.size(); i++) {
					if (customers.get(i).getUsername().equals(name)) {
						customers.get(i).getOwnInformation();
						break;
					}
				}
			}
			else if (temp.equals("3")) {
				break;
			}
		}
	}

	public static void saveToFile()  {

		File file = new File("C:\\sellersAndCustomers.txt");

		try {
			FileWriter writer = new FileWriter(file);

			for (int i = 0; i < sellers.size(); i++) {
				writer.write("Seller, ");
				writer.write(sellers.get(i).getName() + ", ");
				writer.write(sellers.get(i).getSurname() + ", ");
				writer.write(sellers.get(i).getDateOfBirth() + ", ");
				writer.write(sellers.get(i).getUsername() + ", ");
				writer.write(sellers.get(i).getPassword() + ", \n");
			}

			for (int i = 0; i < customers.size(); i++) {
				writer.write("Customer, ");
				writer.write(customers.get(i).getName() + ", ");
				writer.write(customers.get(i).getSurname() + ", ");
				writer.write(customers.get(i).getDateOfBirth() + ", ");
				writer.write(customers.get(i).getUsername() + ", ");
				writer.write(customers.get(i).getPassword() + ", \n");
			}

			for (int i = 0; i < products.size(); i++) {
				writer.write("Product, ");
				writer.write(products.get(i).getProductName() + ", ");
				writer.write(products.get(i).getProductCategory() + ", ");
				writer.write(products.get(i).getProductDescription() + ", ");
				writer.write(products.get(i).getProductPrice() + ", \n");
			}

			
			writer.close();
		}
		catch(IOException e) {
			System.err.println("Something with loading data into file went wrong!");
		}
	}

	public static void loadFromFile() {

		Path path = Paths.get("C:\\sellersAndCustomers.txt");

		try {
			String data = Files.readString(path);
			String[] temp= data.split(", ");

			for (int i = 0; i < temp.length; i++) {

				if (temp[i].contains("Seller")) {
					Seller newSeller = new Seller(temp[i + 1], temp[i + 2], temp[i + 3], temp[i + 4], temp[i + 5]);
					sellers.add(newSeller);
					i += 5;
				} else if (temp[i].contains("Customer")) {
					Customer newCustomer = new Customer(temp[i + 1], temp[i + 2], temp[i + 3], temp[i + 4], temp[i + 5]);
					customers.add(newCustomer);
					i += 5;
				} else if (temp[i].contains("Product")) {
					Product newProduct = new Product(temp[i + 1], temp[i + 2], temp[i + 3], Double.parseDouble(temp[i + 4]));
					products.add(newProduct);
					i += 4;
				}
			}
		}
		catch(IOException e) {
			System.err.println("Something went wrong with loading data from file into ArrayLists");
		}
	}
}