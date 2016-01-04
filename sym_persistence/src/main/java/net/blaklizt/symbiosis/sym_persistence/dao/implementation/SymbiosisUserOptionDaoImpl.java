//package net.blaklizt.symbiosis.sym_persistence.dao.implementation;
//
//import net.blaklizt.symbiosis.sym_persistence.dao.super_class.AbstractDao;
//import net.blaklizt.symbiosis.sym_persistence.dao.super_class.SymbiosisUserOptionDao;
//import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_user_option;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
///**
// * Created by tkaviya on 9/13/2015.
// */
//@Repository
//public class SymbiosisUserOptionDaoImpl extends AbstractDao<symbiosis_user_option, Long> implements SymbiosisUserOptionDao {
//
//    protected SymbiosisUserOptionDaoImpl() { super(symbiosis_user_option.class); }
//
//    public symbiosis_user_option findByUserIDAndOption(Long symbiosisUserID, Long symbiosisOptionID) {
//        List result = findByCriteria(
//            Restrictions.eq("symbiosis_user_id", symbiosisUserID),
//            Restrictions.eq("symbiosis_option_id", symbiosisOptionID));
//        if (result == null || result.size() != 1) return null;
//        return (symbiosis_user_option)result.get(0);
//    }
//}
