package service;

import javax.inject.Inject;
import javax.inject.Singleton;

import dao.UserDAO;
import entity.User;

@Singleton
public class UserService extends GenericService<User> {

	private final UserDAO userDAO;

	@Inject
	public UserService(UserDAO userDAO) {
		super(userDAO);
		this.userDAO = userDAO;
	}

	public boolean canUserLogin(String userEmail, String password) {
		return userDAO.checkUserCredential(userEmail, password);
	}

}