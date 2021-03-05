package minimarketdemo.model.tester.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class Users
 */
@Stateless
@LocalBean
public class UsersService {

	/**
	 * Default constructor.
	 */

	private List<User> users;

	public UsersService() {
		this.users = new ArrayList<User>();
		this.init();
	}

	public List<User> getUsers() {
		return this.users;
	}

	private void init() {
		this.users.add(new User("Kevin", "Catucuamba", 23));
		this.users.add(new User("Leo", "Walker", 12));
		this.users.add(new User("Lenin", "Mcmanam", 23));
		this.users.add(new User("Wilson", "Jacob", 23));
	}

}
