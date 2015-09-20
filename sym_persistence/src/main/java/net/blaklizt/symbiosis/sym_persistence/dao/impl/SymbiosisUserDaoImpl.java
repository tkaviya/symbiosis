package net.blaklizt.symbiosis.sym_persistence.dao.impl;

import net.blaklizt.symbiosis.sym_persistence.helper.AbstractDao;
import net.blaklizt.symbiosis.sym_persistence.dao.SymbiosisUserDao;
import net.blaklizt.symbiosis.sym_persistence.complex_type.symbiosis_user;
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
public class SymbiosisUserDaoImpl extends AbstractDao<symbiosis_user, Long> implements SymbiosisUserDao
{
	protected SymbiosisUserDaoImpl() { super(symbiosis_user.class); }

	public symbiosis_user findByUsername(String username)
	{
		List result = findByCriterion(Restrictions.eq("username", username));
		if (result == null || result.size() != 1) return null;
		return (symbiosis_user)result.get(0);
	}

	public symbiosis_user findByEmail(String email)
	{
		List result = findByCriterion(Restrictions.eq("email", email));
		if (result == null || result.size() != 1) return null;
		return (symbiosis_user)result.get(0);
	}

	public symbiosis_user findByMsisdn(String msisdn)
	{
		List result = findByCriterion(Restrictions.eq("msisdn", msisdn));
		if (result == null || result.size() != 1) return null;
		return (symbiosis_user)result.get(0);
	}
}
