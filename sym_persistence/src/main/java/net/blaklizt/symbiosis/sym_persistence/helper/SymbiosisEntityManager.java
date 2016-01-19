//package net.blaklizt.symbiosis.sym_persistence.helper;
//
//import net.blaklizt.symbiosis.sym_common.structure.Pair;
//import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_entity;
//import net.blaklizt.symbiosis.sym_persistence.structure.ResponseObject;
//
//import org.hibernate.Criteria;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.criterion.Criterion;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.Serializable;
//import java.util.List;
//import java.util.logging.Logger;
//
//import static net.blaklizt.symbiosis.sym_common.configuration.SymbiosisUtilities.sendEmailAlert;
//import static net.blaklizt.symbiosis.sym_persistence.admin.SymbiosisConfig.DATA_NOT_FOUND;
//import static net.blaklizt.symbiosis.sym_persistence.admin.SymbiosisConfig.GENERAL_ERROR;
//import static net.blaklizt.symbiosis.sym_persistence.admin.SymbiosisConfig.SUCCESS;
//
///**
// * Created with IntelliJ IDEA.
// * SymbiosisUser: tkaviya
// * Date: 8/11/13
// * Time: 8:30 PM
// */
//
//@Repository
//@Transactional
//public class SymbiosisEntityManager <E extends symbiosis_entity, I extends Serializable> {
//
//	@Autowired
//	SessionFactory sessionFactory;
//
//	Logger LOGGER = Logger.getLogger(SymbiosisEntityInterface.class.getSimpleName());
//
//	public Session getCurrentSession() { return sessionFactory.getCurrentSession(); }
//
//	public void refresh(E e) { getCurrentSession().refresh(e); }
//
//	public void saveOrUpdate(symbiosis_entity<E> e) {
//		getCurrentSession().saveOrUpdate(e);
//		LOGGER.info("Updated entity " + e.toString());
//	}
//
//	public I save(symbiosis_entity<E> e) {
//		I id = (I)getCurrentSession().save(e);
//		LOGGER.info("Persisted entity " + e.toString());
//		return id;
//	}
//
//	public void delete(symbiosis_entity e) {
//		LOGGER.info("Deleting entity " + e.toString());
//		getCurrentSession().delete(e);
//	}
//
//	public List findByCriterion(Criterion criterion) { return findByCriteria(criterion); }
//
//	public E findById(I id) {
//		return (E) getCurrentSession().get(getEntityClass(), id);
//	}
//
//	public List<E> findAll() {
//		return getCurrentSession().createQuery("from " + getEntityClass().getSimpleName()).list();
//	}
//
//	public List<E> findByCriteria(Criterion... criterion) {
//		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
//		for (Criterion c : criterion) { criteria.add(c); }
//		return criteria.list();
//	}
//
//	public List<E> findWhere(List<Pair<String, ?>> criterion, int maxResults) {
//		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
//		for (Pair<String, ?> p : criterion) { criteria.add(Restrictions.eq(p.getLeft(), p.getRight())); }
//		if (maxResults != 0) {
//			criteria.setMaxResults(maxResults);
//		}
//		return criteria.list();
//	}
//
//	public ResponseObject<E> findUniqueWhere(List<Pair<String, ?>> criterion) {
//		return enforceUnique(findWhere(criterion, 0));
//	}
//
//	public <E> ResponseObject<E> enforceUnique(List<E> list) {
//		if (list == null) {
//			return new ResponseObject<>(DATA_NOT_FOUND);
//		} else if (list.size() != 1) {
//			StringBuilder stringBuilder = new StringBuilder();
//			for (E entity : list) { stringBuilder.append(entity.toString()).append("\r\n"); }
//			sendEmailAlert(this.getClass().getSimpleName(),
//					"Constraint violation on " + getEntityClass().getSimpleName(),
//					"Entities in violation:\r\n\r\n" + stringBuilder.toString());
//			return new ResponseObject<>(GENERAL_ERROR);
//		}
//		return new ResponseObject<>(SUCCESS, list.get(0));
//	}
//
//}