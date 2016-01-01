package net.blaklizt.symbiosis.sym_persistence.dao.super_class;

import net.blaklizt.symbiosis.sym_common.structure.Pair;
import net.blaklizt.symbiosis.sym_core_lib.response.ResponseObject;
import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_entity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static net.blaklizt.symbiosis.sym_common.configuration.SymbiosisUtilities.sendEmailAlert;
import static net.blaklizt.symbiosis.sym_core_lib.enumeration.SYM_RESPONSE_CODE.*;
import static net.blaklizt.symbiosis.sym_persistence.dao.super_class.SymbiosisEntityManager.DaoDataManager.sessionFactory;

/**
 * Created with IntelliJ IDEA.
 * SymbiosisUser: tkaviya
 * Date: 8/11/13
 * Time: 8:30 PM
 */

public interface SymbiosisEntityManager<E extends symbiosis_entity, I extends Serializable> {

	@Service
	class DaoDataManager {

		@Autowired public static SessionFactory sessionFactory;

		public static final HashMap<Class<? extends symbiosis_entity>, SymbiosisEntityManager>
			daoManagerMap = new HashMap<>();

		@SuppressWarnings("unchecked")
		public static <E extends symbiosis_entity, I extends Serializable> SymbiosisEntityManager<E, I> using(Class<E> entityType) {
			if (!daoManagerMap.containsKey(entityType)) {
				daoManagerMap.put(entityType, () -> entityType);
			}
			return daoManagerMap.get(entityType);
		}
	}

    default Session getCurrentSession() { return sessionFactory.getCurrentSession(); }

    Class<E> getEntityClass();

	default void refresh(E e) { getCurrentSession().refresh(e); }

	default void saveOrUpdate(symbiosis_entity e) { getCurrentSession().saveOrUpdate(e); }

	default void save(symbiosis_entity e) { getCurrentSession().save(e); }

	default void delete(symbiosis_entity e) { getCurrentSession().delete(e); }

	default List findByCriterion(Criterion criterion) { return findByCriteria(criterion); }

	default E findById(I id) {
        try {
            return (E) getCurrentSession().get(getEntityClass(), id);
        } catch (Exception ex) { ex.printStackTrace(); return null; }
    }

	default List<E> findAll() {
        try {
            Query queryResult = getCurrentSession().createQuery("from " + getEntityClass().getSimpleName());
            return queryResult.list();
        } catch (Exception ex) { ex.printStackTrace(); return null; }
    }

	default List<E> findByCriteria(Criterion... criterion) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		for (Criterion c : criterion) { criteria.add(c); }
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	default List<E> findWhere(boolean unique, int maxResults, Pair<String, ?>... criterion) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		for (Pair<String, ?> p : criterion) { criteria.add(Restrictions.eq(p.getLeft(), p.getRight())); }
		if (unique) {
			return Collections.singletonList((E)criteria.uniqueResult());
		} else if (maxResults != 0) {
			criteria.setMaxResults(maxResults);
		}
		return criteria.list();
	}

	default List<E> findWhere(Pair<String, ?>... criterion) {
		return findWhere(false, 0, criterion);
	}

	default ResponseObject<E> enforceUnique(List<E> list) {
		if (list == null) {
			return new ResponseObject<>(DATA_NOT_FOUND);
		} else if (list.size() != 1) {
			StringBuilder stringBuilder = new StringBuilder();
			for (E entity : list) { stringBuilder.append(entity.toString()).append("\r\n"); }
			sendEmailAlert(this.getClass().getSimpleName(),
				"Constraint violation on " + getEntityClass().getSimpleName(),
				"Entities in violation:\r\n\r\n" + stringBuilder.toString());
			return new ResponseObject<>(GENERAL_ERROR);
		}
		return new ResponseObject<>(SUCCESS, list.get(0));
	}

}