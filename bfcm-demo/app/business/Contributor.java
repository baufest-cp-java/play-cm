package business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import enums.Rol;
import enums.Unity;
import play.db.ebean.Model;

@Entity
@Table(name = "contributor")
public class Contributor extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1815239094500741011L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "code", length = 10, nullable = false, unique = true)
	private String code;
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	@Column(name = "mail", length = 150, nullable = false, unique = true)
	private String mail;
	@Column(name = "rol", length = 10, nullable = false)
	private Rol rol;
	@Column(name = "unity", length = 10, nullable = false)
	private Unity unity;

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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Unity getUnity() {
		return unity;
	}

	public void setUnity(Unity unity) {
		this.unity = unity;
	}

}
