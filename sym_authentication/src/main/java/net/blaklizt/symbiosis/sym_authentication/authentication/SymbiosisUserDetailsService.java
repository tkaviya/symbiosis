package net.blaklizt.symbiosis.sym_authentication.authentication;

import net.blaklizt.symbiosis.sym_authentication.security.Security;
import net.blaklizt.symbiosis.sym_persistence.User;
import net.blaklizt.symbiosis.sym_persistence.UserGroupRole;
import net.blaklizt.symbiosis.sym_persistence.dao.UserDao;
import net.blaklizt.symbiosis.sym_persistence.dao.UserGroupRoleDao;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
* Created with IntelliJ IDEA.
* User: tkaviya
* Date: 8/6/13
* Time: 7:06 PM
*/
@Service
//@Transactional(readOnly=true)
public class Authenticator implements UserDetailsService, PasswordEncoder {
	protected HashMap<String, List<SimpleGrantedAuthority>> grantedAuthoritiesCache = new HashMap<>();

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserGroupRoleDao userGroupRoleDao;

	private static final Logger logger = Logger.getLogger(SymbiosisAuthenticator.class.getSimpleName());

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		logger.info("Logging in user: " + username);
		User dbUser = userDao.findByUsername(username);

		if (dbUser == null) throw new UsernameNotFoundException("Could not find username " + username);

		boolean active;

		if (dbUser.getStatus() == User.UserStatus.ACTIVE.getValue()) active = true;
		else
		{
			active = false;
			logger.warn("Cannot login " + dbUser.getUsername() + " : Account is not active.");
		}

		return new org.springframework.security.core.userdetails.User(username, dbUser.getPassword(),
				active, active, active, active, getAuthorities(dbUser.getUserGroupID()));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(String userGroup)
	{
		List<SimpleGrantedAuthority> authList = new ArrayList<>();

		if (!grantedAuthoritiesCache.containsKey(userGroup))
		{
			logger.fine("Getting authorities for access group " + userGroup);

			List<UserGroupRole> userGroupRoles = userGroupRoleDao.findByUserGroup(userGroup);

			for (UserGroupRole userGroupRole : userGroupRoles)
			{
				logger.fine("Caching role " + userGroupRole.getRoleID());
				authList.add(new SimpleGrantedAuthority(userGroupRole.getRoleID()));
			}

			//cache the authorities to avoid future db hits.
			grantedAuthoritiesCache.put(userGroup, authList);
		}
		return grantedAuthoritiesCache.get(userGroup);
	}

	@Override
	public String encodePassword(String rawPass, Object salt) {
		//implement hectic encryption here
		logger.info("Encrypting [ " + rawPass + " with salt " + salt + " ]");
		return new String(Security.encrypt(rawPass.getBytes()));
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		//implement hectic encryption here
		logger.info("Comparing [ " + new String(Security.encrypt(rawPass.getBytes())) + " | " + rawPass + " ]");
		return encPass.matches(new String(Security.encrypt(rawPass.getBytes())));
	}
}
