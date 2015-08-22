package net.blaklizt.symbiosis.sym_authentication.authentication;

import net.blaklizt.symbiosis.sym_common.response.ResponseCode;
import net.blaklizt.symbiosis.sym_persistence.SymbiosisUser;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SymbiosisUserDetails extends org.springframework.security.core.userdetails.User
{
	SymbiosisUser symbiosisUser;

	public SymbiosisUserDetails(SymbiosisUser user, boolean isActive, Collection<? extends GrantedAuthority> authorities)
	{
		super(user.getUsername(),
			  user.getPassword(),
			  isActive, isActive, isActive,
			  isActive, authorities);
		symbiosisUser = user;
		symbiosisUser.setUsername(user.getUsername());
//		symbiosisUser.setSymbiosisUserStatusID(isActive ? ResponseCode.ACTIVE.responseCode() : ResponseCode.INACTIVE.responseCode());
		symbiosisUser.setPassword(user.getPassword());
	}
	
	public SymbiosisUser getSymbiosisUser() { return symbiosisUser; }
}
