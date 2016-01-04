package net.blaklizt.symbiosis.sym_authentication.authentication;

import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_user;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_channel;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_system;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_user_status;
import net.blaklizt.symbiosis.sym_persistence.structure.ResponseObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static net.blaklizt.symbiosis.sym_authentication.authentication.SymbiosisAuthenticator.getUserByUsername;
import static net.blaklizt.symbiosis.sym_persistence.admin.SymbiosisConfig.*;

/**
* Created with IntelliJ IDEA.
* User: tkaviya
* Date: 8/6/13
* Time: 7:06 PM
*/
@Service
public class SymbiosisUserDetailsService implements UserDetailsService {

	protected HashMap<String, List<SimpleGrantedAuthority>> grantedAuthoritiesCache = new HashMap<>();

	protected symbiosis_system symbiosisSystem;

	protected symbiosis_channel symbiosisChannel;

	public void setSymbiosisSystem(symbiosis_system symbiosisSystem) {
		this.symbiosisSystem = symbiosisSystem;
	}

	public void setSymbiosisChannel(symbiosis_channel symbiosisChannel) {
		this.symbiosisChannel = symbiosisChannel;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		ResponseObject<symbiosis_user> userResponse = getUserByUsername(username, symbiosisSystem, symbiosisChannel);

		symbiosis_user user = userResponse.getResponseObject();

		boolean accountNonExpired = true, accountNonLocked = true, credentialsNonExpired = true, enabled = false;

		symbiosis_user_status userStatus = user.getUser_status();

		if (userStatus.equals(ACC_ACTIVE)) {
			enabled = true;
		} else if (userStatus.equals(ACC_INACTIVE) || userStatus.equals(ACC_SUSPENDED)) {
			accountNonLocked = false;
		} else if (userStatus.equals(ACC_CLOSED)) {
			accountNonExpired = false;
		} else if (userStatus.equals(ACC_PASSWORD_TRIES_EXCEEDED) || userStatus.equals(ACC_PASSWORD_EXPIRED)) {
			credentialsNonExpired = false;
		}

		return new User(username, user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
			getAuthorities(null));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(String userGroup)
	{
		List<SimpleGrantedAuthority> authList = new ArrayList<>();

		if (!grantedAuthoritiesCache.containsKey(userGroup))
		{
//			logger.fine("Getting authorities for access group " + userGroup);
//
//			List<SymbiosisUserGroupSystemRole> userGroupRoles = symbiosisUserGroupSystemRoleDaoImpl.findByUserGroup(userGroup);
//
//			for (SymbiosisUserGroupSystemRole userGroupRole : userGroupRoles)
//			{
//				logger.fine("Caching role " + userGroupRole.getSymbiosisRoleID());
//				authList.add(new SimpleGrantedAuthority(userGroupRole.getSymbiosisRoleID().toString()));
//			}

			//cache the authorities to avoid future db hits.
			grantedAuthoritiesCache.put(userGroup, authList);
		}
		return grantedAuthoritiesCache.get(userGroup);
	}

}
