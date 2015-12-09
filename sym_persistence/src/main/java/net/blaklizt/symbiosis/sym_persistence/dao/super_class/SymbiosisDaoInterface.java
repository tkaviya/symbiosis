package net.blaklizt.symbiosis.sym_persistence.dao.super_class;

import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_entity;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import java.util.List;

public interface SymbiosisDaoInterface
{
    void saveOrUpdate(symbiosis_entity e);

    void save(symbiosis_entity e);

    Class getEntityClass();

	Session getCurrentSession();

	List findAll();

	List findByCriteria(Criterion... criterion);

	List findByCriterion(Criterion criterion);
}