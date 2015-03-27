package net.blaklizt.symbiosis.sym_persistence.dao;

import net.blaklizt.symbiosis.sym_persistence.Role;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 8/10/13
 * Time: 11:25 AM
 */

@Repository
public class RoleDao extends AbstractDao<Role, Long>
{
	protected RoleDao() { super(Role.class); }

	public Role findEnabled()
	{
		List result = findByCriterion(Restrictions.eq("Enabled", 1));
		if (result == null || result.size() != 1) return null;
		return (Role)result.get(0);
	}
}
