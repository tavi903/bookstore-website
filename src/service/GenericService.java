package service;

import java.util.List;
import java.util.Optional;

import dao.GenericDAO;
import interceptor.Transaction;

@Transaction
public abstract class GenericService<E> {

	private final GenericDAO<E> genericDAO;

	public GenericService(GenericDAO<E> genericDAO) {
		this.genericDAO = genericDAO;
	}
	
	public GenericDAO<E> getGenericDAO() {
		return genericDAO;
	}
	
	public boolean create(E entity) {
		genericDAO.create(entity);
		return true;
	}

	public Optional<E> find(long entityId) {
		return genericDAO.find(entityId);
	}

	public E update(E entity) {
		return genericDAO.update(entity);
	}

	public boolean delete(long entityId) {
		genericDAO.delete(entityId);
		return true;
	}

	public long count() {
		return genericDAO.count();
	}

	public List<E> findAll(int page, int pageSize) {
		return genericDAO.findAll(page, pageSize);
	}

	public List<E> findAll() {
		return genericDAO.findAll();
	}

}
