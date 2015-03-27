package net.blaklizt.symbiosis.sym_persistence.dao;

import net.blaklizt.symbiosis.sym_persistence.UserGroupRole;
import org.hibernate.criterion.MatchMode;
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
public class UserGroupRoleDao extends AbstractDao<UserGroupRole, Long>
{
	protected UserGroupRoleDao() { super(UserGroupRole.class); }

	public List<UserGroupRole> findByUserGroup(String userGroup)
	{
		return findByCriterion(Restrictions.like("userGroupID", userGroup, MatchMode.EXACT));
	}

//	@Override
//	protected String getEntityClassName() {
//		return UserGroupRole.class.getSimpleName();
//	}
}
