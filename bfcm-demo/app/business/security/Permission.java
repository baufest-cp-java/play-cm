package business.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;
@Entity
@Table(name = "permission")
public class Permission extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4768453356198632899L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "section", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private Section section;
	@Column(name = "action", length = 10, nullable = false)
	@Enumerated(EnumType.STRING)
	private Action action;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

}
