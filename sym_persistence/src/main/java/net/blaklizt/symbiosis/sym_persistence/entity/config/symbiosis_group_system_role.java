package net.blaklizt.symbiosis.sym_persistence.entity.config;

import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_enum_entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created with Intelli_j IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
@Entity
public class symbiosis_group_system_role extends symbiosis_enum_entity {

    private Long symbiosis_group_id;
    private Long symbiosis_system_id;
	private Long symbiosis_role_id;

    @Basic
    @Column(name = "symbiosis_group_id", nullable = false)
    public Long get_symbiosis_group_id() {
        return symbiosis_group_id;
    }

    public void set_symbiosis_group_id(Long symbiosis_group_id) { this.symbiosis_group_id = symbiosis_group_id; }

    @Basic
    @Column(name = "symbiosis_system_id", nullable = false)
    public Long get_symbiosis_system_id() {
        return symbiosis_system_id;
    }

    public void set_symbiosis_system_id(Long symbiosis_system_id) {
        this.symbiosis_system_id = symbiosis_system_id;
    }

	@Basic
	@Column(name = "symbiosis_role_id", nullable = false)
	public Long get_symbiosis_role_id() {
		return symbiosis_role_id;
	}

	public void set_symbiosis_role_id(Long symbiosis_role_id) {
		this.symbiosis_role_id = symbiosis_role_id;
	}
}
