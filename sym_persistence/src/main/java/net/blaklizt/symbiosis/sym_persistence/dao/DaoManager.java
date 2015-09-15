package net.blaklizt.symbiosis.sym_persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 11/14/13
 * Time: 8:00 PM
 */
@Component
public class DaoManager
{
	@Autowired private SymbiosisAuthUserDao symbiosisAuthUserDao;
	@Autowired private SymbiosisEventLogDao symbiosisEventLogDao;
	@Autowired private SymbiosisOptionDao symbiosisOptionDao;
	@Autowired private SymbiosisRoleDao symbiosisRoleDao;
	@Autowired private SymbiosisUserDao symbiosisUserDao;
	@Autowired private SymbiosisUserOptionDao symbiosisUserOptionDao;
	@Autowired private SymbiosisUserGroupSystemRoleDao symbiosisUserGroupSystemRoleDao;

	private static DaoManager daoManager = null;

	private DaoManager() { daoManager = this; }

	public static DaoManager getInstance()
	{
		if (daoManager == null) daoManager = new DaoManager();
		return daoManager;
	}

	public SymbiosisAuthUserDao getAuthUserDao() { return symbiosisAuthUserDao; }
	public SymbiosisEventLogDao getEventLogDao() { return symbiosisEventLogDao; }
	public SymbiosisOptionDao getOptionDao() { return symbiosisOptionDao; }
	public SymbiosisRoleDao getRoleDao() { return symbiosisRoleDao; }
	public SymbiosisUserDao getUserDao() { return symbiosisUserDao; }
	public SymbiosisUserOptionDao getUserOptionDao() { return symbiosisUserOptionDao; }
	public SymbiosisUserGroupSystemRoleDao getUserGroupSystemRoleDao() { return symbiosisUserGroupSystemRoleDao; }

}
