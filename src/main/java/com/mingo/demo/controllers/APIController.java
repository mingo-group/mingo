package com.mingo.demo.controllers;

import com.mingo.demo.daos.*;
import com.mingo.demo.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@Controller
public class APIController {


    @Autowired
    private UserRepository userDao;
    @Autowired
    private BusinessRepository businessDao;
    @Autowired
    private MessageRepository messageDao;
    @Autowired
    private CategoryRepository categoryDao;
    @Autowired
    private InterestRepository interestDao;

    @PostMapping("/user-add")
    public @ResponseBody ResponseEntity<String> userAdd(@RequestParam(value="username", required = false) String username,
                                                        @RequestParam(value="email", required = false) String email,
                                                        @RequestParam(value="password", required = false) String password,
                                                        @RequestParam(value="avatarPath", required = false) String avatarPath,
                                                        @RequestParam(value="latitude", required = false) Double latitude,
                                                        @RequestParam(value="longitude", required = false) Double longitude,
                                                        @RequestParam(value="interests", required = false) List<Long> interests) {
        User user = new User();
        if (username != null) {user.setUsername(username);}
        if(email != null) {user.setEmail(email);}
        if (password != null) {user.setPassword(password);}
        if (avatarPath != null) {user.setAvatar_path(avatarPath);}
        if (latitude != null) {user.setLatitude(latitude);}
        if (longitude != null) {user.setLongitude(longitude);}
//        if (interests != null) {
//            List<Interest> interestsObjects = new ArrayList<>();
//            for (int i=0; i<interests.size(); i++) {
//            interestsObjects.add(interestDao.findById(interests.get(i)));
//            }
//        }
        userDao.save(user);
        return new ResponseEntity<String> ("New user created with id: " + user.getUsername(), HttpStatus.OK);
    }

    @PostMapping("/user-interest")
    public ResponseEntity<String> interestAddToUser(@RequestParam(value="user") long userid,
                                    @RequestParam(value="interest") List<Long> interestsid) {
        User user = userDao.getById(userid);
        List<Interest> interests = user.getInterests();
        for (int i = 0; i < interestsid.size(); i++) {
            interests.add(interestDao.getById(interestsid.get(i)));
        }
        user.setInterests(interests);
        userDao.save(user);
        return new ResponseEntity<String> ("User " + user.getUsername() + " modified.", HttpStatus.OK);
    }

    @PostMapping("/business-interest")
    public ResponseEntity<String> interestAddToBusiness(@RequestParam(value="business") long businessid, @RequestParam(value="interest") List<Long> interestsid) {
        Business business = businessDao.getById(businessid);
        List<Interest> interests = business.getInterests();
        for (int i = 0; i < interestsid.size(); i++) {
            interests.add(interestDao.getById(interestsid.get(i)));
        }
        business.setInterests(interests);
        businessDao.save(business);
        return new ResponseEntity<String> ("Business " + business.getName() + " modified.", HttpStatus.OK);
    }

    @PostMapping("/message-api")
    public String messageSend(@ModelAttribute Message message,
                              @RequestParam(value="sender") long sender,
                              @RequestParam(value="receiver") long receiver) {
        message.setSender(userDao.getById(sender));
        message.setReceiver(userDao.getById(receiver));
        message.setSent(Timestamp.from(Instant.now()));
        message.setStatus(MessageStatus.DELIVERED);
        messageDao.save(message);
        return "redirect:/test";
    }

}

//curl -d 'username=fooforreal&email=fooforreal@foo.com' http://localhost:8080/user-add
//curl -d 'username=fooforreal1&email=fooforreal1@food.com&password=password' http://localhost:8080/user-add
//curl -d 'username=fooforreal2&na' http://localhost:8080/user-add
//curl -d 'username=fooforreal3&na' http://localhost:8080/user-add
//curl -d 'username=fooforreal4&na' http://localhost:8080/user-add
//curl -d 'business=2&interest=8' http://localhost:8080/business-interest