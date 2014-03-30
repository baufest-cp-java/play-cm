package models;

import play.data.validation.Constraints.Required;

public class Login {

	@Required
	private String username;
	@Required
	private String password;

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String validate() {
		return "admin".equals(username) && "admin".equals(password) ? null : "No user found";
	}
}
