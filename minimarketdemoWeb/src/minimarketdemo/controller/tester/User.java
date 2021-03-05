package minimarketdemo.controller.tester;

public class User {

	private String name;
	private String lastname;
	private Integer id;

	public User(String name, String lastname, Integer id) {
		this.name = name;
		this.lastname = lastname;
		this.id = id;
	}

	public User() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
