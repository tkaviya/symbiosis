package net.blaklizt.symbiosis.sym_persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 11/14/13
 * Time: 8:00 PM
 */

public class DaoManager
{
	@Autowired private SymbiosisEventLogDao eventLogDao;
	@Autowired private SymbiosisUserDao userDao;

	private static DaoManager daoManager = null;

	private DaoManager() { }

	public static DaoManager getInstance()
	{
		if (daoManager == null) daoManager = new DaoManager();
		return daoManager;
	}

	public SymbiosisEventLogDao getEventLogDao() { return eventLogDao; }
	public SymbiosisUserDao getUserDao() { return userDao; }
}
