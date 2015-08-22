package net.blaklizt.symbiosis.sym_persistence.dao;

import net.blaklizt.symbiosis.sym_persistence.SymbiosisAuthUser;
import net.blaklizt.symbiosis.sym_persistence.SymbiosisUser;
import net.blaklizt.symbiosis.sym_persistence.dao.AbstractDao;
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
public class SymbiosisAuthUserDao extends AbstractDao<SymbiosisAuthUser, Long>
{
	protected SymbiosisAuthUserDao() { super(SymbiosisAuthUser.class); }

	public SymbiosisUser findByUserIDAndChannel(Long userID, int channelID)
	{
		List result = findByCriteria(Restrictions.eq("SymbiosisUserID", userID), Restrictions.eq("SymbiosisChannelID", channelID));
		if (result == null || result.size() != 1) return null;
		return (SymbiosisUser)result.get(0);
	}
}
