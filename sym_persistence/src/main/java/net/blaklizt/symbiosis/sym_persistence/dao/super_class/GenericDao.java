package net.blaklizt.symbiosis.sym_persistence.dao.super_class;

import net.blaklizt.symbiosis.sym_common.structure.Pair;
import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_entity;
import net.blaklizt.symbiosis.sym_persistence.structure.ResponseObject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import static net.blaklizt.symbiosis.sym_common.configuration.SymbiosisUtilities.sendEmailAlert;
import static net.blaklizt.symbiosis.sym_persistence.admin.SymbiosisConfig.DATA_NOT_FOUND;
import static net.blaklizt.symbiosis.sym_persistence.admin.SymbiosisConfig.GENERAL_ERROR;
import static net.blaklizt.symbiosis.sym_persistence.admin.SymbiosisConfig.SUCCESS;

/**
 * Created with IntelliJ IDEA.
 * SymbiosisUser: tkaviya
 * Date: 8/11/13
 * Time: 8:30 PM
 */

@Repository
@Transactional
public class GenericDao {

    @Autowired
	public SessionFactory sessionFactory;

	private static final Logger LOGGER = Logger.getLogger(GenericDao.class.getSimpleName());

    public static Session getCurrentSession() { return genericDao.sessionFactory.getCurrentSession(); }

	private static GenericDao genericDao;

	public GenericDao() {
		genericDao = this;
	}

    public static symbiosis_entity saveOrUpdate(symbiosis_entity e) {
        getCurrentSession().saveOrUpdate(e);
        LOGGER.info("Updated entity " + e.toString());
		return e;
    }

    public static <I extends Serializable> I save(symbiosis_entity e) {
        I id = (I)getCurrentSession().save(e);
        LOGGER.info("Persisted entity " + e.toString());
        return id;
    }

    public static <E extends symbiosis_entity> void delete(symbiosis_entity<E> e) {
        LOGGER.info("Deleting entity " + e.toString());
        getCurrentSession().delete(e);
    }

    public static <E extends symbiosis_entity> List<E> findByCriterion(Class<E> entityClass, Criterion criterion) {
		return findByCriteria(entityClass, criterion);
	}

    public static <I extends Serializable, E extends symbiosis_entity> E findById(Class<E> entityClass, I id) {
        return (E) getCurrentSession().get(entityClass, id);
    }

    public static <E extends symbiosis_entity> List<E> findAll(Class<E> entityClass) {
        return getCurrentSession().createQuery("from " + entityClass.getSimpleName()).list();
    }

    public static <E extends symbiosis_entity> List<E> findByCriteria(Class<E> entityClass, Criterion... criterion) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        for (Criterion c : criterion) { criteria.add(c); }
        return criteria.list();
    }

    public static <E extends symbiosis_entity> List<E> findWhere(Class<E> entityClass, List<Pair<String, ?>> criterion, int maxResults) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        for (Pair<String, ?> p : criterion) { criteria.add(Restrictions.eq(p.getLeft(), p.getRight())); }
        if (maxResults != 0) {
            criteria.setMaxResults(maxResults);
        }
        return criteria.list();
    }

    public static <E extends symbiosis_entity> ResponseObject<E> findUniqueWhere(Class<E> entityClass, List<Pair<String, ?>> criterion) {
        return enforceUnique(entityClass, findWhere(entityClass, criterion, 0));
    }

    public static <E extends symbiosis_entity> ResponseObject<E> enforceUnique(Class<E> entityClass, List<E> list) {
        if (list == null) {
            return new ResponseObject<>(DATA_NOT_FOUND);
        } else if (list.size() != 1) {
            StringBuilder stringBuilder = new StringBuilder();
            for (E entity : list) { stringBuilder.append(entity.toString()).append("\r\n"); }
            sendEmailAlert(GenericDao.class.getSimpleName(),
                    "Constraint violation on " + entityClass.getSimpleName(),
                    "Entities in violation:\r\n\r\n" + stringBuilder.toString());
            return new ResponseObject<>(GENERAL_ERROR);
        }
        return new ResponseObject<>(SUCCESS, list.get(0));
    }
}