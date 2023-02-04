package com.tavi903.service;

import java.util.List;
import java.util.Optional;

import com.tavi903.dao.BaseDAO;
import com.tavi903.entity.BaseEntity;
import com.tavi903.interceptor.Transactional;

@Transactional
public abstract class BaseService<E extends BaseEntity> {

	private final BaseDAO<E> baseDAO;

	public BaseService(BaseDAO<E> baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	public BaseDAO<E> getGenericDAO() {
		return baseDAO;
	}
	
	public boolean create(E entity, String userEmail) {
		baseDAO.create(entity, userEmail);
		return true;
	}

	public Optional<E> find(long entityId) {
		return baseDAO.find(entityId);
	}

	public E update(E entity, String userEmail) {
		return baseDAO.update(entity, userEmail);
	}

	public boolean delete(long entityId) {
		baseDAO.delete(entityId);
		return true;
	}

	public long count() {
		return baseDAO.count();
	}

	public List<E> findAll(int page, int pageSize) {
		return baseDAO.findAll(page, pageSize);
	}

	public List<E> findAll() {
		return baseDAO.findAll();
	}

}
