package dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import config.PersistenceConfig;
import entity.User;

import static utils.ProjectUtils.*;

public class UserDAOTest {
	
	private static UserDAO userDAO;
	private static PersistenceConfig persistenceConfig;
	private static EntityManager entityManager;
	private static Validator validator;
	
	private static List<User> usersInDb = createList (
		new User("cesare@gmail.com", "AleaIactaEst", "Cesare Augusto"),
		new User("nerone@gmail.com", "RomeOnFire", "Nerone Germanico"),
		new User("aurelio@gmail.com", "Meditation", "Marco Aurelio")
	);
	
	@BeforeClass
	public static void setUpClass() {
		
		persistenceConfig = mock(PersistenceConfig.class);
		Mockito.when(persistenceConfig.getEntityManagerFactory()).thenReturn(Persistence.createEntityManagerFactory("H2"));
		userDAO = new UserDAO(persistenceConfig);
		entityManager = userDAO.getEntityManager();
		
		validator = Validation.buildDefaultValidatorFactory().getValidator();
			
	}
	
	@Test
	public void createUser() throws Exception {
		entityManager.getTransaction().begin();
		User user = new User();
		user.setEmail("diocleziano@gmail.com");
		user.setFullName("Diocleziano");
		user.setPassword("password");
		
		User userSavedToDB = userDAO.create(user);
		assertTrue(Objects.nonNull(userSavedToDB));
		entityManager.getTransaction().rollback();
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
		entityManager.getTransaction().begin();
		User user = userDAO.find(1).get();
		user.setEmail("cesare.augusto@gmail.com");
		userDAO.update(user);
		assertTrue(userDAO.find(1).get().equals(user));
		entityManager.getTransaction().rollback();
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
		entityManager.getTransaction().begin();
		User user = userDAO.find(2).get();
		userDAO.delete(user.getUserId());
		Optional<User> userNotPresent = userDAO.find(user.getUserId());
		assertFalse(userNotPresent.isPresent());
		entityManager.getTransaction().rollback();
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
