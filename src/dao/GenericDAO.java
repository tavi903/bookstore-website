package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class GenericDAO<E> {

	private final Class<E> type;
	private final EntityManager entityManager;

	public GenericDAO(EntityManager entityManager, Class<E> type) {
		this.type = type;
		this.entityManager = entityManager;
	}
	
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	@Override
	protected void finalize() {
		this.entityManager.close();
	}

	public final E create(E entity) {
		entityManager.persist(entity);
		return entity;
	}

	public final Optional<E> find(long id) {
		Optional<E> entityMaybe = Optional.ofNullable(entityManager.find(type, id));
		return entityMaybe;
	}

	public final E update(E entity) {
		entityManager.merge(entity);
		return entity;
	}

	public final void delete(long id) {
		Object reference = entityManager.getReference(type, id);
		entityManager.remove(reference);
	}

	public final long count() {
		Query query = entityManager.createNamedQuery(type.getSimpleName() + ".count");
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

		Query query = entityManager.createNamedQuery(queryName);
		
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
