package com.dave.inventorymanagement.service.implementation;


import com.dave.inventorymanagement.entity.user_management.Role;
import com.dave.inventorymanagement.repository.user_management.RoleDao;
import com.dave.inventorymanagement.service.service_manager.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findByName(String name) {
        return roleDao.findRoleByName(name);
    }

    @Override
    public Role saveRoles(Role roleDto) {
        Role role = new Role();

        if (!existsByRoleName(roleDto.getName())){

            role.setName(roleDto.getName());
            role.setDescription(roleDto.getDescription());
            roleDao.save(role);
        }

        return new Role();
    }

    @Override
    public Boolean existsByRoleName(String roleName) {
        return roleDao.existsRoleByName(roleName);
    }


}
