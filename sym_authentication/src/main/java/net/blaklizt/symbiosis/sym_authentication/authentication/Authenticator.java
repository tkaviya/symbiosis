package net.blaklizt.symbiosis.sym_authentication.authentication;

import net.blaklizt.symbiosis.sym_authentication.security.Security;
import net.blaklizt.symbiosis.sym_persistence.User;
import net.blaklizt.symbiosis.sym_persistence.UserGroupRole;
import net.blaklizt.symbiosis.sym_persistence.dao.UserDao;
import net.blaklizt.symbiosis.sym_persistence.dao.UserGroupRoleDao;
import org.apache.log4j.Logger;
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
	

	private static final Logger log4j = Logger.getLogger(Authenticator.class.getSimpleName());

	@Override
	public SymbiosisUserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		log4j.info("Logging in user: " + username);
		User symbiosisUser = userDao.findByUsername(username);

		if (symbiosisUser == null) throw new UsernameNotFoundException("Could not find username " + username);

		boolean active;

		if (symbiosisUser.getStatus() == User.UserStatus.ACTIVE.getValue()) active = true;
		else
		{
			active = false;
			log4j.warn("Cannot login " + symbiosisUser.getUsername() + " : Account is not active.");
		}
				
		return new SymbiosisUserDetails(dbUser, active, getAuthorities(dbUser.getUserGroupID());
	}

	@Override
	public String encodePassword(String rawPass, Object salt) {
		//implement hectic encryption here
		log4j.info("Encrypting [ " + rawPass + " with salt " + salt + " ]");
		String encryptedPassword = Security.encryptWithSalt(rawPass, "SHA512", String.valueOf(salt));
		log4j.debug("Encrypted password: " + encryptedPassword);
		return encryptedPassword;
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		//implement hectic encryption here
		log4j.info("Comparing [ " + new String(Security.encrypt(rawPass.getBytes())) + " | " + rawPass + " ]");
		return encPass.matches(new String(Security.encrypt(rawPass.getBytes())));
	}

	public ResponseCode registerUser(User symbiosisUser)
	{
		//generate password salt
		byte[] passwordSalt = Security.generateSecureRandomBytes();
		
		//update object to new data
		symbiosisUser.setSalt(passwordSalt);
		symbiosisUser.setPassword(encodePassword(symbiosisUser.getPassword(), passwordSalt));
		symbiosisUser.setLastAccessTime(new Date());
		symbiosisUser.setActive(true);
		
		//write event log
		
		//persist new data
		
		return ResponseCode.SUCCESS;
	}

	public ResponseCode authenticateUser(User symbiosisUser, String password)
	{
		try
		{
			if (!symbiosisUser.isActive())
			{
				//write event log
				return ResponseCode.NOT_ACTIVE;
			}
			if (isPasswordValid(symbiosisUser.getPassword(),
								encodePassword(password, symbiosisUser.getSalt()),
								symbiosisUser.getSalt())
			{
				//update auth_token
				//update last login
				//write event log
				return ResponseCode.SUCCESS;
			}
			else
			{
				//update password tries
				//write event log
				return ResponseCode.AUTHENTICATION_FAILED;
			}
		}
		catch (Exception ex)
		{
			//write event log
			//send email
			return ResponseCode.GENERAL_ERROR;
		}
	}

	private Collection<? extends GrantedAuthority> getAuthorities(String userGroup)
	{
		List<SimpleGrantedAuthority> authList = new ArrayList<>();

		if (!grantedAuthoritiesCache.containsKey(userGroup))
		{
			log4j.debug("Getting authorities for access group " + userGroup);

			List<UserGroupRole> userGroupRoles = userGroupRoleDao.findByUserGroup(userGroup);

			for (UserGroupRole userGroupRole : userGroupRoles)
			{
				log4j.debug("Caching role " + userGroupRole.getRoleID());
				authList.add(new SimpleGrantedAuthority(userGroupRole.getRoleID()));
			}

			//cache the authorities to avoid future db hits.
			grantedAuthoritiesCache.put(userGroup, authList);
		}
		return grantedAuthoritiesCache.get(userGroup);
	}
}
