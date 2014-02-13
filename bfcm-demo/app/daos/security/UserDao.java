package daos.security;

import java.util.List;

import javax.persistence.PersistenceException;

import play.db.ebean.Model.Finder;
import business.security.User;
import exceptions.DaoException;

public class UserDao {

	private Finder<Long, User> finder = new Finder<Long, User>(Long.class,
			User.class);

	public List<User> all() {
		return finder.all();
	}

	public User findById(Long id) {
		return finder.byId(id);
	}
	
	public User findByUsername(String username) {
		return finder.where().eq("username", username).findUnique();
	}
	
	public void save(User user) throws DaoException {
		try {
			user.save();
		} catch (PersistenceException pe) {
			throw new DaoException("Error al crear el usuario", pe);
		}
	}
	
	public void update(User user) throws DaoException {
		try {
			user.update();
		} catch (PersistenceException pe) {
			throw new DaoException("Error al modificar el usuario", pe);
		}
	}

	public void delete(User user) throws DaoException {
		try {
			user.delete();
		} catch (PersistenceException pe) {
			throw new DaoException("Error al eliminar el usuario", pe);
		}
	}
	
	public User findByUsernameByPassword(String username, String password) {
		return finder.where().eq("username", username).eq("password", password).findUnique();
	}
}
