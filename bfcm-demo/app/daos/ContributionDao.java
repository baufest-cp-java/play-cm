package daos;

import java.util.List;

import javax.persistence.PersistenceException;

import play.db.ebean.Model.Finder;
import business.Contribution;
import exceptions.DaoException;

public class ContributionDao {

	private Finder<Long, Contribution> finder = new Finder<Long, Contribution>(
			Long.class, Contribution.class);

	public List<Contribution> all() {
		return finder.all();
	}

	public Contribution findById(Long id) {
		return finder.byId(id);
	}

	public void save(Contribution contribution) throws DaoException {
		try {
			contribution.save();
		} catch (PersistenceException pe) {
			throw new DaoException("Error al crear la contribución", pe);
		}
	}

	public void update(Contribution contribution) throws DaoException {
		try {
			contribution.update();
		} catch (PersistenceException pe) {
			throw new DaoException("Error al modificar la contribución", pe);
		}
	}

	public void delete(Contribution contribution) throws DaoException {
		try {
			contribution.delete();
		} catch (PersistenceException pe) {
			throw new DaoException("Error al eliminar la contribución", pe);
		}
	}
}
