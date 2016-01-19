//package net.blaklizt.symbiosis.sym_persistence.dao.super_class;
//
//import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_option;
//import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_entity;
//import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_enum_entity;
//import org.hibernate.criterion.Restrictions;
//
//import java.io.Serializable;
//import java.util.List;
//
//import static net.blaklizt.symbiosis.sym_persistence.dao.super_class.AbstractDao.using;
//
///******************************************************************************
// * *
// * Created:     28 / 12 / 2015                                             *
// * Platform:    Red Hat Linux 9                                            *
// * Author:      Tich de Blak (Tsungai Kaviya)                              *
// * Copyright:   Blaklizt Entertainment                                     *
// * Website:     http://www.blaklizt.net                                    *
// * Contact:     blaklizt@gmail.com                                         *
// * *
// * This program is free software; you can redistribute it and/or modify    *
// * it under the terms of the GNU General Public License as published by    *
// * the Free Software Foundation; either version 2 of the License, or       *
// * (at your option) any later version.                                     *
// * *
// * This program is distributed in the hope that it will be useful,         *
// * but WITHOUT ANY WARRANTY; without even the implied warranty of          *
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.    See the         *
// * GNU General Public License for more details.                            *
// * *
// ******************************************************************************/
//
//
//public interface SymbiosisEnumDao<E extends symbiosis_enum_entity, I extends Serializable> {
//
//    @SuppressWarnings("unchecked")
//    default List<E> findEnabled(Class<E> type) {
//        return using(type).findByCriteria(Restrictions.eq("enabled", true));
//    }
//
//    default E findEnabledByDescription(Class<E> type, String description) {
//        List result = using(type).findByCriteria(
//                Restrictions.eq("enabled", true),
//                Restrictions.eq("description", description));
//        if (result == null || result.size() != 1) return null;
//        return (E)result.get(0);
//    }
//
//    default E findByDescription(Class<E> type, String description) {
//        List result = using(type).findByCriterion(Restrictions.eq("description", description));
//        if (result == null || result.size() != 1) return null;
//        return (E)result.get(0);
//    }
//}
