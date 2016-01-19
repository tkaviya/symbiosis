package net.blaklizt.symbiosis.sym_persistence.dao.super_class;

import net.blaklizt.symbiosis.sym_common.structure.Pair;
import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_entity;
import net.blaklizt.symbiosis.sym_persistence.structure.ResponseObject;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

public interface SymbiosisDaoInterface<E extends symbiosis_entity, I extends Serializable>
{
    void saveOrUpdate(symbiosis_entity<E> e);

    I save(symbiosis_entity<E> e);

    void delete(symbiosis_entity<E> e);

    void refresh(E e);

    Class<E> getEntityClass();

	Session getCurrentSession();

    E findById(I id);

	List<E> findAll();

	List<E> findByCriteria(Criterion... criterion);

	List<E> findByCriterion(Criterion criterion);

    List<E> findWhere(List<Pair<String, ?>> criterion, int maxResults);

    ResponseObject<E> findUniqueWhere(List<Pair<String, ?>> criterion);

    <E> ResponseObject<E> enforceUnique(List<E> list);
}