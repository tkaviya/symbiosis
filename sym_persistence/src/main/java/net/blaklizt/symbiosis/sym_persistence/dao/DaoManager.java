package net.blaklizt.symbiosis.sym_persistence.dao;

import net.blaklizt.symbiosis.sym_persistence.dao.impl.SymbiosisEventLogDaoImpl;
import net.blaklizt.symbiosis.sym_persistence.dao.impl.SymbiosisUserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 11/14/13
 * Time: 8:00 PM
 */

public class DaoManager
{
	@Autowired private SymbiosisEventLogDaoImpl eventLogDao;
	@Autowired private SymbiosisUserDaoImpl userDao;

	private static DaoManager daoManager = null;

	private DaoManager() { }

	public static DaoManager getInstance()
	{
		if (daoManager == null) daoManager = new DaoManager();
		return daoManager;
	}

	public SymbiosisEventLogDaoImpl getEventLogDao() { return eventLogDao; }
	public SymbiosisUserDaoImpl getUserDao() { return userDao; }
}
