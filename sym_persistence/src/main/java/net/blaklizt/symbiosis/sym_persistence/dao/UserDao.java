package net.blaklizt.symbiosis.sym_persistence.dao;

import net.blaklizt.symbiosis.sym_persistence.User;
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
public class UserDao extends AbstractDao<User, Long>
{
	protected UserDao() { super(User.class); }

	public User findByUsername(String username)
	{
		List result = findByCriterion(Restrictions.eq("Username", username));
		if (result == null || result.size() != 1) return null;
		return (User)result.get(0);
	}

	public User findByEmail(String email)
	{
		List result = findByCriterion(Restrictions.eq("Email", email));
		if (result == null || result.size() != 1) return null;
		return (User)result.get(0);
	}

	public User findByMsisdn(String msisdn)
	{
		List result = findByCriterion(Restrictions.eq("Msisdn", msisdn));
		if (result == null || result.size() != 1) return null;
		return (User)result.get(0);
	}
}
