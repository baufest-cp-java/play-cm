package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Contribution extends Model {

	private static final long 	serialVersionUID = 760569544939796589L;

	@Id
	private Long				id;
	@Required
	private String				title;
	@ManyToOne 
	@Required
	private ContributionType	contributionType;
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "contributions")
	@Required
	private Set<Contributor>	contributors;
	private String				description;
	
	public Contribution() {
		this.contributors = new HashSet<Contributor>();
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
	
	public ContributionType getContributionType() {
		return contributionType;
	}

	public void setContributionType(ContributionType contributionType) {
		this.contributionType = contributionType;
	}

	public Set<Contributor> getContributors() {
		return contributors;
	}

	public void setContributors(Set<Contributor> contributors) {
		this.contributors = contributors;
	}
	
	public void addContributor(Contributor contributor) {
		this.contributors.add(contributor);
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static Finder<Long, Contribution> find() {
		return new Finder<Long, Contribution>(Long.class, Contribution.class);
	}
}
