package models;

import exceptions.AuthenticationException;
import play.data.validation.Constraints.Required;
import services.AuthenticationService;

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
}
