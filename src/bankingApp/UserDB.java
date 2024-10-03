package bankingApp;
import java.util.*;

public class UserDB {
	private ArrayList<User> users = new ArrayList<>();
	
//	get/add a new user
	public ArrayList<User> getUsers() {
		return users;
	}
//	replace the entire user list
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
}
