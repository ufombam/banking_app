package bankingApp;
import java.util.*;
import java.io.Console;

public class Main {
	
	static int response1;
	static double amount;
	static String beneficiary;
	static String name;
	static String dob;
	static String phone;
	static String email;
	static String idNumber;
	static String BVN;
	static String accountNumber;
	static String password;
	static Scanner s = new Scanner(System.in);
	//		create a DB
	static UserDB myDB = new UserDB();
	
	
	
	
	
	static void scroll () {
		for (int i=0; i<50; i++) {
			System.out.println("");
		}
	}
	
//	create account
	static void createAccount() {
		String inputHolder;
		boolean validateName = false, validateDOB = false, validateNumber = false, validateEmail = false, validateID = false, validateBVN = false;
		@SuppressWarnings("unused")
		String beneficiaryAcc;

		System.out.println("Started account creation");
		
		
		while(!validateName) {
			System.out.println("ENTER YOUR FULL NAME");
			inputHolder = s.nextLine();
			if (inputHolder.matches("[a-zA-Z]+\\s+[A-Za-z]+")) {
				name = inputHolder.toUpperCase();
				validateName = true;
			} else {
				System.out.println("Invalid input. Please enter your full name (first and last) separated by a space.");
			}
		}
		
		while(!validateDOB) {
			System.out.println("ENTER YOUR DATE OF BIRTH dd/mm/yyyy");
			inputHolder = s.nextLine();
			if (inputHolder.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/[0-9]{4}$")) {
				dob = inputHolder;
				validateDOB = true;
			} else {
				System.out.println("Invalid input: Please use the format \"dd/mm/yyyy\" for your input.");
			}
		}
		
		while(!validateNumber) {
			System.out.println("ENTER YOUR PHONE NUMBER e.g 07012345678");
			inputHolder = s.nextLine();
			if (inputHolder.matches("^0[1789]\\d{9}$")) {
				phone = inputHolder;
				accountNumber = phone.substring(1);
				validateNumber = true;
			} else {
				System.out.println("Invalid input: Please use the specified format");
			}
		}
		
		while(!validateEmail) {
			System.out.println("ENTER YOUR EMAIL");
			inputHolder = s.nextLine();
			if (inputHolder.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
				email = inputHolder;
				validateEmail = true;
			} else {
				System.out.println("Invalid input: Please use a valid email address");
			}
		}
		
		
		while(!validateID) {
			System.out.println("ENTER YOUR ID NUMBER (Voters card, Drivers license)  max. 11 digits");
			inputHolder = s.nextLine();
			if (inputHolder.matches("\\d{11}")) {
				idNumber = inputHolder;
				validateID = true;
			}  else {
				System.out.println("Invalid input: Please enter a valid ID number");
			}
		}
		
		while(!validateBVN) {
			System.out.println("ENTER YOUR BVN");
			inputHolder = s.nextLine();
			if (inputHolder.matches("^22\\d{9}")) {
				BVN = inputHolder;
				validateBVN = true;
			} else {
				System.out.println("Invalid input: Please enter a valid BVN eg. 22XXXXXXX 11 digits max.");
			}
		}
		
//		collect password from console
		Console console = System.console();
		if (console == null) {
			System.out.println("No console available");
			return;
		}
		char[] passwordArray = console.readPassword("Please enter your security code. This will be used to login in the future");
		password = new String(passwordArray);
		
		scroll();
//		add new user to DB
		myDB.getUsers().add(new User(name, dob, phone, email, idNumber, BVN, password));
		
		System.out.println("Accout creation SUCCESSFUL");
		System.out.println("Hello, " + name + "! Welcome to OSIFO Bank!");
		System.out.println("Your Personal Account number is "+ accountNumber);

		System.out.println("Please login to continue");
//		Print Welcome message		
		loginAccount();
	}
	
//	Login user
	static void loginAccount() {
		String inputHolder;
		boolean validate = false;
		String accountNumberResponse = "";
		int responseHolder;		//Hold integer prompt
		@SuppressWarnings("unused")
		String beneficiaryAcc;
		boolean validUser = false; //validate user
		
		
		while (!validUser) {
			validate = false;
			while (!validate) {
				System.out.println("Please enter your account number");
				inputHolder = s.nextLine();
				if (inputHolder.matches("\\d{10}$")) {
					accountNumberResponse = inputHolder;
					validate = true;
				} else {
					System.out.println("Invalid input: Please enter a valid account number,not more than 10 digits long");
				}
				
//				collect password from console
				Console console = System.console();
				if (console == null) {
					System.out.println("No console available");
					return;
				}
				char[] passwordArray = console.readPassword("Please enter your security code to login: ");
				password = new String(passwordArray);
			}
			
			scroll();
	//		search the DB to find user and provide account features
			for (User usersList: myDB.getUsers())  {
				if (usersList.getPhone().substring(1).matches(accountNumberResponse) && usersList.getPassword().equals(password)) {
					validUser = true;
	//				Provide account features for the user
					Account userAccount = new Account(usersList.getName(), usersList.getDob(), usersList.getPhone(), usersList.getEmail(), usersList.getId(), usersList.getBvn(), usersList.getPassword() );
					System.out.println("LOGIN SUCCESSFUL!");
	//				Print Welcome message		
					System.out.println("Hello, " + userAccount.getName() + "! Welcome to OSIFO Bank! What would you like to do today ?");
					
					do {
						validate = false;
					System.out.println("DEPOSIT (1)");
					System.out.println("WITHDRAW (2)");
					System.out.println("MAKE A TRANSFER (3)");
					System.out.println("LOGOUT (0)");
					System.out.println("Enter a number to select");
					
	//				validate response
					while (!validate) {
						responseHolder = s.nextInt();
						s.nextLine();
						if (responseHolder >=0 && responseHolder <= 3) {
							response1 = responseHolder;
							validate = true;
						} else {
							System.out.println("Invalid input: Please enter a valid response");
						}
						
					}
					
	//				Performing account	operations	
					if (response1 == 0) {
						System.out.println("GOODBYE");
						System.exit(response1);
					}
					else {
						scroll();
						if (response1 == 1) {
							System.out.println("Please enter the deposit amount"); 	//Deposit
							validate = false;
							while (!validate) {
								if (s.hasNextDouble()) {
									amount = s.nextDouble();
									s.nextLine();
									validate = true;
									scroll();
									userAccount.deposit(amount);								
								} else {
									System.out.println("Invalid input: Please enter a valid one");
								}
							}
							
						} else if (response1 == 2) {
							System.out.println("Please enter the withdrawal amount");  	//Withdraw
							validate = false;
							while (!validate) {
								if (s.hasNextDouble()) {
									amount = s.nextDouble();
									s.nextLine();
									validate = true;
									scroll();
									userAccount.withdraw(amount);
								} else {
									System.out.println("Invalid input: Please enter a valid one");
								}
							}						
						} else if (response1 == 3) {
							System.out.println("Enter the 10 digits beneficiary account number");	//Transfer
							validate = false;
							while (!validate) {
								inputHolder = s.nextLine();
								if (inputHolder.matches("\\d{10}")) {
									beneficiaryAcc = inputHolder;
									validate = true;
								} else {
									System.out.println("Invalid input: Please enter a valid 10-digit account number");
								}	
							}	
								System.out.println("Please enter the transfer amount");
								validate = false;
								while(!validate) {
									if (s.hasNextDouble()) {
										amount = s.nextDouble();
										s.nextLine();
										validate = true;
										scroll();
										userAccount.transfer(amount);
									} else {
									System.out.println("Invalid input: Please enter a valid amount");
								}
							}
						}		
					}
					System.out.println("Perform another operation ?");
					System.out.println("YES (1)");
					System.out.println("EXIT APP (0)");
					
					validate = false;
					while (!validate) {
						inputHolder = s.nextLine();
						if (inputHolder.matches("[0-1]")) {
							validate = true;
							scroll();
							response1 = Integer.parseInt(inputHolder);
						} else {
							System.out.println("Invalid input: Please enter a valid response");
						}
					}
					
//					s.nextLine(); 
					} while (response1 > 0);
					System.out.println("GOODBYE");
				} 
				
			}
			if (!validUser) {
				System.out.println("USER NOT FOUND: Please check your password and account number combination");
				validate = false;
			}
		}
	}
	
