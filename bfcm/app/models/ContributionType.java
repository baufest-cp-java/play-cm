package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class ContributionType extends Model {
	private static final long serialVersionUID = 7965172086105278922L;
	
	@Id
	private Long	id;
	private String	code;
	private	String	name;
	private Long	score;
	
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
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public static Finder<Long, ContributionType> find() {
		return new Finder<Long, ContributionType>(Long.class, ContributionType.class);
	}
	
}
