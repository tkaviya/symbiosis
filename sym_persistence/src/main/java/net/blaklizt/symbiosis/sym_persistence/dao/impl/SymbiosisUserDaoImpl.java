package net.blaklizt.symbiosis.sym_persistence.dao.impl;

import net.blaklizt.symbiosis.sym_persistence.SymbiosisUser;
import net.blaklizt.symbiosis.sym_persistence.dao.AbstractDao;
import net.blaklizt.symbiosis.sym_persistence.dao.SymbiosisUserDao;
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
public class SymbiosisUserDaoImpl extends AbstractDao<SymbiosisUser, Long> implements SymbiosisUserDao
{
	protected SymbiosisUserDaoImpl() { super(SymbiosisUser.class); }

	public SymbiosisUser findByUsername(String username)
	{
		List result = findByCriterion(Restrictions.eq("Username", username));
		if (result == null || result.size() != 1) return null;
		return (SymbiosisUser)result.get(0);
	}

	public SymbiosisUser findByEmail(String email)
	{
		List result = findByCriterion(Restrictions.eq("Email", email));
		if (result == null || result.size() != 1) return null;
		return (SymbiosisUser)result.get(0);
	}

	public SymbiosisUser findByMsisdn(String msisdn)
	{
		List result = findByCriterion(Restrictions.eq("Msisdn", msisdn));
		if (result == null || result.size() != 1) return null;
		return (SymbiosisUser)result.get(0);
	}
}
