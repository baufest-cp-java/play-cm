package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import play.db.ebean.Model;

@Entity
public class Contributor extends Model {
	private static final long serialVersionUID = -2636238505596421379L;

	@Id	
	private 	Long				id;
	private		String				name;
	@ManyToMany
	private 	Set<Contribution>	contributions;
	
	public Contributor() {
		this.contributions = new HashSet<Contribution>();
	}

	public Contributor(Long id, String name) {
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
	
	public Set<Contribution> getContributions() {
		return contributions;
	}

	public void setContributions(Set<Contribution> contributions) {
		this.contributions = contributions;
	}
	
	public void addContribution(Contribution contribution) {
		this.contributions.add(contribution);
	}

	public static Finder<Long, Contributor> find() {
		return new Finder<Long, Contributor>(Long.class, Contributor.class);
	}
	
}
