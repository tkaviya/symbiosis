//package net.blaklizt.symbiosis.sym_persistence.dao.implementation;
//
//import net.blaklizt.symbiosis.sym_persistence.dao.super_class.AbstractDao;
//import net.blaklizt.symbiosis.sym_persistence.dao.super_class.SymbiosisEventLogDao;
//import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_event_log;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
///**
// * Created with IntelliJ IDEA.
// * SymbiosisUser: tkaviya
// */
//
//@Repository
//public class SymbiosisEventLogDaoImpl extends AbstractDao<symbiosis_event_log, Long> implements SymbiosisEventLogDao
//{
//	protected SymbiosisEventLogDaoImpl() { super(symbiosis_event_log.class); }
//
//    @SuppressWarnings("unchecked")
//	public List<symbiosis_event_log> findByUserID(Long userId) {
//        return findByCriterion(Restrictions.like("symbiosis_user_id", userId));
//    }
//
//    @SuppressWarnings("unchecked")
//    public List<symbiosis_event_log> findByUserAndEventTypeID(Long userId, Long eventTypeId) {
//        return findByCriteria(
//            Restrictions.like("symbiosis_user_id", userId),
//            Restrictions.like("symbiosis_event_type_id", eventTypeId)
//        );
//    }
//}
