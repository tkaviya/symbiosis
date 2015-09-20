package net.blaklizt.symbiosis.sym_persistence.helper;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created with IntelliJ IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
@MappedSuperclass
public abstract class symbiosis_enum_entity extends symbiosis_entity {

    private String description;
    private Byte enabled;

    @Basic
    @Column(name = "description", nullable = true)
    public String get_description() { return description; }

    public void set_description(String description) { this.description = description; }

    @Basic
    @Column(name = "enabled", nullable = false)
    public Byte get_enabled() { return enabled; }

    public void set_enabled(Byte enabled) { this.enabled = enabled; }
}
