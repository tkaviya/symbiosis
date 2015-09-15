package net.blaklizt.symbiosis.sym_persistence.dao;

import net.blaklizt.symbiosis.sym_persistence.SymbiosisUserGroupSystemRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */
@Repository
public interface SymbiosisUserGroupSystemRoleDao {
    List<SymbiosisUserGroupSystemRole> findByUserGroup(String userGroup);
}
