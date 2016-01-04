package net.blaklizt.symbiosis.sym_authentication.authentication;

/* *************************************************************************
 * Created:     2016/01/01                                                 *
 * Author:      Tich de Blak (Tsungai Kaviya)                              *
 * Copyright:   Blaklizt Entertainment                                     *
 * Website:     http://www.blaklizt.net                                    *
 * Contact:     blaklizt@gmail.com                                         *
 *                                                                         *
 * This program is free software; you can redistribute it and/or modify    *
 * it under the terms of the GNU General Public License as published by    *
 * the Free Software Foundation; either version 2 of the License, or       *
 * (at your option) any later version.                                     *
 *                                                                         *
 * This program is distributed in the hope that it will be useful,         *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of          *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.    See the         *
 * GNU General Public License for more details.                            *
 * *************************************************************************
*/

import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_user;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_channel;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_system;
import net.blaklizt.symbiosis.sym_persistence.structure.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.logging.Logger;

import static net.blaklizt.symbiosis.sym_authentication.authentication.SymbiosisChainAuthenticationProvider.addMappedResponseCode;
import static net.blaklizt.symbiosis.sym_authentication.authentication.SymbiosisChainAuthenticationProvider.getMappedResponseCode;
import static net.blaklizt.symbiosis.sym_persistence.admin.SymbiosisConfig.*;

public class SpringAuthenticationProvider implements AuthenticationProvider {

	Logger logger = Logger.getLogger(SpringAuthenticationProvider.class.getSimpleName());

	private final symbiosis_system symbiosisSystem;

	private final symbiosis_channel symbiosisChannel = WEB;

	@Autowired
	private SymbiosisUserDetailsService userService;

	SpringAuthenticationProvider(symbiosis_system symbiosisSystem) {
		this.symbiosisSystem = symbiosisSystem;
		userService.setSymbiosisSystem(symbiosisSystem);
		userService.setSymbiosisChannel(symbiosisChannel);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		logger.info("Authenticating user " + authentication.getPrincipal() + " on to channel WEB");

		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		WebAuthenticationProvider webAuthenticationProvider = new WebAuthenticationProvider(
			symbiosisSystem, symbiosisChannel, username, password
		);

		logger.info("Initialized WebAuthenticationProvider chain provider.");

		addMappedResponseCode(AUTH_NON_EXISTENT, new BadCredentialsException(AUTH_NON_EXISTENT.getResponse_message()));
		addMappedResponseCode(ACC_PASSWORD_EXPIRED, new BadCredentialsException(ACC_PASSWORD_EXPIRED.getResponse_message()));

		logger.info("Running authentication chain.");
		ResponseObject<symbiosis_user> symbiosisUserResponse = webAuthenticationProvider.authenticateUser();

		logger.info("Authentication response: " + symbiosisUserResponse.getResponseCode().getResponse_message());
		if (symbiosisUserResponse.getResponseCode() == SUCCESS) {

			symbiosis_user user = symbiosisUserResponse.getResponseObject();

			Collection<? extends GrantedAuthority> authorities = null;//user.getAuthorities();

			logger.info("Returning authenticated used with granted authorities");
			return new UsernamePasswordAuthenticationToken(user, password, authorities);
		} else {
			Object result = getMappedResponseCode(symbiosisUserResponse.getResponseCode());
			logger.info("Returning mapped auth response: " + result);
			if (result instanceof AuthenticationException) {
				throw (AuthenticationException)result;
			}
			throw new RuntimeException(symbiosisUserResponse.getResponseCode().getResponse_message());
		}
	}


//	private Collection<? extends GrantedAuthority> getAuthorities(String userGroup)
//	{
//		List<SimpleGrantedAuthority> authList = new ArrayList<>();
//
//		if (!grantedAuthoritiesCache.containsKey(userGroup))
//		{
//			logger.fine("Getting authorities for access group " + userGroup);
//
//			List<UserGroupRole> userGroupRoles = userGroupRoleDao.findByUserGroup(userGroup);
//
//			for (UserGroupRole userGroupRole : userGroupRoles)
//			{
//				logger.fine("Caching role " + userGroupRole.getRoleID());
//				authList.add(new SimpleGrantedAuthority(userGroupRole.getRoleID()));
//			}
//
//			//cache the authorities to avoid future db hits.
//			grantedAuthoritiesCache.put(userGroup, authList);
//		}
//		return grantedAuthoritiesCache.get(userGroup);
//	}


	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(Authentication.class);
	}
}
