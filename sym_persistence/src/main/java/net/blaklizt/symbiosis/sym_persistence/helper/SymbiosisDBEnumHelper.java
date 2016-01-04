package net.blaklizt.symbiosis.sym_persistence.helper;

import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import net.blaklizt.symbiosis.sym_persistence.dao.super_class.SymbiosisEnumEntityDao;
import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_enum_entity;
import org.hibernate.criterion.Restrictions;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.String.format;

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

public class SymbiosisDBEnumHelper {

    private static final Logger logger = Configuration.getNewLogger(SymbiosisDBEnumHelper.class.getSimpleName());

    //maintain a list of all initialized DB handlers so we don't have more than 1 instance
    private static HashMap<String, SymbiosisDBEnumHelper> registeredHelpers = new HashMap<>();
    
    //maintain a list of class to DAO mappings for data retrieval
    private static HashMap<String, SymbiosisEnumEntityDao> registeredDaos = new HashMap<>();

    //maintain reference to single instance
    private static SymbiosisDBEnumHelper symbiosisDBEnumHelper = new SymbiosisDBEnumHelper();

    //cache for all enum values
    private static final HashMap<String, HashMap<String, Long>> valueMap = new HashMap<>();

    //populate cache
    public static SymbiosisDBEnumHelper getDaoHelper(SymbiosisEnumEntityDao entityDao) {
        populateCache(entityDao);
        return symbiosisDBEnumHelper;
    }

    private SymbiosisDBEnumHelper() {}

    private static void populateCache(SymbiosisEnumEntityDao entityDao) {

        List enumValues = entityDao.findEnabled();

        logger.warning(format("Found %d enum values", enumValues.size()));

        for (Object value : enumValues) {
            symbiosis_enum_entity enum_entity = (symbiosis_enum_entity)value;
            String className = enum_entity.getClass().getSimpleName();

            //register the dao we will use to retrieve the value
            registeredDaos.put(className, entityDao);

            //cache the value
            HashMap<String, Long> mappedValue = new HashMap<>();
            mappedValue.put(enum_entity.getDescription(), enum_entity.getId());
            valueMap.put(className, mappedValue);

            logger.warning(format("Mapped %s -> %d for enum %s", enum_entity.getDescription(),
                    valueMap.get(className).get(enum_entity.getDescription()), enum_entity.getClass()));
        }

    }

    public <E extends SymbiosisEnumType> Long getMappedID(E symbiosisEnumType) {

        String className = symbiosisEnumType.getEnumEntityClass().getSimpleName();
        String enumName = symbiosisEnumType.toString();

        //try to get cached value
        if (valueMap.get(className) != null && valueMap.get(className).get(enumName) != null)
            return valueMap.get(className).get(enumName);

        //value is not in cache, do a db hit
        logger.warning(format("Performing DB lookup of enum value %s on entity %s", enumName, className));

        List result = registeredDaos.get(className).findByCriterion(Restrictions.eq("description", enumName));

        if (result == null || result.size() != 1) return null;

        //value found, cache in memory
        HashMap<String, Long> mappedValue = new HashMap<>();
        mappedValue.put(enumName, ((symbiosis_enum_entity)result.get(0)).getId());
        valueMap.put(className, mappedValue);
        logger.warning(format("Mapped %s -> %d for enum %s", enumName, valueMap.get(className).get(enumName), className));

        return valueMap.get(className).get(enumName);
    }
}
