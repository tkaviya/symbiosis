package net.blaklizt.symbiosis.sym_persistence.dao.impl;

import net.blaklizt.symbiosis.sym_persistence.SymbiosisUserOption;
import net.blaklizt.symbiosis.sym_persistence.dao.AbstractDao;
import net.blaklizt.symbiosis.sym_persistence.dao.SymbiosisUserOptionDao;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tkaviya on 9/13/2015.
 */
@Repository
public class SymbiosisUserOptionDaoImpl extends AbstractDao<SymbiosisUserOption, Long> implements SymbiosisUserOptionDao {

    protected SymbiosisUserOptionDaoImpl() { super(SymbiosisUserOption.class); }

    public SymbiosisUserOption findByUserIDAndOption(Long symbiosisUserID, Long symbiosisOptionID) {

        Criterion criterion1 = Restrictions.eq("SymbiosisUserID", symbiosisUserID);
        Criterion criterion2 = Restrictions.eq("SymbiosisOptionID", symbiosisOptionID);
        LogicalExpression userIDExpression = Restrictions.and(criterion1, criterion2);

        List result = findByCriterion(userIDExpression);
        if (result == null || result.size() != 1) return null;
        return (SymbiosisUserOption)result.get(0);
    }
}
