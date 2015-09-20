package net.blaklizt.symbiosis.sym_persistence.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import java.util.List;

public interface SymbiosisDaoInterface
{
	public Session getCurrentSession();

	public List findAll();

	public List findByCriteria(Criterion... criterion);

	public List findByCriterion(Criterion criterion);
}