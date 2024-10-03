package bankingApp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account extends User {
	
//	Account features
	
	public Account(String name, String dob, String phone, String email, String idNumber, String BVN, String accountNumber) {
		super(name, dob, phone, email, idNumber, BVN, accountNumber);
		// TODO Auto-generated constructor stub
	}
	
//	Get date and time
	LocalDateTime dateNow = LocalDateTime.now();
	DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
	String formattedDate = dateNow.format(format);

	
	public void deposit (double amount) {
		if (amount <= 0) {
			System.out.println("Please enter a valid amount");
		}
		setAccountBalance(Math.round((getBalance() + amount) * 100.0)/ 100.0);
		System.out.println("Desposit successful!");
		System.out.println("Account Balance: " + getBalance());
		System.out.println(formattedDate);
	}
	public void withdraw (double amount) {
		if (getBalance() < amount) {
			System.out.println("Sorry you do not have sufficient funds to perform this operation, your current account balance is " + getBalance());
		} else {
		setAccountBalance(Math.round((getBalance() - amount) * 100.0)/ 100.0);
		System.out.println("-" + amount+ " has been debited from your account ");
		System.out.println("Account Balance: " + getBalance());
		System.out.println(formattedDate);
		}
	}
	public void transfer (double amount) {
		if (getBalance() < amount) {
			System.out.println("Sorry you do not have sufficient funds to perform this operation, your current account balance is " + getBalance());
		} else {
		setAccountBalance(Math.round((getBalance() - amount) * 100.0)/ 100.0);
		System.out.println("-" + amount+ " has been debited from your account ");
		System.out.println("Account Balance: " + getBalance());
		System.out.println(formattedDate);
		}
	}

}











