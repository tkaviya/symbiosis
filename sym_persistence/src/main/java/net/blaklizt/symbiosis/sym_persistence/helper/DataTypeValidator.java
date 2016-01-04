package net.blaklizt.symbiosis.sym_persistence.helper;

import static net.blaklizt.symbiosis.sym_common.configuration.SymbiosisUtilities.sendEmailAlert;
import static net.blaklizt.symbiosis.sym_persistence.helper.SymbiosisEnumHelper.getID;

/**
 * Created by photon on 2016/01/01.
 */
public class DataTypeValidator {

	public static boolean isValidEnumeratedType(SymbiosisEnumType enumeratedType) {

		if (enumeratedType == null) {
			sendEmailAlert(DataTypeValidator.class.getSimpleName(),
				"Enumerated value cannot be null!",
				"Enumerated value cannot be null!");
			return false;
		}
		else if (getID(enumeratedType.getEnumEntityClass(), enumeratedType.toString()) == null) {
			sendEmailAlert(DataTypeValidator.class.getSimpleName(),
				"Invalid Enumerated Type " + enumeratedType.getClass().getSimpleName(),
				"Enumerated Type " + enumeratedType.getClass().getSimpleName() + " is not mapped in the database");
			return false;
		}
		else if (getID(enumeratedType.getEnumEntityClass(), enumeratedType.toString()) == null) {
			sendEmailAlert(DataTypeValidator.class.getSimpleName(),
				"Invalid Enumerated Type " + enumeratedType.getClass().getSimpleName(),
				"Enumerated Type " + enumeratedType.getClass().getSimpleName() + " is not mapped in the database");
			return false;
		}
		return true;
	}


}
