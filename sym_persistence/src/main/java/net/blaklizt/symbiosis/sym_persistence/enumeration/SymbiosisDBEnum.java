package net.blaklizt.symbiosis.sym_persistence.enumeration;

import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import net.blaklizt.symbiosis.sym_persistence.dao.AbstractDao;
import net.blaklizt.symbiosis.sym_persistence.helper.SymbiosisEnumEntity;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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

@Component
public class SymbiosisDBEnum
        <SymbiosisEntity extends SymbiosisEnumEntity,
         PrimaryKey extends Serializable,
         Dao extends AbstractDao<SymbiosisEntity, PrimaryKey>> {

    //Dao used to get data
//    @Autowired
    private Dao entityDao;

    private static Map<String, ClassMetadata> classMetadata = null;
    
    private static final Logger logger = Configuration.getNewLogger(SymbiosisDBEnum.class.getSimpleName());

    //entity information
//    private final String className;
//    private final String identifierPropertyName;


    //Cache all enum values
    private final Map<String, PrimaryKey> valueMap = new HashMap<>();

    public void SymbiosisDBEnum() {

        //populate all metadata from the DB once and for all
        if (classMetadata == null) {
            classMetadata = entityDao.getCurrentSession().getSessionFactory().getAllClassMetadata();
        }

//        identifierPropertyName = classMetadata.get(className).getIdentifierPropertyName();
//
//        logger.info(format("Loading enum values for %s. Id property is %s", className, identifierPropertyName));
        
        List<SymbiosisEntity> enumValues = entityDao.findAll();

        logger.info(format("Found %d enum values", enumValues.size()));
        
        for (SymbiosisEntity value : enumValues) {
            valueMap.put(value.getDescription(), mapValue(value.getDescription()));
            logger.info(format("Mapped %s -> %d ", value.getDescription(), valueMap.get(value.getDescription())));
        }
    }
    
    private PrimaryKey mapValue(String enumValue) {

        if (valueMap.get(enumValue) != null) return valueMap.get(enumValue);

        List result = entityDao.findByCriterion(Restrictions.eq("Description", enumValue));
        if (result == null || result.size() != 1) return null;
        return (PrimaryKey)result.get(0);
    }

    public Long getMappedID(Enum enumValue) {
        return (Long)valueMap.get(enumValue.name());
    }
}
