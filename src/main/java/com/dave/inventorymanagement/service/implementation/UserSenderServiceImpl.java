package com.dave.inventorymanagement.service.implementation;


import com.dave.inventorymanagement.entity.user_management.Role;
import com.dave.inventorymanagement.entity.user_management.UserSender;
import com.dave.inventorymanagement.repository.user_management.UserSenderDao;
import com.dave.inventorymanagement.service.service_manager.RoleService;
import com.dave.inventorymanagement.service.service_manager.UserSenderService;
import com.example.smsmanager.responses.UsersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value = "userSenderService")
public class UserSenderServiceImpl implements UserDetailsService, UserSenderService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserSenderDao userSenderDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserSender user = userSenderDao.findUserSenderByUsername(userName);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return new User(user.getusername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserSender user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    public List<UserSender> findAllUsers(){
        List<UserSender> userSenderList = new ArrayList<>();
        userSenderDao.findAll().iterator().forEachRemaining(userSenderList::add);
        return userSenderList;
    }

    @Override
    public UserSender findUserByUsername(String username) {
        return userSenderDao.findUserSenderByUsername(username);
    }

    @Override
    public Map<String, Object> saveUser(UserSender user) {

        Map<String, Object> stringMap = new HashMap<>();

        boolean isUserUsername = userSenderDao.existsByUsername(user.getusername());
        String message = "";
        if (!isUserUsername){


            //Add user
            user.setPassword(bcryptEncoder.encode(user.getPassword()));

            Role role = roleService.findByName("USER");
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(role);

            if(user.getEmail_address().split("@")[1].equals("admin.edu")){
                role = roleService.findByName("ADMIN");
                roleSet.add(role);
            }

            user.setRoles(roleSet);
            userSenderDao.save(user);
            message = "User created successfully.";

            //            return new ResponseEntity<>(new UsersDetails(message), HttpStatus.CREATED);

        }else {
            message = "User exists with that username. Please use another username.";

//            return new ResponseEntity<>(new UsersDetails(message), HttpStatus.BAD_REQUEST);
        }
        stringMap.put("details", message);

        return stringMap;


    }

    @Override
    public UserSender findUserByUserId(String id) {
        return null;
    }

}
