package com.tavi903.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Singleton;
import javax.persistence.StoredProcedureQuery;

import com.tavi903.config.ApplicationConfig;
import com.tavi903.entity.User;

@Singleton
public class UserDAO extends GenericDAO<User> {

	public UserDAO() {
		super(ApplicationConfig.entityManagerFactory, User.class);
	}

	@Deprecated
	public User update(User user, long lastAccessedTime) {
		super.getEntityManager().getTransaction().begin();
		try {
			StoredProcedureQuery query = super.getEntityManager().createNamedStoredProcedureQuery("User.update_user");
			query.setParameter("p_last_accessed_time", lastAccessedTime);
			query.setParameter("p_user_id", user.getUserId());
			query.setParameter("p_email", user.getEmail());
			query.setParameter("p_password", user.getPassword());
			query.setParameter("p_full_name", user.getFullName());
			query.execute();
			super.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			super.getEntityManager().getTransaction().rollback();
			throw e;
		} 
		return user;
	}

	public boolean checkUserCredential(String email, String password) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("email", email);
		parameters.put("password", password);

		List<User> user = (List<User>) super.findWithNamedQuery("User.findByEmailAndPassword", parameters, true);
		
		if (Objects.nonNull(user) && user.size()==1) {
			return true;
		}

		return false;

	}

}
