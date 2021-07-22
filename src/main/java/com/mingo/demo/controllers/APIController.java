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
// The API controller, for the most part, implements the Post methods that were already
// implemented on the Home controller, but does so RESTfully.

// Again, this implementation does NOT feature API keys, so anyone can add or modify
// entries, even without being 'in' the Mingo front-end, and this should be exactly
// as concerning from a security perspective as it sounds.

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
    @Autowired
    private OfferRepository offerDao;
    @Autowired
    private VisitRepository visitDao;

    @PostMapping("/user-add")
    public @ResponseBody ResponseEntity<String> userAdd(@RequestParam(value="id", required = false) Long userid,
                                                        @RequestParam(value="username", required = false) String username,
                                                        @RequestParam(value="email", required = false) String email,
                                                        @RequestParam(value="password", required = false) String password,
                                                        @RequestParam(value="avatarPath", required = false) String avatarPath,
                                                        @RequestParam(value="latitude", required = false) Double latitude,
                                                        @RequestParam(value="longitude", required = false) Double longitude,
                                                        @RequestParam(value="interests", required = false) List<Long> interests) {

        User user = new User();
        if (userid != null) {
            user.setId(userid);
        }
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

    @PostMapping("/business-add")
    public ResponseEntity<String> bussinessAdd(@RequestParam(value ="id", required = false) Long businessID,
                                               @RequestParam(value="name") String name,
                                               @RequestParam(value="description") String description,
                                               @RequestParam(value="latitude") Double latitude,
                                               @RequestParam(value="longitude") Double longitude) {
        Business business = new Business();
        if (businessID != null) {
            business.setId(businessID);
        }
        business.setName(name);
        business.setDescription(description);
        business.setLatitude(latitude);
        business.setLongitude(longitude);

        return new ResponseEntity<String> ("New business created with name: " + business.getName(), HttpStatus.OK);
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
    public ResponseEntity<String> messageSendAPI(@ModelAttribute Message message,
                              @RequestParam(value="sender") long sender,
                              @RequestParam(value="receiver") long receiver,
                              @RequestParam(value="body") String body) {
        message.setSender(userDao.getById(sender));
        message.setReceiver(userDao.getById(receiver));
        message.setSent(Timestamp.from(Instant.now()));
        message.setBody(body);
        message.setStatus(MessageStatus.DELIVERED);
        messageDao.save(message);
        return new ResponseEntity<String> ("Post created.", HttpStatus.OK);
    }

    @PostMapping("/offer-api")
    public ResponseEntity<String> offerAddAPI(
                                                 @RequestParam(value="details") String details,
                                                 @RequestParam(value="users") List<Long> userIDs,
                                                 @RequestParam(value="business") Long businessID) {
        Offer offer = new Offer();
        offer.setDetails(details);
        offer.setBusiness(businessDao.getById(businessID));
        List<User> users = new ArrayList<>();
        for (int i = 0; i < userIDs.size(); i++) {
            users.add(userDao.getById(userIDs.get(i)));
        }
        offer.setUsers(users);
        offerDao.save(offer);
        return new ResponseEntity<String> ("Offer created.", HttpStatus.OK);
    }

    @PostMapping("/visit-api")
    public ResponseEntity<String> visitAddAPI(
            @RequestParam(value="user") Long userID,
            @RequestParam(value="business") Long businessID,
            @RequestParam(value="offer", required = false) Long offerID,
            @RequestParam(value="start", required = false) Timestamp start,
            @RequestParam(value="end", required = false) Timestamp end
            ) {
        Visit visit = new Visit();
        visit.setBusiness(businessDao.getById(businessID));
        visit.setUser(userDao.getById(userID));
        if (start != null) {
            visit.setStart(start);
        }
        else {
            visit.setStart(Timestamp.from(Instant.now()));
        }
        if (end != null) {
            visit.setEnd(end);
        }
        if (offerID != null) {
            visit.setOffer(offerDao.getById(offerID));
        }
        visitDao.save(visit);

        return new ResponseEntity<String> ("Visit created; visit id =" + visit.getId(), HttpStatus.OK);
    }

    @PutMapping("/visitend-api")
    public ResponseEntity<String> visitEndSaveAPI(
            @RequestParam(value="visit") Long visitID,
            @RequestParam(value="end", required = false) Timestamp end) {
        Visit visit = visitDao.getById(visitID);
        if (end != null) {
            visit.setEnd(end);
        }
        else {
            visit.setEnd(Timestamp.from(Instant.now()));
        }
        visitDao.save(visit);
        return new ResponseEntity<String> ("Offer created.", HttpStatus.OK);
    }

}


// These commands can be used to test functionality of different add and search methods,
// you might have to update them for your particular setup if Mingo isn't running on localhost
//curl -d 'username=fooforreal&email=fooforreal@foo.com' http://localhost:8080/user-add
//curl -d 'username=fooforreal1&email=fooforreal1@food.com&password=password' http://localhost:8080/user-add
//curl -d 'username=fooforreal2&na' http://localhost:8080/user-add
//curl -d 'username=fooforreal3&na' http://localhost:8080/user-add
//curl -d 'username=fooforreal4&na' http://localhost:8080/user-add
//curl -d 'business=2&interest=8' http://localhost:8080/business-interest
//curl -d 'user=2&interest=8' http://localhost:8080/user-interest