package business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;
import enums.Area;
import enums.Period;
import enums.Unit;

@Entity
@Table(name = "contribution_type")
public class ContributionType extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4205875958801269412L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "code", length = 10, nullable = false, unique = true)
	private String code;
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	@Column(name = "short_name", length = 20, nullable = false)
	private String shortName;
	@Column(name = "area", length = 50, nullable = false)
	private Area area;
	@Column(name = "score", nullable = false)
	private Integer score;
	@Column(name = "unit", length = 50, nullable = false)
	private Unit unit;
	@Column(name = "period", length = 50, nullable = false)
	private Period period;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "sequence", length = 5, nullable = false)
	private String sequence;
	@Column(name = "motivation", nullable = false)
	private String motivation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getMotivation() {
		return motivation;
	}

	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}

}
