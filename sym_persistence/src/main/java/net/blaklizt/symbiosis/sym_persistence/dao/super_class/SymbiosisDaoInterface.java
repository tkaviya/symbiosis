package net.blaklizt.symbiosis.sym_persistence.dao.super_class;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import java.util.List;

public interface SymbiosisDaoInterface
{
    public Class getEntityClass();

	public Session getCurrentSession();

	public List findAll();

	public List findByCriteria(Criterion... criterion);

	public List findByCriterion(Criterion criterion);
}