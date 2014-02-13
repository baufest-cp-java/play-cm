package models;

import java.util.List;

import javax.validation.Valid;

import play.data.validation.Constraints.Required;

public class ContributionForm {

	public Long id;
	@Required
	public String contributionTypeCode;
	@Valid
	public List<ContributorCode> contributors;
	@Required
	public String description;
	@Required
	public String evidence;

}
