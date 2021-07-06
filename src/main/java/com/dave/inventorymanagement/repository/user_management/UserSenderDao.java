package com.dave.inventorymanagement.repository.user_management;

import com.dave.inventorymanagement.entity.user_management.UserSender;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSenderDao extends CrudRepository<UserSender, Long> {

    UserSender findUserSenderByUsername(String username);
    Boolean existsByUsername(String username);




}
