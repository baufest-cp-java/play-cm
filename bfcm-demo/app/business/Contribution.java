package business;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.ebean.Model;

@Entity
@Table(name = "contribution")
public class Contribution extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 760569544939796589L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(optional = false)
	@JoinColumn(name = "contribution_type_id", nullable = false)
	private ContributionType contributionType;
	@ManyToMany(cascade=CascadeType.REMOVE)
	private List<Contributor> contributors;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "evidence", nullable = false)
	private String evidence;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ContributionType getContributionType() {
		return contributionType;
	}

	public void setContributionType(ContributionType contributionType) {
		this.contributionType = contributionType;
	}

	public List<Contributor> getContributors() {
		return contributors;
	}

	public void setContributors(List<Contributor> contributors) {
		this.contributors = contributors;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEvidence() {
		return evidence;
	}

	public void setEvidence(String evidence) {
		this.evidence = evidence;
	}

}
