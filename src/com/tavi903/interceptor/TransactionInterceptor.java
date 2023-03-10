package com.tavi903.interceptor;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import com.tavi903.service.BaseService;

@Transactional
@Priority(Interceptor.Priority.PLATFORM_AFTER+10)
@Interceptor
public class TransactionInterceptor {
	
	@AroundInvoke
	public Object manageTransaction(InvocationContext invocationContext) throws Exception {
		
	    BaseService<?> genericService = (BaseService<?>) invocationContext.getTarget();
		EntityManager entityManager= genericService.getGenericDAO().getEntityManager();
		
		entityManager.clear();
		entityManager.getTransaction().begin();
		
		Object result;
		
		try {
			result = invocationContext.proceed();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
		
		return result;
		
	}

}
