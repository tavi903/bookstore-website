package com.tavi903.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.tavi903.dao.UserDAO;
import com.tavi903.entity.User;

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