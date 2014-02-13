package services;

import java.util.List;

import business.ContributionType;
import daos.ContributionTypeDao;
import exceptions.DaoException;
import exceptions.ServiceException;

public class ContributionTypeService {

	private ContributionTypeDao contributionTypeDao = new ContributionTypeDao();

	public List<ContributionType> all() {
		return contributionTypeDao.all();
	}

	public ContributionType findById(Long id) {
		return contributionTypeDao.findById(id);
	}
	
	public ContributionType findByCode(String code) {
		return contributionTypeDao.findByCode(code);
	}

	public void save(ContributionType contributionType) throws ServiceException {
		try {
			contributionTypeDao.save(contributionType);
		} catch (DaoException de) {
			throw new ServiceException(de.getMessage(), de);
		}
	}

	public void update(ContributionType contributionType) throws ServiceException {
		try {
			contributionTypeDao.update(contributionType);
		} catch (DaoException de) {
			throw new ServiceException(de.getMessage(), de);
		}
	}

	public void delete(ContributionType contributionType) throws ServiceException {
		try {
			contributionTypeDao.delete(contributionType);
		} catch (DaoException de) {
			throw new ServiceException(de.getMessage(), de);
		}
	}
}
