package models;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;
import enums.Rol;
import enums.Unity;

public class ContributorForm {

	public Long id;
	@Required
	@MaxLength(value = 10)
	public String code;
	@Required
	@MaxLength(value = 100)
	public String name;
	@Required
	@Email
	@MaxLength(value = 150)
	public String mail;
	@Required
	public Rol rol;
	@Required
	public Unity unity;

}
