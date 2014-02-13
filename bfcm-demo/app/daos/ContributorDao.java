package daos;

import java.util.List;

import javax.persistence.PersistenceException;

import play.db.ebean.Model.Finder;
import business.Contributor;
import exceptions.DaoException;

public class ContributorDao {

	private Finder<Long, Contributor> finder = new Finder<Long, Contributor>(
			Long.class, Contributor.class);

	public List<Contributor> all() {
		return finder.all();
	}

	public Contributor findById(Long id) {
		return finder.byId(id);
	}

	public Contributor findByCode(String code) {
		return finder.where().eq("code", code).findUnique();
	}

	public void save(Contributor contributor) throws DaoException {
		try {
			contributor.save();
		} catch (PersistenceException pe) {
			throw new DaoException("Error al crear el contribuyente", pe);
		}
	}

	public void update(Contributor contributor) throws DaoException {
		try {
			contributor.update();
		} catch (PersistenceException pe) {
			throw new DaoException("Error al modificar el contribuyente", pe);
		}
	}

	public void delete(Contributor contributor) throws DaoException {
		try {
			contributor.delete();
		} catch (PersistenceException pe) {
			throw new DaoException("Error al eliminar el contribuyente", pe);
		}
	}
}
