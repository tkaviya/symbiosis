package net.blaklizt.symbiosis.sym_persistence.entity.super_class;

import net.blaklizt.symbiosis.sym_persistence.dao.super_class.GenericDao;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import static net.blaklizt.symbiosis.sym_common.utilities.Validator.isNumeric;

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
public abstract class symbiosis_entity<E extends symbiosis_entity> implements Serializable
{
    protected Long id;

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() { return id; }

    /* this function is private because we do not want to be
     * able to update an entity id via the program ever */
    public void setId(Long id) { this.id = id; }

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			stringBuilder.append("{ ");
			for (Field field : this.getClass().getDeclaredFields()) {
				//field.setAccessible(true);
				if (!field.isAccessible()) {
					continue;
				}
				Object fieldValue = field.get(field);
				String fieldData;
				if (fieldValue == null)					{ fieldData = "[" + field.getName() + ":null]"; }
				else if (fieldValue instanceof String)	{ fieldData = String.valueOf(fieldValue); }
				else if (isNumeric(fieldValue))			{ fieldData = "" + fieldValue; }
				else									{ fieldData = "[" + field.getName() + ":not null]"; }
				stringBuilder.append(field.getName()).append("=\"").append(fieldData).append("\"\t");
			}
			stringBuilder.append("}");
		}
		catch (IllegalAccessException e) { e.printStackTrace(); }
		return stringBuilder.toString();
	}

	public Long save() {
		return GenericDao.save(this);
	}

	public E saveOrUpdate() {
		return (E)GenericDao.saveOrUpdate(this);
	}
}
