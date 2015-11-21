package net.blaklizt.symbiosis.sym_persistence.helper;

import net.blaklizt.symbiosis.sym_persistence.dao.super_class.*;
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
    @Autowired private SymbiosisChannelDao symbiosisChannelDao;
	@Autowired private SymbiosisEventLogDao symbiosisEventLogDao;
    @Autowired private SymbiosisGroupDao symbiosisGroupDao;
    @Autowired private SymbiosisGroupSystemRoleDao symbiosisGroupSystemRoleDao;
    @Autowired private SymbiosisOptionDao symbiosisOptionDao;
    @Autowired private SymbiosisRoleDao symbiosisRoleDao;
    @Autowired private SymbiosisUserDao symbiosisUserDao;
    @Autowired private SymbiosisUserOptionDao symbiosisUserOptionDao;

	private static DaoManager daoManager = null;

	private DaoManager() {
        daoManager = this;
    }

	public static DaoManager getInstance()
	{
		if (daoManager == null) daoManager = new DaoManager();
		return daoManager;
	}


	public SymbiosisAuthUserDao getAuthUserDao() { return symbiosisAuthUserDao; }
    public SymbiosisChannelDao getChannelDao() { return symbiosisChannelDao; }
	public SymbiosisEventLogDao getEventLogDao() { return symbiosisEventLogDao; }
    public SymbiosisGroupDao getGroupDao() { return symbiosisGroupDao; }
    public SymbiosisOptionDao getOptionDao() { return symbiosisOptionDao; }
    public SymbiosisGroupSystemRoleDao getUserGroupSystemRoleDao() { return symbiosisGroupSystemRoleDao; }
    public SymbiosisRoleDao getRoleDao() { return symbiosisRoleDao; }
    public SymbiosisUserDao getUserDao() { return symbiosisUserDao; }
    public SymbiosisUserOptionDao getUserOptionDao() { return symbiosisUserOptionDao; }

}
