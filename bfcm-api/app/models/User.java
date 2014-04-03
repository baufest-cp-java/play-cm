package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class User extends Model {

	private static final long serialVersionUID = 4765963555598365516L;
	private static final Finder<Long, User> finder = new Finder<Long, User>(Long.class, User.class);
	
	@Id	
	public Long			id;
	@Required
	public String		code;
	@Required
	public String		name;
	@Required
	public String		surname;
	@Required @Email
	public	String		email;
	@Required
	public	String		username;
	@Required
	public String		password;
	@Required
	public Role			role;

	public static User find(String username, String password) {
		return finder.where().eq("username", username).eq("password", password).findUnique();
	}

}
