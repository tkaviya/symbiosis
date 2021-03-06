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

import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_channel;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_system;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static net.blaklizt.symbiosis.sym_persistence.admin.SymbiosisConfig.WEB;

public class WebAuthenticationProvider extends SymbiosisChainAuthenticationProvider {

	@Override
	protected void initializeAuthenticationChain() {
		authenticationChain.put(WEB, new ArrayList<>(asList((AuthenticationStep)
			this::getUserByUsernameAndChannel,
			this::validatePassword)));
	}

	public WebAuthenticationProvider(symbiosis_system system, symbiosis_channel channel,
									 String username, String password) {
		super(system, channel);
		setAuthUsername(username);
		setAuthPassword(password);
	}
}
