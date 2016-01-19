package net.blaklizt.symbiosis.sym_persistence.helper;

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

import net.blaklizt.symbiosis.sym_common.configuration.ThreadPoolManager;
import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_auth_user;
import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_event_log;
import net.blaklizt.symbiosis.sym_persistence.structure.ResponseObject;

import java.util.Date;

import static net.blaklizt.symbiosis.sym_persistence.dao.super_class.GenericDao.findById;
import static net.blaklizt.symbiosis.sym_persistence.dao.super_class.SymbiosisUserHelper.findByUserId;

public class SymbiosisLogHelper {

	static class LogRunner implements Runnable {

		final symbiosis_event_log symbiosis_event;

		public LogRunner(final symbiosis_event_log symbiosis_event) {
			this.symbiosis_event = symbiosis_event;
		}

		public void run() {
			//validate that all data is there
			if (symbiosis_event.getSystem() == null || symbiosis_event.getChannel() == null) {

				symbiosis_auth_user auth_user = null;

				if (symbiosis_event.getAuth_user_id() != null) {
					auth_user = findById(symbiosis_auth_user.class, symbiosis_event.getAuth_user_id());
				}
				else if (symbiosis_event.getSymbiosis_user_id() != null) {
					ResponseObject<symbiosis_auth_user> authUserResponse = findByUserId(
						symbiosis_event.getSymbiosis_user_id(), symbiosis_event.getSystem(),
						symbiosis_event.getChannel(), null);
					auth_user = authUserResponse.getResponseObject();
				}
				if (auth_user != null) {
					symbiosis_event.setSystem(auth_user.getAccess_system());
					symbiosis_event.setChannel(auth_user.getChannel());
				}
			}
			if (symbiosis_event.getEvent_date() == null) {
				symbiosis_event.setEvent_date(new Date());
			}

			symbiosis_event.save();
		}
	}

	public static void logSystemEvent(final symbiosis_event_log symbiosis_event) {
		ThreadPoolManager.schedule(new LogRunner(symbiosis_event));
	}
}
