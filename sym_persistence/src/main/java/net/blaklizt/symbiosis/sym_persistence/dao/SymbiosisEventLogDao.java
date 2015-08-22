package net.blaklizt.symbiosis.sym_persistence.dao;

import net.blaklizt.symbiosis.sym_persistence.SymbiosisEventLog;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * SymbiosisUser: tkaviya
 */

@Repository
public class SymbiosisEventLogDao extends AbstractDao<SymbiosisEventLog, Long>
{
	protected SymbiosisEventLogDao() { super(SymbiosisEventLog.class); }

	public List<SymbiosisEventLog> findByUserID(Long userId) { return findByCriterion(Restrictions.like("userID", userId)); }

	public List<SymbiosisEventLog> findCoreAndUserID(Long userId)
	{
		Criterion criterion1 = Restrictions.like("userID", 0L);
		Criterion criterion2 = Restrictions.like("userID", userId);
		LogicalExpression userIDExpression = Restrictions.or(criterion1, criterion2);
		return findByCriterion(userIDExpression);
	}
}
