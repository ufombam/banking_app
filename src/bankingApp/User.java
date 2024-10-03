package bankingApp;

public class User {
	private String name;
	private String dob;
	private String phone;
	private String email;
	private String idNumber;
	private String BVN;
	private String accountNumber;
	private String password;
	private double accountBalance = 0.00;
	
	public User (String name, String dob, String phone, String email, String idNumber, String BVN, String password) {
		this.name = name;
		this.dob = dob;
		this.phone = phone;
		this.email = email;
		this.idNumber = idNumber;
		this.BVN = BVN;
		this.password = password;
	}
	public User (String name, String dob, String phone, String email, String idNumber, String BVN, Double accountBalance, String password) {
		this.name = name;
		this.dob = dob;
		this.phone = phone;
		this.email = email;
		this.idNumber = idNumber;
		this.BVN = BVN;
		this.accountBalance = accountBalance;
		this.password = password;
	}
	
	//Setters for account variables
	
	public void setName (String n) {
		name = n;
	}
	public void setDob (String d) {
		this.dob = d;
	}
	public void setPhone (String p) {
		this.phone = p;
	}
	public void setEmail (String e) {
		this.email = e;
	}
	public void setId (String i) {
		this.idNumber = i;
	}
	public void setBvn (String b) {
		this.BVN = b;
	}
	public void setAccountBalance (Double b) {
		this.accountBalance = b;
	}
	public void setPassword (String p) {
		this.password = p;
	}
	
	//getters for fields
	
	public String getName () {
		return name;
	}
	public String getDob () {
		return this.dob;
	}
	public String getPhone () {
		return this.phone;
	}
	public String getEmail () {
		return this.email;
	}
	public String getId () {
		return this.idNumber;
	}
	public String getBvn () {
		return this.BVN;
	}
	public String getAccountNumber () {
		return this.accountNumber;
	}
	public double getBalance() {
		return this.accountBalance;
	}
	public String getPassword() {
		return this.password;
	}
}




