package net.blaklizt.symbiosis.sym_persistence.dao;

import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.String.format;

/**
 * Created with IntelliJ IDEA.
 * SymbiosisUser: tkaviya
 * Date: 8/11/13
 * Time: 8:30 PM
 */

@Transactional
public abstract class AbstractDao<E, I extends Serializable> {

	private SessionFactory sessionFactory;

	private Class<E> entityClass;

	private String className;
    
    private Logger logger = Configuration.getNewLogger(AbstractDao.class.getSimpleName());
    
	protected AbstractDao(Class<E> entityClass) {
		this.entityClass = entityClass;
		this.className = entityClass.getSimpleName();
	}

    @Autowired(required = true)
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        logger.info(format("Initialized new DAO %s with sessionFactory %s.", className, sessionFactory));
    }

	public Session getCurrentSession() {
		try { return sessionFactory.getCurrentSession(); } catch (Exception e) { e.printStackTrace(); return null; }
	}

	@Transactional
	public void refresh(E e)
	{
		getCurrentSession().refresh(e);
	}

	public E findById(I id)
	{
		try {
			return (E) getCurrentSession().get(entityClass, id);
		} catch (Exception ex) { ex.printStackTrace(); return null; }
	}

	public List findAll()
	{
		try {
			Query queryResult = getCurrentSession().createQuery("from " + className);
			return queryResult.list();
		} catch (Exception ex) { ex.printStackTrace(); return null; }
	}

	@Transactional
	public void saveOrUpdate(E e) {
		getCurrentSession().saveOrUpdate(e);
	}

	@Transactional
	public void save(E e) {
		getCurrentSession().save(e);
	}

	@Transactional
	public void delete(E e) {
		getCurrentSession().delete(e);
	}

	public List findByCriteria(Criterion... criterion) {
		try {
			Criteria criteria = getCurrentSession().createCriteria(entityClass);
			for (Criterion c : criterion) { criteria.add(c); }
			return criteria.list();
		} catch (Exception ex) { ex.printStackTrace(); return null; }
	}

	public List findByCriterion(Criterion criterion) { return findByCriteria(criterion); }
}