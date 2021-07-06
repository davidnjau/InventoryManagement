package com.dave.inventorymanagement.controller;

import com.dave.inventorymanagement.authentication.TokenProvider;
import com.dave.inventorymanagement.entity.user_management.UserSender;
import com.dave.inventorymanagement.helper_class.LoginUser;
import com.dave.inventorymanagement.service.service_manager.UserSenderService;
import com.example.smsmanager.responses.DetailsData;
import com.example.smsmanager.responses.LoginDetails;
import com.example.smsmanager.responses.LoginError;
import com.example.smsmanager.responses.UsersDetails;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UsersControllers {

    @Value("${jwt.token.validity}")
    public long TOKEN_VALIDITY;


    @Autowired
    private UserSenderService userSenderService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @RequestMapping(value = "/api/v1/auth/register", method = RequestMethod.POST)
    public Map<String, Object> saveUser(@RequestBody UserSender user){

        return userSenderService.saveUser(user);

    }

    @RequestMapping(value = "/api/v1/auth/login", method = RequestMethod.POST)
    public Map<String, Object> generateToken(@RequestBody LoginUser loginUser) {

        Map<String, Object> response = new HashMap<>();

        try{

            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUser.getUsername(),
                            loginUser.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenUtil.generateToken(authentication);

            String userId = String.valueOf(userSenderService.findUserByUsername(loginUser.getUsername()).getId());
            String username = loginUser.getUsername();
            String hashedAccessToken = DigestUtils.sha256Hex(userId + username);

            DetailsData detailsData = new DetailsData(hashedAccessToken, TOKEN_VALIDITY, "Bearer", token);

            response.put("details", detailsData);


        }catch (Exception e){
            System.out.println("-*-*-*-*"+e.getMessage());
            LoginError details = new LoginError("Wrong credentials. Please try again.");
            response.put("details", details);
        }

        return response;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/api/v1/users/adminping", method = RequestMethod.GET)
    public String adminPing(){
        return "Only Admins Can read this";
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/api/v1/users/userping", method = RequestMethod.GET)
    public String userPing(){
        return "Any User can read this";
    }



//    @PutMapping(value = "/api/v1/users/recipients/{id}")
//    public ResponseEntity<UserRecipients> updateRecipient(@PathVariable("id") Long id,
//                                                          @RequestBody UserRecipients userRecipients){
//
//        return userRecipientsDao.findById(id)
//                .map(userRecipientsOld -> {
//
//                    userRecipientsOld.setName(userRecipients.getName());
//                    userRecipientsOld.setEmailAddress(userRecipients.getEmailAddress());
//
//                    UserRecipients userRecipientsUpdated = userRecipientsDao.save(userRecipientsOld);
//                    return ResponseEntity.ok().body(userRecipientsUpdated);
//
//                }).orElse(ResponseEntity.notFound().build());
//
//    }

    /**
     * Web Controller
     */


//
//    @RequestMapping("/dashboard")
//    public String dashboard(){
//        return "dashboard";
//    }
//
//    @RequestMapping("/mysms")
//    public String mysms(){
//        return "mySms";
//    }
//
//    @RequestMapping("/user_details")
//    public String userDetails(){
//        return "all_users";
//    }
//
//    @RequestMapping("/profile")
//    public String myProfile(){
//        return "user";
//    }



    @RequestMapping(value = "/")
    public ModelAndView index() {
        return new ModelAndView("authentication/login");
    }

    @RequestMapping("/login")
    public ModelAndView myLogin(){

        return new ModelAndView("authentication/login");
    }

}
