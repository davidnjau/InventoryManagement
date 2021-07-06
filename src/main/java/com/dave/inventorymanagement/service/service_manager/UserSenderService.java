package com.dave.inventorymanagement.service.service_manager;

import com.dave.inventorymanagement.entity.user_management.UserSender;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserSenderService {

    List<UserSender> findAllUsers();
    UserSender findUserByUsername(String username);
    Map<String, Object> saveUser(UserSender user);
    UserSender findUserByUserId(String id);

}
