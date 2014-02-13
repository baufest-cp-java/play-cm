package services;

import java.util.List;

import business.Contribution;
import daos.ContributionDao;
import exceptions.DaoException;
import exceptions.ServiceException;

public class ContributionService {

	private ContributionDao contributionDao = new ContributionDao();

	public List<Contribution> all() {
		return contributionDao.all();
	}

	public Contribution findById(Long id) {
		return contributionDao.findById(id);
	}

	public void save(Contribution contribution) throws ServiceException {
		try{
			contributionDao.save(contribution);
		} catch (DaoException de) {
			throw new ServiceException(de.getMessage(), de);
		}
	}

	public void update(Contribution contribution) throws ServiceException {
		try{
			contributionDao.update(contribution);
		} catch (DaoException de) {
			throw new ServiceException(de.getMessage(), de);
		}
	}

	public void delete(Contribution contribution) throws ServiceException {
		try{
			contributionDao.delete(contribution);
		} catch (DaoException de) {
			throw new ServiceException(de.getMessage(), de);
		}
	}
}
