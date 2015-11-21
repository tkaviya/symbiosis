package net.blaklizt.symbiosis.sym_persistence.dao.implementation;

import net.blaklizt.symbiosis.sym_persistence.entity.config.symbiosis_group_system_role;
import net.blaklizt.symbiosis.sym_persistence.dao.super_class.SymbiosisGroupSystemRoleDao;
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
public class SymbiosisGroupSystemRoleDaoImpl
     extends SymbiosisEnumEntityDaoImpl<symbiosis_group_system_role, Long>
  implements SymbiosisGroupSystemRoleDao
{
	public SymbiosisGroupSystemRoleDaoImpl() { super(symbiosis_group_system_role.class); }

    @SuppressWarnings("unchecked")
	public List<symbiosis_group_system_role> findByUserGroup(Long userGroupId)
	{
		return findByCriterion(Restrictions.eq("user_group_id", userGroupId));
	}

    @SuppressWarnings("unchecked")
    public List<symbiosis_group_system_role> findBySystem(Long systemId)
    {
        return findByCriterion(Restrictions.eq("symbiosis_system_id", systemId));
    }

    @SuppressWarnings("unchecked")
    public List<symbiosis_group_system_role> findByRole(Long userRoleId)
    {
        return findByCriterion(Restrictions.eq("symbiosis_role_id", userRoleId));
    }

    @SuppressWarnings("unchecked")
    public List<symbiosis_group_system_role> findByGroupSystemRole(Long userGroupId, Long systemId, Long roleId) {
        return findByCriteria(
                Restrictions.eq("user_group_id", userGroupId),
                Restrictions.eq("symbiosis_system_id", systemId),
                Restrictions.eq("symbiosis_role_id", roleId));
    }
}