	public static void main(String[] args) {
		
		boolean validate = false;
		String inputHolder;
		
//		add pseudo users
		myDB.getUsers().add(new User("Moses Ufomba", "12/03/1994", "07062679954", "ufombam@gmail.com", "123456789", "222222222222222", "123456789" ));
        myDB.getUsers().add(new User("Tochi Ebere", "24/09/1995", "08105562646", "tochiebby@gmail.com", "123456789", "222222222222223", "123456789"));
        
		System.out.println("WELCOME TO THE PAN AFRICAN BANK");
		System.out.println("What would you like to do");
		System.out.println("NEW USER ? CREATE AN ACCOUNT (1)");
		System.out.println("LOGIN (2)");
		System.out.println("CANCEL (0)");
		System.out.println("Enter a number to select");
		
		while (!validate) {
			inputHolder = s.nextLine();
			if (inputHolder.matches("[012]")) {
				response1 = Integer.valueOf(inputHolder);
				validate = true;
			} else {
				System.out.println("Invalid input: Please enter a valid response");
			}
		}
        
        if (response1 == 0) { //end operation
        	System.out.println("GOODBYE");
        	System.exit(response1);
        } else if (response1 == 1) { //create account
//        	s.nextLine();
        	
//		get operation code from input
        	createAccount();
			s.close();
        } else if (response1 == 2) {
//        	s.nextLine();
        	loginAccount();
        	s.close();
        }
	}
}
