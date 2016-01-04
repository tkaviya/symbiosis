package net.blaklizt.symbiosis.sym_persistence.helper;

import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_enum_entity;

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


public interface SymbiosisEnumType<E extends symbiosis_enum_entity> {

    Long value();

    Class<? extends symbiosis_enum_entity> getEnumEntityClass();
}
