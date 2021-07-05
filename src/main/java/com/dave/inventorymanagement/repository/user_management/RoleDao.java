package com.dave.inventorymanagement.repository.user_management;

import com.dave.inventorymanagement.entity.user_management.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {
    Role findRoleByName(String name);
    boolean existsRoleByName(String name);

}
