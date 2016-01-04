package net.blaklizt.symbiosis.sym_persistence.entity.super_class;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
@MappedSuperclass
public abstract class symbiosis_enum_entity<E extends symbiosis_enum_entity>
	extends symbiosis_entity<E> {

    protected String description;
	protected Boolean enabled;

    public symbiosis_enum_entity (String description, Boolean enabled) {
        this.description = description;
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "description", nullable = false, updatable = false)
    public String getDescription() { return description; }

    /* this function is private because we do not want to be
     * able to update an enum entity to set description programmatically */
    private void setDescription(String description) { this.description = description; }

    @Basic
    @Column(name = "enabled", nullable = false, updatable = false)
    public Boolean isEnabled() { return enabled; }

    /* this function is private because we do not want to be
     * able to update an enum entity to set enabled flag programmatically */
    private void setEnabled(Boolean enabled) { this.enabled = enabled; }
}
