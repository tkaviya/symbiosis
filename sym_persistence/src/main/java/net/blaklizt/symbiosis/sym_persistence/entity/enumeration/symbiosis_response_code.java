package net.blaklizt.symbiosis.sym_persistence.entity.enumeration;

import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_enum_entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
@Entity
public class symbiosis_response_code extends symbiosis_enum_entity {
    private Long symbiosis_system_id;

    @Column(name = "symbiosis_system_id", nullable = false)
    public Long get_symbiosis_system_id() {
        return symbiosis_system_id;
    }

    public void set_symbiosis_system_id(Long symbiosis_system_id) {
        this.symbiosis_system_id = symbiosis_system_id;
    }

}
