package models;

import java.util.List;

import play.data.validation.Constraints.Required;
import business.security.Section;

public class LoginForm {

	@Required
	public String username;
	@Required
	public String password;
	List<Section> sections;
}
