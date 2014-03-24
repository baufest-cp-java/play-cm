package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class ContributionType extends Model {
	private static final long serialVersionUID = 7965172086105278922L;
	
	@Id
	private Long	id;
	private	String	name;
	
	public ContributionType(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static Finder<Long, ContributionType> find() {
		return new Finder<Long, ContributionType>(Long.class, ContributionType.class);
	}
	
}
