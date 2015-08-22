package net.blaklizt.symbiosis.sym_persistence.dao;

import net.blaklizt.symbiosis.sym_persistence.SymbiosisRole;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * SymbiosisUser: tkaviya
 * Date: 8/10/13
 * Time: 11:25 AM
 */

@Repository
public class SymbiosisRoleDao extends AbstractDao<SymbiosisRole, Long>
{
	protected SymbiosisRoleDao() { super(SymbiosisRole.class); }

	public SymbiosisRole findEnabled()
	{
		List result = findByCriterion(Restrictions.eq("Enabled", 1));
		if (result == null || result.size() != 1) return null;
		return (SymbiosisRole)result.get(0);
	}
}
