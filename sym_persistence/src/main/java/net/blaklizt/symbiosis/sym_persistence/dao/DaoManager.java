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
	@Autowired private SymbiosisEventLogDao symbiosisEventLogDao;
	@Autowired private SymbiosisUserDao symbiosisUserDao;
	@Autowired private SymbiosisAuthUserDao symbiosisAuthUserDao;
	@Autowired private SymbiosisRoleDao symbiosisRoleDao;
	@Autowired private SymbiosisUserGroupSystemRoleDao symbiosisUserGroupSystemRoleDao;

	private static DaoManager daoManager = null;

	private DaoManager() { }

	public static DaoManager getInstance()
	{
		if (daoManager == null) daoManager = new DaoManager();
		return daoManager;
	}

	public SymbiosisEventLogDao getEventLogDao() { return symbiosisEventLogDao; }
	public SymbiosisUserDao getUserDao() { return symbiosisUserDao; }
	public SymbiosisAuthUserDao getAuthUserDao() { return symbiosisAuthUserDao; }
	public SymbiosisRoleDao getRoleDao() { return symbiosisRoleDao; }
	public SymbiosisUserGroupSystemRoleDao getUserGroupSystemRoleDao() { return symbiosisUserGroupSystemRoleDao; }

}
