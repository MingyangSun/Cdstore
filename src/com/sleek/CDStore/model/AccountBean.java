package com.sleek.CDStore.model;
/**
 */
public class AccountBean {
	protected int id;
	protected String username;
	protected String password;
	protected String firstname;
	protected String lastname;
	protected String email;
	protected String phone;
	/** get id
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/** get username
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**get password
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/** get firstname
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/** get lastname
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/** get email
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**set id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**set username
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/** set the password
	 * @param password 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/** set the first name
	 * @param firstname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/** set the last name
	 * @param lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**set the email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	protected AccountBean clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (AccountBean) super.clone();
	}
	/** get phone number
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/** set phone number
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
