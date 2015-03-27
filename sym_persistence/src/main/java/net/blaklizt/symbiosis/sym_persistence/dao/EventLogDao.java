package net.blaklizt.symbiosis.sym_persistence.dao;

import net.blaklizt.symbiosis.sym_persistence.EventLog;
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
public class EventLogDao extends AbstractDao<EventLog, Long>
{
	protected EventLogDao() { super(EventLog.class); }

	public List<EventLog> findByUserID(Long userId) { return findByCriterion(Restrictions.like("userID", userId)); }

	public List<EventLog> findCoreAndUserID(Long userId)
	{
		Criterion criterion1 = Restrictions.like("userID", 0L);
		Criterion criterion2 = Restrictions.like("userID", userId);
		LogicalExpression userIDExpression = Restrictions.or(criterion1, criterion2);
		return findByCriterion(userIDExpression);
	}
}
