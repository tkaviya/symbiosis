package net.blaklizt.symbiosis.sym_authentication.authentication;

import net.blaklizt.symbiosis.sym_authentication.security.Security;
import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_auth_user;
import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_user;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_channel;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_system;
import net.blaklizt.symbiosis.sym_persistence.structure.ResponseObject;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Logger;

import static net.blaklizt.symbiosis.sym_common.utilities.Validator.isValidPassword;
import static net.blaklizt.symbiosis.sym_persistence.admin.SymbiosisConfig.*;
import static net.blaklizt.symbiosis.sym_persistence.dao.super_class.SymbiosisUserHelper.findByActiveUsername;
import static net.blaklizt.symbiosis.sym_persistence.dao.super_class.SymbiosisUserHelper.findByUserId;

/**
* Created with IntelliJ IDEA.
* User: tkaviya
* Date: 8/6/13
* Time: 7:06 PM
*/
@Service
public class SymbiosisAuthenticator {

	protected static final Integer MAX_PASSWORD_TRIES = 5;

	static Logger logger = Logger.getLogger(SpringAuthenticationProvider.class.getSimpleName());

	public static ResponseObject<symbiosis_user> getUserByUsername(
		String username, symbiosis_system symbiosisSystem, symbiosis_channel symbiosisChannel) {
		logger.info("Searching for user by name " + username);
		return findByActiveUsername(username, symbiosisSystem, symbiosisChannel);
	}

	public static ResponseObject<symbiosis_auth_user> getAuthUserByUserIdSystemAndChannel(
		Long userId, symbiosis_system symbiosisSystem, symbiosis_channel symbiosisChannel) {
		logger.info("Searching for auth user by user Id " + userId
			+ ", system " + symbiosisSystem.getDescription()
			+ ", channel " + symbiosisChannel.getDescription());
		return findByUserId(userId, symbiosisSystem, symbiosisChannel, ACC_ACTIVE);
	}

	public static String encyptPassword(String rawPassword, String salt) {
		logger.info("Encrypting [ " + rawPassword + " with salt " + salt + " ]");
		String encryptedPassword = Security.encryptWithSalt(rawPassword, "SHA512", salt.getBytes());
		logger.info("Encrypted password: " + encryptedPassword);
		return encryptedPassword;
	}

