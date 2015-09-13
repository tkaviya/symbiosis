package net.blaklizt.symbiosis.sym_persistence.dao.impl;

import net.blaklizt.symbiosis.sym_persistence.SymbiosisOption;
import net.blaklizt.symbiosis.sym_persistence.dao.AbstractDao;
import net.blaklizt.symbiosis.sym_persistence.dao.SymbiosisOptionDao;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tkaviya on 9/13/2015.
 */
@Repository
public class SymbiosisOptionDaoImpl extends AbstractDao<SymbiosisOption, Long> implements SymbiosisOptionDao {

    protected SymbiosisOptionDaoImpl() { super(SymbiosisOption.class); }

    @Override
    public SymbiosisOption findByDescription(String description) {
        List result = findByCriterion(Restrictions.eq("Description", description));
        if (result == null || result.size() != 1) return null;
        return (SymbiosisOption)result.get(0);
    }
}
