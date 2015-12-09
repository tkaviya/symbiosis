package net.blaklizt.symbiosis.sym_persistence.dao.implementation;

import net.blaklizt.symbiosis.sym_persistence.dao.super_class.AbstractDao;
import net.blaklizt.symbiosis.sym_persistence.dao.super_class.SymbiosisAuthUserDao;
import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_auth_user;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * symbiosis_user: tkaviya
 * Date: 8/10/13
 * Time: 11:25 AM
 */

@Repository
public class SymbiosisAuthUserDaoImpl extends AbstractDao<symbiosis_auth_user, Long> implements SymbiosisAuthUserDao
{
	protected SymbiosisAuthUserDaoImpl() { super(symbiosis_auth_user.class); }

	public symbiosis_auth_user findByUserIDAndChannel(Long userID, Long channelID)
	{
		List result = findByCriteria(
                Restrictions.eq("symbiosis_user_id", userID),
                Restrictions.eq("symbiosis_channel_id", channelID));
		if (result == null || result.size() != 1) return null;
		return (symbiosis_auth_user)result.get(0);
	}
}
