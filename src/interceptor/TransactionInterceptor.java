package interceptor;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import config.PersistenceConfig;
import service.GenericService;

@Transaction
@Priority(Interceptor.Priority.PLATFORM_AFTER+10)
@Interceptor
public class TransactionInterceptor {
	
	@AroundInvoke
	public Object manageTransaction(InvocationContext invocationContext) throws Exception {
		
	    GenericService<?> genericService = (GenericService<?>) invocationContext.getTarget();
		EntityManager entityManager= genericService.getGenericDAO().getEntityManager();
		
		entityManager.getTransaction().begin();
		
		Object result;
		
		try {
			result = invocationContext.proceed();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			if(PersistenceConfig.CLEAR_CACHE) entityManager.clear();
		}
		
		return result;
		
	}

}
