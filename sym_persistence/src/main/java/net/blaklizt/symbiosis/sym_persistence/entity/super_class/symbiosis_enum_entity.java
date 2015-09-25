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

    public symbiosis_enum_entity setValues(String description, Boolean enabled) {
        this.description = description;
        this.enabled = enabled;
        return this;
    }

    @Basic
    @Column(name = "description", nullable = false)
    public String getDescription() { updateMutex.waitForLock(); return description; }

    public void setDescription(String description) {
        updateMutex.acquireLock();      //make sure no cache updates are running during update
        this.description = description;
        notifyObservers(updateMutex);   //once cache update is complete, observer will release the lock
    }

    @Basic
    @Column(name = "enabled", nullable = false)
    public Boolean isEnabled() { updateMutex.waitForLock(); return enabled; }

    public void setEnabled(Boolean enabled) {
        updateMutex.acquireLock();      //make sure no cache updates are running during update
        this.enabled = enabled;
        notifyObservers(updateMutex);   //once cache update is complete, observer will release the lock
    }
}