	public static ResponseObject<symbiosis_auth_user> validatePassword(symbiosis_auth_user symbiosisAuthUser, String password) {

		ResponseObject<symbiosis_auth_user> response = new ResponseObject<>(GENERAL_ERROR, symbiosisAuthUser);

		if (symbiosisAuthUser == null) {
			return response.setResponseCode(INPUT_INVALID_REQUEST).setMessage("User was null");
		}

		symbiosis_user symbiosisUser = symbiosisAuthUser.getUser();

		symbiosisAuthUser.setLast_auth_date(new Date());

		if (symbiosisUser.getUser_status() != ACC_ACTIVE) {
			response.setResponseCode(symbiosisUser.getUser_status());
		}
		else {
			int passwordTries = symbiosisUser.getPassword_tries();

			if (passwordTries >= MAX_PASSWORD_TRIES) {
				response.setResponseCode(ACC_PASSWORD_TRIES_EXCEEDED);
			}
			else if (!isValidPassword(password)) {
				response.setResponseCode(INPUT_INVALID_REQUEST).setMessage("Password format was invalid");
				symbiosisUser.setPassword_tries(++passwordTries);
				if (symbiosisUser.getPassword_tries() >= MAX_PASSWORD_TRIES) {
					symbiosisUser.setUser_status(ACC_PASSWORD_TRIES_EXCEEDED);
				}
			}
			else if (symbiosisUser.getPassword().equals(encyptPassword(password, symbiosisUser.getSalt()))) {
				response.setResponseCode(SUCCESS);
				symbiosisUser.setPassword_tries(0);
			}
			else {
				response.setResponseCode(AUTH_INCORRECT_PASSWORD);
				symbiosisUser.setPassword_tries(++passwordTries);
				symbiosisAuthUser.setLast_login_date(symbiosisAuthUser.getLast_auth_date());
			}
		}
		symbiosisAuthUser.saveOrUpdate();
		symbiosisUser.saveOrUpdate();
		return response;
	}


//	protected static final HashMap<SYM_RESPONSE_CODE, Object> mappedResponseCode = new HashMap<>();
//
//	protected static final Logger logger = Logger.getLogger(SymbiosisAuthenticator.class.getSimpleName());
//
//	protected static final Integer MAX_PASSWORD_TRIES = 5;
//
//	static {
//		// We will mask any response code < 0 because it is a general system error that a user should not see
//		for (SYM_RESPONSE_CODE symResponseCode : values()) {
//			if (symResponseCode.code < 0) { mappedResponseCode.put(symResponseCode, GENERAL_ERROR); }
//		}
//
//		// We will mask certain authentication response codes to avoid username/password guessing
//		mappedResponseCode.put(DATA_NOT_FOUND,			AUTH_AUTHENTICATION_FAILED);
//		mappedResponseCode.put(INPUT_INVALID_REQUEST,	AUTH_AUTHENTICATION_FAILED);
//		mappedResponseCode.put(AUTH_INCORRECT_PASSWORD, AUTH_AUTHENTICATION_FAILED);
//		mappedResponseCode.put(AUTH_NON_EXISTENT,		AUTH_AUTHENTICATION_FAILED);
//	}
//
//	public static ResponseObject<symbiosis_user> validatePassword(symbiosis_user user, String rawPassword) {
//
//		ResponseObject<symbiosis_user> response = new ResponseObject<>(GENERAL_ERROR, user);
//
//		if (user == null) {
//			response.setResponseCode(INPUT_INVALID_REQUEST).setMessage("User was null");
//		}
//		else if (user.getUser_status().getId() != ACC_ACTIVE.code) {
//			response.setResponseCode(SYM_RESPONSE_CODE.valueOf(user.getUser_status().getId().intValue()));
//		}
//		else {
//			int passwordTries = user.getPassword_tries();
//
//			if (passwordTries >= MAX_PASSWORD_TRIES) {
//				response.setResponseCode(ACC_PASSWORD_TRIES_EXCEEDED);
//			}
//			else if (!isValidPassword(rawPassword)) {
//				response.setResponseCode(INPUT_INVALID_REQUEST).setMessage("Password format was invalid");
//				user.setPassword_tries(++passwordTries);
//			}
//			else if (user.getPassword().equals(encyptPassword(rawPassword, user.getSalt()))) {
//				response.setResponseCode(SUCCESS);
//				user.setPassword_tries(0);
//			}
//			else {
//				response.setResponseCode(AUTH_INCORRECT_PASSWORD);
//				user.setPassword_tries(++passwordTries);
//			}
//		}
//		return logAndReturn(response);
//	}
//
//	protected static <E>ResponseObject<E> logAndReturn(symbiosis_user user, ResponseObject<E> responseObject) {
//
//		SymbiosisLogHelper.logSystemEvent(new symbiosis_event_log(user.getId()));
//
//		if (responseObject.getResponseCode() != SUCCESS) {
//			Object mappedResponse = mappedResponseCode.get(responseObject.getResponseCode());
//			if (mappedResponse != null && mappedResponse instanceof SYM_RESPONSE_CODE) {
//				logger.info("Returning response " + mappedResponse + " for response code " + responseObject.getResponseCode());
//				responseObject.setResponseCode((SYM_RESPONSE_CODE) mappedResponse);
//			}
//		}
//		return responseObject;
//	}
//
//	public static String encyptPassword(String rawPassword, String salt) {
//		logger.info("Encrypting [ " + rawPassword + " with salt " + salt + " ]");
//		String encryptedPassword = Security.encryptWithSalt(rawPassword, "SHA512", salt.getBytes());
//		logger.info("Encrypted password: " + encryptedPassword);
//		return encryptedPassword;
//	}
//
//	@Override
//	public SymbiosisUserDetails loadUserByUsername(String username) throws UsernameNotFoundException
//	{
//		logger.info("Getting user by username: " + username);
//
//		ResponseObject<symbiosis_user> dbSymbiosisUser = findByUsername(username);
//
//		if (dbSymbiosisUser == null) throw new UsernameNotFoundException("Could not find username " + username);
//
//		boolean active = true;
//
////		if (dbSymbiosisUser.getSymbiosisUserStatusID() == ResponseCode.ACC_ACTIVE.responseCode()) active = true;
////		else
////		{
////			active = false;
////			logger.warning("Cannot login " + dbSymbiosisUser.getUsername() + " : Account is not active.");
////		}
//
//		return new SymbiosisUserDetails(dbSymbiosisUser, active, getAuthorities(dbSymbiosisUser.getSymbiosisUserGroup().getDescription()));
//	}
//
//	public ResponseCode registerUser(User symbiosisUser)
//	{
//		SymbiosisUser newSymbiosisUser = new SymbiosisUser();
//		SymbiosisAuthUser newSymbiosisAuthUser = new SymbiosisAuthUser();
//
//		//generate password salt
//		byte[] passwordSalt = Security.generateSecureRandomBytes();
//
//		//update object to new data
//		newSymbiosisUser.setSalt(new String(passwordSalt));
//		newSymbiosisUser.setPassword(encodePassword(symbiosisUser.getPassword(), passwordSalt));
//
//
//		//persist new data
//		userDao.saveOrUpdate(newSymbiosisUser);
//
//		newSymbiosisAuthUser.setSymbiosisUser(newSymbiosisUser);
//		newSymbiosisAuthUser.setSymbiosisUserID(newSymbiosisUser.getSymbiosisUserID());
//		newSymbiosisAuthUser.setLastLoginDate(new Date());
//		newSymbiosisAuthUser.setLastAuthDate(new Date());
//		newSymbiosisAuthUser.setSymbiosisUserStatusID(ResponseCode.ACC_ACTIVE.responseCode());
//
//		authUserDao.saveOrUpdate(newSymbiosisAuthUser);
//
//		//write event log
//
//		return ResponseCode.SUCCESS;
//	}
//
//
//	public ResponseObject registerNewUser(SymbiosisUser registerUser)
//	{
//		ResponseCode registrationResponse = ResponseCode.GENERAL_ERROR;
//			 if (!Validator.isValidUsername(registerUser.getUsername()))      {   registrationResponse = ResponseCode.INVALID_USERNAME;  }
//		else if (!Validator.isValidPassword(registerUser.getPassword()))      {   registrationResponse = ResponseCode.INVALID_PASSWORD;  }
//		else if (CommonUtilities.isNullOrEmpty(registerUser.getEmail())
//	         || !Validator.isValidEmailAddress(registerUser.getEmail()))
//		{
//			registrationResponse = ResponseCode.INPUT_INVALID_EMAIL;
//		}
//		else if (CommonUtilities.isNullOrEmpty(registerUser.getMsisdn())
//	         ||       !Validator.isValidMsisdn(registerUser.getMsisdn()))
//		{
//			registrationResponse = ResponseCode.INPUT_INVALID_MSISDN;
//		}
//		else if (CommonUtilities.isNullOrEmpty(registerUser.getFirstName())
//	         ||    !Validator.isValidFirstName(registerUser.getFirstName()))
//		{
//			registrationResponse = ResponseCode.INVALID_FIRST_NAME;
//		}
//		else if (CommonUtilities.isNullOrEmpty(registerUser.getLastName())
//	         ||     !Validator.isValidLastName(registerUser.getLastName()))
//		{
//			registrationResponse = ResponseCode.INVALID_LAST_NAME;
//		}
//		else
//		{
//			//data is valid. attempt registration
//			if (userDao.findByUsername(registerUser.getUsername()) != null)
//			{
//				registrationResponse = ResponseCode.PREVIOUS_REGISTRATION_FOUND;
//			}
//			else if (userDao.findByEmail(registerUser.getEmail()) != null)
//			{
//				registrationResponse = ResponseCode.PREVIOUS_EMAIL_FOUND;
//			}
//			else if (userDao.findByMsisdn(registerUser.getMsisdn()) != null)
//			{
//				registrationResponse = ResponseCode.PREVIOUS_MSISDN_FOUND;
//			}
//			else
//			{
//				//no existing user or invalid data. registration can proceed
//
//				//if name is not passed, use firstname or lastname or both
//				registerUser.setPassword(this.encodePassword(registerUser.getPassword(), Security.generateSecureRandomBytes()));
////				registerUser.setLastLoginDate(new Date());
//				registerUser.setAuthToken(new String(Security.generateSecureRandomBytes()));
//
//				userDao.saveOrUpdate(registerUser);
//
//				logger.info("Registration successful. Populating response");
//
//				try
//				{
//					JSONObject registrationData = new JSONObject(registrationResponse);
//					registrationData.put("auth_token", registerUser.getAuthToken());
//					logger.info("Returning response:" + registrationData.toString());
//					registrationResponse = ResponseCode.SUCCESS;
//				}
//				catch (Exception ex)
//				{
//					registrationResponse = ResponseCode.GENERAL_ERROR;
//				}
//			}
//		}
//		return new ResponseObject(registrationResponse, registerUser);
//	}
//
//	private Collection<? extends GrantedAuthority> getAuthorities(String userGroup)
//	{
//		List<SimpleGrantedAuthority> authList = new ArrayList<>();
//
//		if (!grantedAuthoritiesCache.containsKey(userGroup))
//		{
//			logger.fine("Getting authorities for access group " + userGroup);
//
//			List<SymbiosisUserGroupSystemRole> userGroupRoles = symbiosisUserGroupSystemRoleDaoImpl.findByUserGroup(userGroup);
//
//			for (SymbiosisUserGroupSystemRole userGroupRole : userGroupRoles)
//			{
//				logger.fine("Caching role " + userGroupRole.getSymbiosisRoleID());
//				authList.add(new SimpleGrantedAuthority(userGroupRole.getSymbiosisRoleID().toString()));
//			}
//
//			//cache the authorities to avoid future db hits.
//			grantedAuthoritiesCache.put(userGroup, authList);
//		}
//		return grantedAuthoritiesCache.get(userGroup);
//	}
//
//
//	public ResponseCode authenticateUser(SymbiosisUserDetails userDetails)
//	{
//		return authenticateUser(userDetails, userDetails.getPassword());
//	}
}
