package net.blaklizt.symbiosis.sym_persistence.dao;

import net.blaklizt.symbiosis.sym_persistence.SymbiosisRole;
import org.springframework.stereotype.Repository;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */
@Repository
public interface SymbiosisRoleDao {
    SymbiosisRole findEnabled();
}
