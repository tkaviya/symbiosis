package net.blaklizt.symbiosis.sym_persistence.entity.super_class;

/* *************************************************************************
 * Created:     2016/01/01                                                 *
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

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class symbiosis_complex_enum<G extends symbiosis_complex_enum> extends
	symbiosis_enum_entity<G> {

//	protected ArrayList<G> groupItems;
//	protected G group;

	public symbiosis_complex_enum(String description, Boolean enabled) {
		super(description, enabled);
	}

//	public symbiosis_complex_enum(String description, Boolean enabled,
//								  ArrayList<G> groupItems, G group) {
//		super(description, enabled);
//		this.groupItems = groupItems;
//		this.group = group;
//	}

//	@Basic(fetch = FetchType.LAZY, optional = false)
//	public G getGroup() {
//		return group;
//	}
//
//	protected void setGroup(G group) {
//		this.group = group;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY)
//	public ArrayList<G> getGroupItems() {
//		return groupItems;
//	}
//
//	public void setGroupItems(ArrayList<G> groupItems) {
//		this.groupItems = groupItems;
//	}

}
