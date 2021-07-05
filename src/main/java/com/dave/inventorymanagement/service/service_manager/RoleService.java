package com.dave.inventorymanagement.service.service_manager;


import com.dave.inventorymanagement.entity.user_management.Role;

public interface RoleService {
    Role findByName(String name);
    Role saveRoles(Role roleDto);
    Boolean existsByRoleName(String roleName);

}
