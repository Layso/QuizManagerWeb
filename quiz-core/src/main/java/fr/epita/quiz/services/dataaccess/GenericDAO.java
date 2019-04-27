package fr.epita.quiz.services.dataaccess;

import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericDAO<T>{
	private static final Logger LOGGER = LogManager.getLogger(GenericDAO.class);
	
	@PersistenceContext
	EntityManager em;
	
	
	
	@Transactional(Transactional.TxType.REQUIRED)
	public void create(T entity) {
		LOGGER.info("entering the create method");
		em.persist(entity); 
		LOGGER.info("exiting the create method");
	}
	
	@Transactional(Transactional.TxType.REQUIRED)
	public void delete(T entity) {
		em.remove(em.contains(entity) ? entity : em.merge(entity)); 
	}
	
	@Transactional(Transactional.TxType.REQUIRED)
	public void update(T entity) {
		em.merge(entity);
	}
	
	public List<T> search(T criteria){
		QueryHolder<T> holder = new QueryHolder<T>();
		prepareSearch(criteria, holder);
		
		TypedQuery<T> query = em.createQuery(holder.getQueryString(), holder.getClassName());
		for(Entry<String,Object> entry : holder.getMap().entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.getResultList();
		
	}
	
	public abstract void prepareSearch(T criteria, QueryHolder<T> holder);
	
}