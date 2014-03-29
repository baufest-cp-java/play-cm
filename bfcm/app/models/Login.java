package models;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

public class Login extends Model{

	private static final long serialVersionUID = -5822957447701659564L;
	
	@Required
	public String username;
	@Required
	public String password;
	
//	public String validate() {
//		return "admin".equals(username) && "admin".equals(password) ? null : "No user found";
//	}
}
