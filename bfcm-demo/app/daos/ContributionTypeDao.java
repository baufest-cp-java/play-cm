package daos;

import java.util.List;

import javax.persistence.PersistenceException;

import exceptions.DaoException;
import business.ContributionType;
import play.db.ebean.Model.Finder;

public class ContributionTypeDao {

	private Finder<Long, ContributionType> finder = new Finder<Long, ContributionType>(
			Long.class, ContributionType.class);

	public List<ContributionType> all() {
		return finder.all();
	}

	public ContributionType findById(Long id) {
		return finder.byId(id);
	}

	public ContributionType findByCode(String code) {
		return finder.where().eq("code", code).findUnique();
	}

	public void save(ContributionType contributionType) throws DaoException {
		try {
			contributionType.save();
		} catch (PersistenceException pe) {
			throw new DaoException("Error al crear el tipo de contribución", pe);
		}
	}

	public void update(ContributionType contributionType) throws DaoException {
		try {
			contributionType.update();
		} catch (PersistenceException pe) {
			throw new DaoException("Error al modificar el tipo de contribución", pe);
		}
	}

	public void delete(ContributionType contributionType) throws DaoException {
		try {
			contributionType.delete();
		} catch (PersistenceException pe) {
			throw new DaoException("Error al eliminar el tipo de contribución",pe);
		}
	}
}
