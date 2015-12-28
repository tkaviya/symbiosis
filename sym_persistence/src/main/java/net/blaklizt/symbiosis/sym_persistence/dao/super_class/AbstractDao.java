package net.blaklizt.symbiosis.sym_persistence.dao.super_class;

import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_entity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * SymbiosisUser: tkaviya
 * Date: 8/11/13
 * Time: 8:30 PM
 */

@Transactional
public abstract class AbstractDao<E extends symbiosis_entity, I extends Serializable> implements SymbiosisDaoInterface {

    @Autowired private SessionFactory sessionFactory;

	private Class<E> entityClass;

	private String className;
    
	protected AbstractDao(Class<E> entityClass) {
		this.entityClass = entityClass;
		this.className = entityClass.getSimpleName();
	}

	public Session getCurrentSession() { return sessionFactory.getCurrentSession(); }

    public Class<E> getEntityClass() { return entityClass; }

	public void refresh(E e) { getCurrentSession().refresh(e); }

	public void saveOrUpdate(symbiosis_entity e) { getCurrentSession().saveOrUpdate(e); }

	public void save(symbiosis_entity e) { getCurrentSession().save(e); }

	public void delete(symbiosis_entity e) { getCurrentSession().delete(e); }

    public List findByCriterion(Criterion criterion) { return findByCriteria(criterion); }

    public E findById(I id) {
        try {
            return (E) getCurrentSession().get(entityClass, id);
        } catch (Exception ex) { ex.printStackTrace(); return null; }
    }

    public List findAll() {
        try {
            Query queryResult = getCurrentSession().createQuery("from " + className);
            return queryResult.list();
        } catch (Exception ex) { ex.printStackTrace(); return null; }
    }

	public List findByCriteria(Criterion... criterion) {
		try {
			Criteria criteria = getCurrentSession().createCriteria(entityClass);
			for (Criterion c : criterion) { criteria.add(c); }
			return criteria.list();
		} catch (Exception ex) { ex.printStackTrace(); return null; }
	}
}