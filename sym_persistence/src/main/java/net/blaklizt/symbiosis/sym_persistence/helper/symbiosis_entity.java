package net.blaklizt.symbiosis.sym_persistence.helper;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;

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
public abstract class symbiosis_entity implements Serializable
{
    Long id;

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public Long get_id() { return id; }

    public void set_id(Long id) { this.id = id; }

    @Override
    public int hashCode() {
        return get_id() != null ? get_id().hashCode() : 0;
    }

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

        return !(get_id() != null ? !get_id().equals(that.get_id()) : that.get_id() != null);
    }
}
