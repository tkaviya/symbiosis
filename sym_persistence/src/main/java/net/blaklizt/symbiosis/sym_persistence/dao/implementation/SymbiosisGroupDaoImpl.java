package net.blaklizt.symbiosis.sym_persistence.dao.implementation;

import net.blaklizt.symbiosis.sym_persistence.dao.super_class.SymbiosisGroupDao;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_group;
import org.springframework.stereotype.Repository;

/**
 * ***************************************************************************
 * *
 * Created:     20 / 09 / 2015                                             *
 * Platform:    Red Hat Linux 9                                            *
 * Author:      Tich de Blak (Tsungai Kaviya)                              *
 * Copyright:   Blaklizt Entertainment                                     *
 * Website:     http://www.blaklizt.net                                    *
 * Contact:     blaklizt@gmail.com                                         *
 * *
 * This program is free software; you can redistribute it and/or modify    *
 * it under the terms of the GNU General Public License as published by    *
 * the Free Software Foundation; either version 2 of the License, or       *
 * (at your option) any later version.                                     *
 * *
 * This program is distributed in the hope that it will be useful,         *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of          *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.    See the         *
 * GNU General Public License for more details.                            *
 * *
 * ****************************************************************************
 */

@Repository
public class SymbiosisGroupDaoImpl extends SymbiosisEnumEntityDaoImpl<symbiosis_group, Long> implements SymbiosisGroupDao {
    protected SymbiosisGroupDaoImpl() { super(symbiosis_group.class); }
}