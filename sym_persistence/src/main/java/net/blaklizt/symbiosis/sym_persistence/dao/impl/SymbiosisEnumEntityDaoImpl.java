package net.blaklizt.symbiosis.sym_persistence.dao.impl;

import net.blaklizt.symbiosis.sym_persistence.dao.SymbiosisEnumEntityDao;
import net.blaklizt.symbiosis.sym_persistence.enumeration.symbiosis_option;
import net.blaklizt.symbiosis.sym_persistence.helper.AbstractDao;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;

/**
 * ***************************************************************************
 * *
 * Created:     19 / 09 / 2015                                             *
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


public abstract class SymbiosisEnumEntityDaoImpl<E, I extends Serializable> extends AbstractDao<E, I> implements SymbiosisEnumEntityDao {

    protected SymbiosisEnumEntityDaoImpl(Class<E> entityClass) { super(entityClass); }

    @Override
    @SuppressWarnings("unchecked")
    public List<symbiosis_option> findEnabled() {
        return findByCriteria(Restrictions.eq("enabled", true));
    }

    @Override
    public symbiosis_option findEnabledByDescription(String description) {
        List result = findByCriteria(
            Restrictions.eq("enabled", true),
            Restrictions.eq("description", description));
        if (result == null || result.size() != 1) return null;
        return (symbiosis_option)result.get(0);
    }

    @Override
    public symbiosis_option findByDescription(String description) {
        List result = findByCriterion(Restrictions.eq("description", description));
        if (result == null || result.size() != 1) return null;
        return (symbiosis_option)result.get(0);
    }
}
