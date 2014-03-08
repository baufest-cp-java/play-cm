package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Contribution extends Model {

	private static final long 	serialVersionUID = 760569544939796589L;

	@Id
	private Long				id;
	private String				title;
	private Set<Contributor>	contributors;
	
	public Contribution() {
		this.contributors = new HashSet<Contributor>();
	}

	public Contribution(Long id, String title, Set<Contributor> contributors) {
		this.id = id;
		this.title = title;
		this.contributors = contributors;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Contributor> getContributors() {
		return contributors;
	}

	public void setContributors(Set<Contributor> contributors) {
		this.contributors = contributors;
	}
	
	public static Finder<Long, Contribution> find() {
		return new Finder<Long, Contribution>(Long.class, Contribution.class);
	}
}