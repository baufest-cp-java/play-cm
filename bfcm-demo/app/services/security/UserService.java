package services.security;

import java.util.List;

import business.security.User;
import daos.security.UserDao;
import exceptions.DaoException;
import exceptions.ServiceException;

public class UserService {
	private UserDao userDao = new UserDao();

	public List<User> all() {
		return userDao.all();
	}

	public User findById(Long id) {
		return userDao.findById(id);
	}
	
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	public void save(User user) throws ServiceException {
		try {
			userDao.save(user);
		} catch (DaoException de) {
			throw new ServiceException(de.getMessage(), de);
		}
	}

	public void update(User user) throws ServiceException {
		try {
			userDao.update(user);
		} catch (DaoException de) {
			throw new ServiceException(de.getMessage(), de);
		}
	}

	public void delete(User user) throws ServiceException {
		try {
			userDao.delete(user);
		} catch (DaoException de) {
			throw new ServiceException(de.getMessage(), de);
		}
	}

	public boolean authenticate(String username, String password) {
		User user = userDao.findByUsernameByPassword(username, password);
		return user != null;
	}
}
