package dao;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import config.PersistenceConfig;
import entity.User;

public class UserDAOTest {
	
	private static UserDAO userDAO;
	private static EntityManager entityManager;
	private static Validator validator;
	
	private static List<User> usersInDb = Arrays.asList(
		new User("cesare@gmail.com", "AleaIactaEst", "Cesare Augusto"),
		new User("nerone@gmail.com", "RomeOnFire", "Nerone Germanico"),
		new User("aurelio@gmail.com", "Meditation", "Marco Aurelio")
	);
	
	@BeforeClass
	public static void setUpClass() {
		
		PersistenceConfig.setPersistenceUnit("H2");
		
		userDAO = new UserDAO();
		
		validator = Validation.buildDefaultValidatorFactory().getValidator();
			
	}
	
	@Before
	public void beginTransaction() {
		entityManager = userDAO.getEntityManager();
		entityManager.getTransaction().begin();
	}
	
	@After
	public void rollbackTransaction() {
		entityManager.getTransaction().rollback();
	}
	
	@Test
	public void createUser() throws Exception {
		
		User user = new User();
		user.setEmail("diocleziano@gmail.com");
		user.setFullName("Diocleziano");
		user.setPassword("password");
		
		User userSavedToDB = userDAO.create(user);
		assertTrue(Objects.nonNull(userSavedToDB));
		
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void createUserWithNullFields() throws Exception {
		Set<ConstraintViolation<User>> violations = validator.validate(new User());
		if(Objects.nonNull(violations)) throw new ConstraintViolationException(violations);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void createUserWithWrongEmail() throws Exception {
		Set<ConstraintViolation<User>> violations = validator.validate(new User("paperino","Paperino","password"));
		if(Objects.nonNull(violations) && !violations.isEmpty()) throw new ConstraintViolationException(violations);
	}
	
	@Test
	public void updateUser() throws Exception {
		User user = userDAO.find(1).get();
		user.setEmail("cesare.augusto@gmail.com");
		userDAO.update(user);
		assertTrue(userDAO.find(1).get().equals(user));
	}
	
	@Test
	public void getUserFound() throws Exception {
		Optional<User> user = userDAO.find(3);
		assertTrue(user.isPresent());
	}
	
	@Test
	public void getUserNotFound() throws Exception {
		Integer userId = 12345;
		Optional<User> user = userDAO.find(userId);
		assertFalse(user.isPresent());
	}
	
	@Test
	public void deleteUser() throws Exception {
		User user = userDAO.find(2).get();
		userDAO.delete(user.getUserId());
		Optional<User> userNotPresent = userDAO.find(user.getUserId());
		assertFalse(userNotPresent.isPresent());
	}
	
	@Test(expected = PersistenceException.class)
	public void deleteNonExistentUser() throws Exception {
		userDAO.delete(12345);
	}
	
	@Test
	public void getAllUsers() throws Exception {
		
		List<User> users = userDAO.findAll();
		
		for(User user: usersInDb) {
			assertTrue(users.contains(user));
		}
		
		for(User user: users) {
			assertTrue(usersInDb.contains(user));
		}
		
	}
	
	@Test
	public void count() throws Exception {
		long totalUsers = userDAO.count();
		assertTrue(totalUsers == usersInDb.size());
	}
	
	@Test
	public void userCanLogin() throws Exception {
		assertTrue(userDAO.checkUserCredential("nerone@gmail.com", "RomeOnFire"));
	}
	
	@Test
	public void userCannotLogin() throws Exception {
		assertFalse(userDAO.checkUserCredential("nerone@gmail.com", "RomeIsOld"));
	}

}
