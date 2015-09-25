package net.blaklizt.symbiosis.sym_persistence.entity.super_class;

import net.blaklizt.symbiosis.sym_common.utilities.MutexLock;
import sun.awt.Mutex;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Observable;

/* *************************************************************************
 * Created:     18 / 09 / 2015                                             *
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

@MappedSuperclass
public abstract class symbiosis_entity extends Observable implements Serializable
{
    protected MutexLock updateMutex = new MutexLock(-1L, 1000L);
    protected Long id;

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        updateMutex.waitForLock();  //make sure cache update not in progress
        return id; }

    public void setId(Long id) {
        updateMutex.acquireLock();      //make sure no cache updates are running during update
        this.id = id;
        notifyObservers(updateMutex);   //once cache update is complete, observer will release the lock
    }

    @Override
    public int hashCode() { return id != null ? id.hashCode() : 0; }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) { return true; }
        if (!(o instanceof symbiosis_entity)) { return false; }
        symbiosis_entity that = (symbiosis_entity) o;

        //use reflection to test all other fields
        Field[] fields = this.getClass().getFields();
        for (Field field : fields) {
            try {
                if (!this.getClass().getField(field.getName()).equals(
                     that.getClass().getField(field.getName())))
                    return false;
            } catch (NoSuchFieldException e) {
                return false;
            }
        }

        return !(id != null ? !id.equals(that.id) : that.id != null);
    }
}
