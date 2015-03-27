package net.blaklizt.symbiosis.sym_authentication.authentication;

import net.blaklizt.symbiosis.sym_common.response.ResponseCode;
import net.blaklizt.symbiosis.sym_common.response.ResponseObject;
import net.blaklizt.symbiosis.sym_common.utilities.CommonUtilities;
import net.blaklizt.symbiosis.sym_common.utilities.Validator;
import net.blaklizt.symbiosis.sym_persistence.SymbiosisUser;
import net.blaklizt.symbiosis.sym_persistence.UserGroupRole;
import net.blaklizt.symbiosis.sym_persistence.dao.UserDao;
import net.blaklizt.symbiosis.sym_persistence.dao.UserGroupRoleDao;
import org.apache.log4j.Logger;
import org.apache.logger.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

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
	

	private static final Logger logger = Logger.getLogger(Authenticator.class.getSimpleName());

	@Override
	public SymbiosisUserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		log4j.info("Logging in user: " + username);
		User symbiosisUser = userDao.findByUsername(username);
		logger.info("Logging in user: " + username);
		SymbiosisUser dbSymbiosisUser = userDao.findByUsername(username);

		if (symbiosisUser == null) throw new UsernameNotFoundException("Could not find username " + username);
		if (dbSymbiosisUser == null) throw new UsernameNotFoundException("Could not find username " + username);

		boolean active;

		if (symbiosisUser.getStatus() == User.UserStatus.ACTIVE.getValue()) active = true;
		if (dbSymbiosisUser.getStatus() == SymbiosisUser.UserStatus.ACTIVE.getValue()) active = true;
		else
		{
			active = false;
			log4j.warn("Cannot login " + symbiosisUser.getUsername() + " : Account is not active.");
			logger.warn("Cannot login " + dbSymbiosisUser.getUsername() + " : Account is not active.");
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
		return new org.springframework.security.core.userdetails.User(username, dbSymbiosisUser.getPassword(),
				active, active, active, active, getAuthorities(dbSymbiosisUser.getUserGroupID()));
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

	public ResponseObject registerNewUser(SymbiosisUser newSymbiosisUser)
	{
		ResponseCode registrationResponse = ResponseCode.GENERAL_ERROR;
			 if (!Validator.isValidUsername(newSymbiosisUser.getUsername()))      {   registrationResponse = ResponseCode.INVALID_USERNAME;  }
		else if (!Validator.isValidPassword(newSymbiosisUser.getPassword()))      {   registrationResponse = ResponseCode.INVALID_PASSWORD;  }
		else if (newSymbiosisUser.getEmail() != null && !Validator.isValidEmailAddress(newSymbiosisUser.getEmail()))
		{
			registrationResponse = ResponseCode.INVALID_EMAIL;
		}
		else if (newSymbiosisUser.getUserAttribute().getMsisdn() != null && !Validator.isValidMsisdn(newSymbiosisUser.getUserAttribute().getMsisdn()))
		{
			registrationResponse = ResponseCode.INVALID_MSISDN;
		}
		else if (newSymbiosisUser.getFirstName() != null && !Validator.isValidFirstName(newSymbiosisUser.getFirstName()))
		{
			registrationResponse = ResponseCode.INVALID_FIRST_NAME;
		}
		else if (newSymbiosisUser.getLastName() != null && !Validator.isValidLastName(newSymbiosisUser.getLastName()))
		{
			registrationResponse = ResponseCode.INVALID_LAST_NAME;
		}
		else if (newSymbiosisUser.getParameter("name") != null && !Validator.isValidLastName(newSymbiosisUser.getParameter("name")))
		{
			registrationResponse = ResponseCode.INVALID_NAME;
		}
		else
		{
			//data is valid. attempt registration
			if (userDao.findByUsername(newSymbiosisUser.getUsername()) != null)
			{
				registrationResponse = ResponseCode.PREVIOUS_REGISTRATION_FOUND;
			}
			else if (userDao.findByEmail(newSymbiosisUser.getEmail()) != null)
			{
				registrationResponse = ResponseCode.PREVIOUS_EMAIL_FOUND;
			}
			else if (userDao.findByMsisdn(newSymbiosisUser.getUserAttribute().getMsisdn()) != null)
			{
				registrationResponse = ResponseCode.PREVIOUS_MSISDN_FOUND;
			}
			else
			{
				//no existing user/invalid data. registration can proceed
				SymbiosisUser newSymbiosisUser = new SymbiosisUser();

				//if name is passed, use that as name
				newSymbiosisUser.setName(newSymbiosisUser.getParameter("name") != null ? newSymbiosisUser.getParameter("name") : null);

				//if name is not passed, use firstname or lastname or both
				if (newSymbiosisUser.getName() == null && (newSymbiosisUser.getFirstName() != null || newSymbiosisUser.getLastName() != null))
				{
					String name = newSymbiosisUser.getFirstName();
					if (CommonUtilities.isNullOrEmpty(name))
					{
						name = newSymbiosisUser.getLastName();
					}
					else if (!CommonUtilities.isNullOrEmpty(newSymbiosisUser.getLastName()))
					{
						name += " " + newSymbiosisUser.getLastName();
					}
					newSymbiosisUser.setName(name);
				}

				newSymbiosisUser.setUsername(newSymbiosisUser.getUsername());
				newSymbiosisUser.setPassword(newSymbiosisUser.getParameter("password"));
				newSymbiosisUser.setEmail(newSymbiosisUser.getEmail());
				newSymbiosisUser.setLastLoginDate(new Date());

				ResponseCode registrationResponse = authenticator.register(newSymbiosisUser);

				registrationResponse = registrationResponse;

				if (registrationResponse == ResponseCode.SUCCESS)
				{
					logger.info("Authentiation successful.");
					JSONObject registrationData = new JSONObject(registrationResponse);
					registrationData.put("auth_token", newSymbiosisUser.getAuthToken());
					registrationResponse = registrationData.toString();
				}
				logger.info("Returning response:" + registrationResponse);
			}
		}
	}

	private Collection<? extends GrantedAuthority> getAuthorities(String userGroup)
	{
		List<SimpleGrantedAuthority> authList = new ArrayList<>();

		if (!grantedAuthoritiesCache.containsKey(userGroup))
		{
			logger.debug("Getting authorities for access group " + userGroup);

			List<UserGroupRole> userGroupRoles = userGroupRoleDao.findByUserGroup(userGroup);

			for (UserGroupRole userGroupRole : userGroupRoles)
			{
				logger.debug("Caching role " + userGroupRole.getRoleID());
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
