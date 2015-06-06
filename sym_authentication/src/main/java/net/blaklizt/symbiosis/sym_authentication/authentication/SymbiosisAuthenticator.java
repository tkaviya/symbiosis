package net.blaklizt.symbiosis.sym_authentication.authentication;

import net.blaklizt.symbiosis.sym_authentication.security.Security;
import net.blaklizt.symbiosis.sym_common.response.ResponseCode;
import net.blaklizt.symbiosis.sym_common.response.ResponseObject;
import net.blaklizt.symbiosis.sym_common.utilities.CommonUtilities;
import net.blaklizt.symbiosis.sym_common.utilities.Validator;
import net.blaklizt.symbiosis.sym_persistence.SymbiosisUser;
import net.blaklizt.symbiosis.sym_persistence.UserGroupRole;
import net.blaklizt.symbiosis.sym_persistence.dao.UserDao;
import net.blaklizt.symbiosis.sym_persistence.dao.UserGroupRoleDao;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
public class SymbiosisAuthenticator implements UserDetailsService, PasswordEncoder
{
	protected HashMap<String, List<SimpleGrantedAuthority>> grantedAuthoritiesCache = new HashMap<>();

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserGroupRoleDao userGroupRoleDao;
	

	private static final Logger logger = Logger.getLogger(SymbiosisAuthenticator.class.getSimpleName());

	@Override
	public SymbiosisUserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		logger.info("Logging in user: " + username);
		SymbiosisUser dbSymbiosisUser = userDao.findByUsername(username);

		if (dbSymbiosisUser == null) throw new UsernameNotFoundException("Could not find username " + username);

		boolean active;

		if (dbSymbiosisUser.getUserStatusID() == ResponseCode.ACTIVE.responseCode()) active = true;
		else
		{
			active = false;
			logger.warn("Cannot login " + dbSymbiosisUser.getUsername() + " : Account is not active.");
		}
				
		return new SymbiosisUserDetails(dbSymbiosisUser, active, getAuthorities(dbSymbiosisUser.getUserGroup().getDescription()));
	}

	@Override
	public String encodePassword(String rawPass, Object salt) {
		//implement hectic encryption here
		logger.info("Encrypting [ " + rawPass + " with salt " + salt + " ]");
		String encryptedPassword = Security.encryptWithSalt(rawPass, "SHA512", String.valueOf(salt).getBytes());
		logger.debug("Encrypted password: " + encryptedPassword);
		return encryptedPassword;
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		//implement hectic encryption here
		logger.info("Comparing [ " + new String(Security.encrypt(rawPass)) + " | " + rawPass + " ]");
		return encPass.matches(new String(Security.encrypt(rawPass)));
	}

	public ResponseCode registerUser(User symbiosisUser)
	{
		SymbiosisUser newSymbiosisUser = new SymbiosisUser();


		//generate password salt
		byte[] passwordSalt = Security.generateSecureRandomBytes();
		
		//update object to new data
		newSymbiosisUser.setSalt(String.valueOf(passwordSalt));
		newSymbiosisUser.setPassword(encodePassword(symbiosisUser.getPassword(), passwordSalt));
		newSymbiosisUser.setLastLoginDate(new Date());
		newSymbiosisUser.setUserStatusID(ResponseCode.ACTIVE.responseCode());
		
		//write event log
		
		//persist new data
		
		return ResponseCode.SUCCESS;
	}

	public ResponseCode authenticateUser(User authUser, String password)
	{
		try
		{

			SymbiosisUserDetails symAuthUser;
			if (!(authUser instanceof SymbiosisUserDetails))
				symAuthUser = (SymbiosisUserDetails)authUser;
			else
				symAuthUser = loadUserByUsername(authUser.getUsername());

			if (symAuthUser == null) return ResponseCode.INVALID_USERNAME;

			if (symAuthUser.getSymbiosisUser().getUserStatusID() != ResponseCode.ACTIVE.responseCode())
			{
				//write event log
				return ResponseCode.valueOf(symAuthUser.getSymbiosisUser().getUserStatusID());
			}
			if (isPasswordValid(authUser.getPassword(),
			                    encodePassword(password, authUser.getPassword()),
			                    symAuthUser.getSymbiosisUser().getSalt()))
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

	public ResponseObject registerNewUser(SymbiosisUser registerUser)
	{
		ResponseCode registrationResponse = ResponseCode.GENERAL_ERROR;
			 if (!Validator.isValidUsername(registerUser.getUsername()))      {   registrationResponse = ResponseCode.INVALID_USERNAME;  }
		else if (!Validator.isValidPassword(registerUser.getPassword()))      {   registrationResponse = ResponseCode.INVALID_PASSWORD;  }
		else if (CommonUtilities.isNullOrEmpty(registerUser.getUserAttribute().getEmail())
	         || !Validator.isValidEmailAddress(registerUser.getUserAttribute().getEmail()))
		{
			registrationResponse = ResponseCode.INVALID_EMAIL;
		}
		else if (CommonUtilities.isNullOrEmpty(registerUser.getUserAttribute().getMsisdn())
	         ||       !Validator.isValidMsisdn(registerUser.getUserAttribute().getMsisdn()))
		{
			registrationResponse = ResponseCode.INVALID_MSISDN;
		}
		else if (CommonUtilities.isNullOrEmpty(registerUser.getUserAttribute().getFirstName())
	         ||    !Validator.isValidFirstName(registerUser.getUserAttribute().getFirstName()))
		{
			registrationResponse = ResponseCode.INVALID_FIRST_NAME;
		}
		else if (CommonUtilities.isNullOrEmpty(registerUser.getUserAttribute().getLastName())
	         ||     !Validator.isValidLastName(registerUser.getUserAttribute().getLastName()))
		{
			registrationResponse = ResponseCode.INVALID_LAST_NAME;
		}
		else
		{
			//data is valid. attempt registration
			if (userDao.findByUsername(registerUser.getUsername()) != null)
			{
				registrationResponse = ResponseCode.PREVIOUS_REGISTRATION_FOUND;
			}
			else if (userDao.findByEmail(registerUser.getUserAttribute().getEmail()) != null)
			{
				registrationResponse = ResponseCode.PREVIOUS_EMAIL_FOUND;
			}
			else if (userDao.findByMsisdn(registerUser.getUserAttribute().getMsisdn()) != null)
			{
				registrationResponse = ResponseCode.PREVIOUS_MSISDN_FOUND;
			}
			else
			{
				//no existing user or invalid data. registration can proceed

				//if name is not passed, use firstname or lastname or both
				registerUser.setPassword(this.encodePassword(registerUser.getPassword(), Security.generateSecureRandomBytes()));
				registerUser.setLastLoginDate(new Date());
				registerUser.setAuthToken(String.valueOf(Security.generateSecureRandomBytes()));

				userDao.saveOrUpdate(registerUser);

				logger.info("Registration successful. Populating response");

				try
				{
					JSONObject registrationData = new JSONObject(registrationResponse);
					registrationData.put("auth_token", registerUser.getAuthToken());
					logger.info("Returning response:" + registrationData.toString());
					registrationResponse = ResponseCode.SUCCESS;
				}
				catch (Exception ex)
				{
					registrationResponse = ResponseCode.GENERAL_ERROR;
				}
			}
		}
		return new ResponseObject(registrationResponse, registerUser);
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


	public ResponseCode authenticateUser(SymbiosisUserDetails userDetails)
	{
		return authenticateUser(userDetails, userDetails.getPassword());
	}
}
