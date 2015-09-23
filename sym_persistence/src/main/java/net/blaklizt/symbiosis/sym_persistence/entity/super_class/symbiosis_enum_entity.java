package net.blaklizt.symbiosis.sym_persistence.entity.super_class;

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
    private Boolean enabled;

    @Basic
    @Column(name = "description", nullable = false)
    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    @Basic
    @Column(name = "enabled", nullable = false)
    public Boolean isEnabled() { return enabled; }

    public void setEnabled(Boolean enabled) { this.enabled = enabled; }
}
