package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public abstract class GenericDAO<E> {

	private final Class<E> type;
	private final EntityManagerFactory entityManagerFactory;
	private ThreadLocal<EntityManager> threadLocal;

	public GenericDAO(EntityManagerFactory entityManagerFactory, Class<E> type) {
		this.type = type;
		this.entityManagerFactory = entityManagerFactory;
	}
	
	public EntityManager getEntityManager() {
		if(Objects.isNull(threadLocal)) {
			threadLocal = ThreadLocal.withInitial(() -> entityManagerFactory.createEntityManager());
		}
		return threadLocal.get();
	}

	public final E create(E entity) {
		getEntityManager().persist(entity);
		return entity;
	}

	public final Optional<E> find(long id) {
		Optional<E> entityMaybe = Optional.ofNullable(getEntityManager().find(type, id));
		return entityMaybe;
	}

	public final E update(E entity) {
		getEntityManager().merge(entity);
		getEntityManager().flush();
		getEntityManager().detach(entity);
		return entity;
	}

	public final void delete(long id) {
		Object reference = getEntityManager().getReference(type, id);
		getEntityManager().remove(reference);
	}

	public final long count() {
		Query query = getEntityManager().createNamedQuery(type.getSimpleName() + ".count");
		long result = (long) query.getSingleResult();
		return result;
	}

	public final List<E> findAll(int page, int pageSize) {
		return (List<E>) findWithNamedQuery(type.getSimpleName() + ".findAll", page, pageSize, true);
	}

	public final List<E> findAll() {
		return (List<E>) findWithNamedQuery(type.getSimpleName() + ".findAll", true);
	}

	public final Object findWithNamedQuery(String queryName, boolean isResultList) {
		return findWithNamedQuery(queryName, new HashMap<String, Object>(), isResultList);
	}

	public final Object findWithNamedQuery(String queryName, Map<String, Object> parameters, boolean isResultList) {
		return findWithNamedQuery(queryName, parameters, null, null, isResultList);
	}

	public final Object findWithNamedQuery(String queryName, int page, int pageSize, boolean isResultList) {
		return findWithNamedQuery(queryName, null, page, pageSize, isResultList);
	}
	
	public final Object findWithNamedQuery(String queryName, Map<String, Object> parameters, Integer page, Integer pageSize, boolean isResultList) {

		Query query = getEntityManager().createNamedQuery(queryName);
		
		if(!Objects.isNull(parameters)) {
			parameters.forEach((name, value) -> query.setParameter(name, value));
		}
		
		if(!Objects.isNull(page) && !Objects.isNull(pageSize)) {
			query.setFirstResult((page - 1) * pageSize);
			query.setMaxResults(pageSize);
		}
		
		Object result = (isResultList == true) ? query.getResultList() : query.getSingleResult();

		return result;
	}
	
}
