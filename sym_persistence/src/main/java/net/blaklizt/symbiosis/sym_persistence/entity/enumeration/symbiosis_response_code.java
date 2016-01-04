package net.blaklizt.symbiosis.sym_persistence.entity.enumeration;

import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_enum_entity;

import javax.persistence.*;

import static javax.persistence.InheritanceType.SINGLE_TABLE;
import static net.blaklizt.symbiosis.sym_persistence.admin.SymbiosisConfig.SYMBIOSIS;
import static net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_response_code.RESPONSE_CODE_TYPE.SYSTEM;

/**
 * Created with IntelliJ IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
@Entity
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "response_code_type", discriminatorType = DiscriminatorType.STRING)
public class symbiosis_response_code extends symbiosis_enum_entity<symbiosis_response_code> {

	public enum RESPONSE_CODE_TYPE {
		SYSTEM, INPUT_VALIDATION, AUTHENTICATION, REGISTRATION, ACCOUNT_STATUS, CONNECTIVITY
	}

	@Column(nullable = false, insertable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	protected RESPONSE_CODE_TYPE response_code_type;
	protected String response_message;
	protected symbiosis_system system;

	public symbiosis_response_code(Number response_code, String description, String response_message,
								   Boolean enabled, symbiosis_system system, RESPONSE_CODE_TYPE response_code_type) {
		super(description, enabled);
		this.id = response_code.longValue();
		this.response_message = response_message;
		this.system = system;
		this.response_code_type = response_code_type;
	}

	public symbiosis_response_code(Number response_code, String description, String response_message,
								   Boolean enabled, symbiosis_system system) {
		this(response_code, description, response_message, enabled, system, SYSTEM);
	}

	public symbiosis_response_code(Number response_code, String description, String response_message) {
		this(response_code, description, response_message, true, SYMBIOSIS, SYSTEM);
	}

	public symbiosis_response_code(Number response_code, String description, String response_message,
								   RESPONSE_CODE_TYPE response_code_type) {
		this(response_code, description, response_message, true, SYMBIOSIS, response_code_type);
	}

	@Column(nullable = false)
    public symbiosis_system getSystem() {
        return system;
    }

    public void setSystem(symbiosis_system system) {
        this.system = system;
    }

	@Column(nullable = false)
    public String getResponse_message() {
        return response_message;
    }

    public void setResponse_message(String response_message) {
        this.response_message = response_message;
    }

}
