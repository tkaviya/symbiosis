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

import net.blaklizt.symbiosis.sym_core_lib.enumeration.SYM_RESPONSE_CODE;
import net.blaklizt.symbiosis.sym_core_lib.response.ResponseObject;
import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_user;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_channel;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_system;

import java.util.ArrayList;
//
//import static java.util.Arrays.asList;
//import static net.blaklizt.symbiosis.sym_persistence.helper.Channel.WEB;
//
//public interface SymbiosisAuthenticationProvider {
//
//	SymbiosisChainAuthenticationProvider getAuthProvider();
//
//	default void addMappedResponseCode(SYM_RESPONSE_CODE symResponseCode, Object returnResponse) {
//		getAuthProvider().addMappedResponseCode(symResponseCode, returnResponse);
//	}
//
//	default Object getMappedResponseCode(SYM_RESPONSE_CODE symResponseCode) {
//		return getAuthProvider().getMappedResponseCode(symResponseCode);
//	}
//
//	default SymbiosisChainAuthenticationProvider getAuthenticationChain(symbiosis_system system, symbiosis_channel channel) {
//		return new SymbiosisChainAuthenticationProvider(system, channel);
//	}
//}
//
//public interface SymbiosisWebAuthentication extends SymbiosisAuthenticationProvider {
//
//	default ResponseObject<symbiosis_user> authenticate(String username, String password) {
//		return getAuthProvider().authenticateWeb(username, password);
//	}
//
//	default void initializeAuthenticationChain() {
//		authenticationChain.put(WEB, new ArrayList<>(asList((AuthenticationStep)
//				this::getUserByUsernameAndChannel,
//			this::validatePassword)));
//	}
//
//}
//
//public interface SymbiosisAndroidAuthentication extends SymbiosisAuthenticationProvider {
//
//	default ResponseObject<symbiosis_user> authenticate(String username, String password) {
//		return getAuthProvider().authenticateAndroid(username, password);
//	}
//}
//
