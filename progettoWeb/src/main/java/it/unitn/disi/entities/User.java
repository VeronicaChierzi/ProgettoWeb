package it.unitn.disi.entities;

public class User {

	private int id;
	private String username;
	private String email;
	private String password;
	private String firstName;
	private String lastName;

	private UserSeller userSeller;
	private UserAdmin userAdmin;

	public User() {
	}

	public User(int id, String username, String email, String password, String firstName, String lastName) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public boolean isSeller() {
		return (userSeller != null);
	}

	public boolean isAdmin() {
		return (userAdmin != null);
	}

	// <editor-fold defaultstate="collapsed" desc="Getters e Setters">
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the userSeller
	 */
	public UserSeller getUserSeller() {
		return userSeller;
	}

	/**
	 * @param userSeller the userSeller to set
	 */
	public void setUserSeller(UserSeller userSeller) {
		this.userSeller = userSeller;
	}

	/**
	 * @return the userAdmin
	 */
	public UserAdmin getUserAdmin() {
		return userAdmin;
	}

	/**
	 * @param userAdmin the userAdmin to set
	 */
	public void setUserAdmin(UserAdmin userAdmin) {
		this.userAdmin = userAdmin;
	}
	// </editor-fold>

}
