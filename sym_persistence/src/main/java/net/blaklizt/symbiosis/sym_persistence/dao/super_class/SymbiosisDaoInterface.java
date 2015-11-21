package net.blaklizt.symbiosis.sym_persistence.dao.super_class;

import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_entity;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SymbiosisDaoInterface
{
    public void saveOrUpdate(symbiosis_entity e);

    public void save(symbiosis_entity e);

    public Class getEntityClass();

	public Session getCurrentSession();

	public List findAll();

	public List findByCriteria(Criterion... criterion);

	public List findByCriterion(Criterion criterion);
}