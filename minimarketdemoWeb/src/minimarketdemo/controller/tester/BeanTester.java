package minimarketdemo.controller.tester;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class BeanTester implements Serializable {

	private String value;

	List<User> users;

	private User newUser;

	public BeanTester() {
		this.users = new ArrayList<User>();
		this.users.add(new User("Kevin", "Catucuamba", 1));
		this.users.add(new User("David", "Rea", 2));
		this.users.add(new User("Erick", "Tyson", 3));
		this.users.add(new User("Emerson", "Ulloa", 4));
	}

	public String getValue() {
		return value;
	}

	@PostConstruct
	public void init() {
		this.value = "Hello JSF";
		this.newUser = new User();
	}

	public void actionCreateUser() {
		this.users.add(this.newUser);
		this.newUser = null;
		this.newUser = new User();
	}

	public void editText() {
		this.value = "HELLO KEVIN";
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

	private static final long serialVersionUID = 1L;
}
