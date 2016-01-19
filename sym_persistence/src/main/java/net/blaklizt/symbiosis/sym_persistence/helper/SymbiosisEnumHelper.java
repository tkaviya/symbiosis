package net.blaklizt.symbiosis.sym_persistence.helper;

import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_enum_entity;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.String.format;
import static net.blaklizt.symbiosis.sym_persistence.dao.super_class.GenericDao.findAll;

/**
 * ***************************************************************************
 * *
 * Created:     14 / 09 / 2015                                             *
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

public class SymbiosisEnumHelper {

    private static final Logger logger = Configuration.getNewLogger(SymbiosisEnumHelper.class.getSimpleName());

    //cache for all enum values
    private static final HashMap<Class<? extends symbiosis_enum_entity>, HashMap<String, Long>> valueMap = new HashMap<>();

	private static final HashMap<Class<? extends symbiosis_enum_entity>, HashMap<Long, symbiosis_enum_entity>> entityMap = new HashMap<>();

    private static void populateCache(final Class<? extends symbiosis_enum_entity> enumEntityClass) {

        List enumValues = findAll(enumEntityClass);

        logger.info(format("Found %d values for enumerated type %s", enumValues.size(), enumEntityClass.getSimpleName()));

		if (valueMap.get(enumEntityClass) == null) {
			valueMap.put(enumEntityClass, new HashMap<>());
			entityMap.put(enumEntityClass, new HashMap<>());
		}
		HashMap<String, Long> valueCache = valueMap.get(enumEntityClass);

		HashMap<Long, symbiosis_enum_entity> entityCache = entityMap.get(enumEntityClass);

        for (Object enumEntity : enumValues) {
			symbiosis_enum_entity enumValue = (symbiosis_enum_entity)enumEntity;
			//cache the enum value
			valueCache.put(enumValue.getDescription(), enumValue.getId());
			entityCache.put(enumValue.getId(), enumValue);
            logger.info(format("Mapped %s -> %d for enum %s", enumValue.getDescription(), enumValue.getId(), enumEntityClass));
		}
	}

	public static Long getID(final Class<? extends symbiosis_enum_entity> enumEntityClass, final String enumValue) {
		HashMap<String, Long> valueCache = valueMap.get(enumEntityClass);
		if (valueCache == null) {
			//value is not yet cached
			populateCache(enumEntityClass);
		} else if (valueCache.get(enumValue) == null) {
			//cache exists, but is incomplete/invalid
			valueCache.clear();
			populateCache(enumEntityClass);
		}
		return valueMap.get(enumEntityClass).get(enumValue);
    }

	public static symbiosis_enum_entity getEnumEntity(Class<? extends symbiosis_enum_entity> enumEntityClass, final Long enumId) {
		HashMap<Long, symbiosis_enum_entity> entityCache = entityMap.get(enumEntityClass);
		if (entityCache == null) {
			//value is not yet cached
			populateCache(enumEntityClass);
		} else if (entityCache.get(enumId) == null) {
			//cache exists, but is incomplete/invalid
			entityCache.clear();
			populateCache(enumEntityClass);
		}
		return entityMap.get(enumEntityClass).get(enumId);
    }
}
