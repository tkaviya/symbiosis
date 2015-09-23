package net.blaklizt.symbiosis.sym_persistence.helper;

import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import net.blaklizt.symbiosis.sym_persistence.dao.super_class.SymbiosisDaoInterface;
import net.blaklizt.symbiosis.sym_persistence.dao.super_class.SymbiosisEnumEntityDao;
import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_enum_entity;
import org.hibernate.criterion.Restrictions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    //maintain a list of all initialized DB handlers so we don't have more than 1 instance
    private static HashMap<String, SymbiosisDBEnumHelper> registeredHelpers = new HashMap<>();

    //always map 1 SymbiosisEnumEntityDao to 1 instance of this and return mapped instance always
    public static SymbiosisDBEnumHelper getSymbiosisDBEnumHelper(SymbiosisEnumEntityDao entityDao) {
        if (registeredHelpers.get(entityDao.getClass().getSimpleName()) == null) {
            registeredHelpers.put(entityDao.getClass().getSimpleName(), new SymbiosisDBEnumHelper(entityDao));
        }
        return registeredHelpers.get(entityDao.getClass().getSimpleName());
    }


    //Dao used to get data
    private static final Logger logger = Configuration.getNewLogger(SymbiosisDBEnumHelper.class.getSimpleName());

    SymbiosisDaoInterface entityDao;

    //Cache all enum values
    private final Map<String, Long> valueMap = new HashMap<>();

    private SymbiosisDBEnumHelper(SymbiosisEnumEntityDao entityDao) {

        this.entityDao = entityDao;

        List enumValues = entityDao.findEnabled();

        logger.info(format("Found %d enum values", enumValues.size()));

        for (Object value : enumValues) {
            symbiosis_enum_entity symbiosis_enum_entity = (symbiosis_enum_entity)value;
            valueMap.put(symbiosis_enum_entity.getDescription(), symbiosis_enum_entity.getId());
            logger.info(format("Mapped %s -> %d ", symbiosis_enum_entity.getDescription(), valueMap.get(symbiosis_enum_entity.getDescription())));
        }
    }

    private Object mapValue(String enumValue) {

        if (valueMap.get(enumValue) != null) return valueMap.get(enumValue);

        //if we have to check the database, either the value is wrong, not enabled or new (else constructor should have loaded it)
        logger.warning(format("Performing DB lookup of enum value %s", enumValue));

        List result = entityDao.findByCriterion(Restrictions.eq("description", enumValue));
        if (result == null || result.size() != 1) return null;
        return result.get(0);
    }

    public Long getMappedID(Enum enumValue) {
        return valueMap.get(enumValue.name());
    }
}
