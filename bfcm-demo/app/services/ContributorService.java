package services;

import java.util.List;

import business.Contributor;
import daos.ContributorDao;
import exceptions.DaoException;
import exceptions.ServiceException;

public class ContributorService {

	private ContributorDao contributorDao = new ContributorDao();

	public List<Contributor> all() {
		return contributorDao.all();
	}

	public Contributor findById(Long id) {
		return contributorDao.findById(id);
	}

	public Contributor findByCode(String code) {
		return contributorDao.findByCode(code);
	}

	public void save(Contributor contributor) throws ServiceException {
		try {
			contributorDao.save(contributor);
		} catch (DaoException de) {
			throw new ServiceException(de.getMessage(), de);
		}
	}

	public void update(Contributor contributor) throws ServiceException {
		try {
			contributorDao.update(contributor);
		} catch (DaoException de) {
			throw new ServiceException(de.getMessage(), de);
		}
	}

	public void delete(Contributor contributor) throws ServiceException {
		try {
			contributorDao.delete(contributor);
		} catch (DaoException de) {
			throw new ServiceException(de.getMessage(), de);
		}
	}
}
