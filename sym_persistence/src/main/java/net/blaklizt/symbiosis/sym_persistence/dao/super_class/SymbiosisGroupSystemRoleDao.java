package net.blaklizt.symbiosis.sym_persistence.dao.super_class;

import net.blaklizt.symbiosis.sym_persistence.entity.config.symbiosis_group_system_role;

import java.util.List;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */
public interface SymbiosisGroupSystemRoleDao extends SymbiosisEnumEntityDao {
    List<symbiosis_group_system_role> findByUserGroup(Long userGroupId);
    List<symbiosis_group_system_role> findBySystem(Long systemId);
    List<symbiosis_group_system_role> findByRole(Long roleId);
    List<symbiosis_group_system_role> findByGroupSystemRole(Long userGroupId, Long systemId, Long roleId);
}
