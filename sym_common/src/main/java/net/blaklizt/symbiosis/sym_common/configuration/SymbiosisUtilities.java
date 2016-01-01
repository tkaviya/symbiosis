package net.blaklizt.symbiosis.sym_common.configuration;

import net.blaklizt.symbiosis.sym_common.mail.EMailer;

import static net.blaklizt.symbiosis.sym_common.configuration.Configuration.getProperties;
import static net.blaklizt.symbiosis.sym_common.configuration.ThreadPoolManager.schedule;

/**
 * Created by photon on 2016/01/01.
 */
public class SymbiosisUtilities {

	public static void sendEmailAlert(String symbiosisSystem, String alertSubject, String alertMessage) {
		schedule(new EMailer(getProperties("AlertEmail"), symbiosisSystem + " alert! " + alertSubject, alertMessage));
	}
}
