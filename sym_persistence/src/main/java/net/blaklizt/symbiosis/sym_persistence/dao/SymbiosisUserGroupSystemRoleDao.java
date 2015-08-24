package net.blaklizt.symbiosis.sym_persistence.dao;

import net.blaklizt.symbiosis.sym_persistence.SymbiosisUserGroupSystemRole;

import java.util.List;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */
public interface SymbiosisUserGroupSystemRoleDao {
    List<SymbiosisUserGroupSystemRole> findByUserGroup(String userGroup);
}
