package com.dave.inventorymanagement.service.service_manager;

import com.dave.inventorymanagement.entity.user_management.UserSender;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserSenderService {

    List<UserSender> findAllUsers();
    UserSender findUserByUsername(String username);
    ResponseEntity<?> saveUser(UserSender user);
    UserSender findUserByUserId(String id);

}
