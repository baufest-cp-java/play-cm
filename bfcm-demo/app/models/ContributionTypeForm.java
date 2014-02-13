package models;

import enums.Area;
import enums.Period;
import enums.Unit;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;

public class ContributionTypeForm {

	public Long id;
	@Required
	@MaxLength(value = 10)
	public String code;
	@Required
	@MaxLength(value = 100)
	public String name;
	@Required
	@MaxLength(value = 20)
	public String shortName;
	@Required
	public Area area;
	@Required
	public Integer score;
	@Required
	public Unit unit;
	@Required
	public Period period;
	@Required
	public String description;
	@Required
	@MaxLength(value = 5)
	public String sequence;
	@Required
	public String motivation;

}
