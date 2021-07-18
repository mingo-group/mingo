package com.mingo.demo.controllers;

import com.mingo.demo.Services.Haversine;
import com.mingo.demo.daos.*;
import com.mingo.demo.models.*;
import org.springframework.beans.factory.annotation.Autowired;
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
public class HomeController {


    @Autowired private UserRepository userDao;
    @Autowired private BusinessRepository businessDao;
    @Autowired private MessageRepository messageDao;
    @Autowired private CategoryRepository categoryDao;
    @Autowired private InterestRepository interestDao;

//    This / mapping is almost exclusively to check that the server is running. It could be modified to display anything.

    @GetMapping("/")
    public String hello() {
        return "home";
    }

//    In the early parts of creating this backend, I used the /test pathway to test read/write functionality
//    and ensure that the pathways were working correctly and the database was well formed.

//    It's probably vestigal and could be deleted but it is a convenient way of getting an update
//    on what's in the database at this point. It (along with all the non-JSON returning methods, and non 200-OK returning
//    Post methods, can be safely deleted without compromising the functionality of the backend.

    @GetMapping("/test")
    public String test(Model model) {
        User user = new User();
        List<User> allUsers = userDao.findAll();
        Business business = new Business();
        List<Business> allBusinesses = businessDao.findAll();
        Message message = new Message();
        List<Message> allMessages = messageDao.findAll();
        Category category = new Category();
        List<Category> allCategories = categoryDao.findAll();
        Interest interest = new Interest();
        List<Interest> allInterests = interestDao.findAll();

        model.addAttribute("user", user);
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("business", business);
        model.addAttribute("allBusinesses", allBusinesses);
        model.addAttribute("message", message);
        model.addAttribute("allMessages", allMessages);
        model.addAttribute("category", category);
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("interest", interest);
        model.addAttribute("allInterests", allInterests);
        return "test";
    }

//    This Post mapping could be commented out or deleted once the front-end writes to the  REST method
    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute User user) {
        userDao.save(user);
        return "redirect:/test";
    }

//    Despite the fact that it won't print passwords, there's probably a MUCH more secure way to implement
//    this; this is almost exclusively for MVP purposes and to demonstrate technical functionality.

//    The actual production version would need API key protection, and some way to limit the returns based
//    on 'who' was generating the query.

    @RequestMapping(value = "/user.json", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<User> userSearch(
//                        @RequestParam(value="category", required = false) Long categoryID
                          @RequestParam(value="interest", required = false) Long interestID,
                          @RequestParam(value="id", required = false) Long userID
                          ) throws Exception {

        if (interestID != null) {
            List<User> users = userDao.findUsersByInterestsEqualsOrderByUsernameAsc(interestDao.getById(interestID));
            return users;
        }
        else if (userID != null) {
            List<User> users = new ArrayList<>();
            users.add(userDao.getById(userID));
            return users;
        }
        else {
            List<User> users = userDao.findAll();
            return users;
        }
    }

    //    This Post mapping could be commented out or deleted once the front-end writes to the  REST method
    @PostMapping("/business/sign-up")
    public String signUpBusiness(@ModelAttribute Business business) {
        businessDao.save(business);

        return "redirect:/test";
    }



    @RequestMapping(value = "/business.json", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Business> businessSearch() throws Exception {
        List<Business> businesses = businessDao.findAll();

        return businesses;
    }

//    This Post mapping could be commented out or deleted once the front-end writes to the  REST method
    @PostMapping("/message")
    public String messageSend(@ModelAttribute Message message, @RequestParam(value="sender") long sender, @RequestParam(value="receiver") long receiver) {
        message.setSender(userDao.getById(sender));
        message.setReceiver(userDao.getById(receiver));
        message.setSent(Timestamp.from(Instant.now()));
        message.setStatus(MessageStatus.DELIVERED);
        messageDao.save(message);
        return "redirect:/test";
    }

// Again, the security concerns of anyone, at any time, pulling messages meant or from any user is not
//    lost on me, but this is an MVP product not meant for production.
    @RequestMapping(value = "/message.json", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Message> messageSearch(@RequestParam(value="user", required = false) Long userid) throws Exception {
        if (userid == null) {
        List <Message> messages = messageDao.findAll();
        return messages;
        }
        else {
        System.out.println("userid = " + userid);
        User user = userDao.getById(userid);
        List <Message> messages = messageDao.findMessagesBySenderEqualsOrReceiverEqualsOrderBySentDesc(user, user);
        for (int i = 0; i< messages.size(); i++) {
            messages.get(i).setStatus(MessageStatus.UNREAD);
            messageDao.save(messages.get(i));
        }

        return messages;
        }
    }

    //    This Post mapping could be commented out or deleted once the front-end writes to the  REST method
    @PostMapping("/category-create")
    public String categoryCreate(@ModelAttribute Category category) {
        categoryDao.save(category);
        return "redirect:/test";
    }

    @RequestMapping(value = "/category.json", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Category> categorySearch() throws Exception {
        List<Category> categories = categoryDao.findAll();

        return categories;
    }

//    This Post mapping could be commented out or deleted once the front-end writes to the  REST method
    @PostMapping("/interest-create")
    public String interestCreate(@ModelAttribute Interest interest) {
        System.out.println("interest.getName() = " + interest.getName());
        interestDao.save(interest);
        return "redirect:/test";
    }

    @RequestMapping(value = "/interest.json", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Interest> interestSearch(@RequestParam(value="category", required = false) Long categoryid) throws Exception {

        if (categoryid == null) {
        List<Interest> interests = interestDao.findAll();

        return interests;}
        else {
            List<Interest> interestsByCategory = interestDao.findInterestByCategoriesIsOrderByNameAsc(categoryDao.getById(categoryid));
            return interestsByCategory;
        }
    }


//This is not quite feature complete - the methodology exists in the Haversine formula service to
// to convert lat/long into distances, but I haven't implemented the other part (converting a 'mile' radius
// into two 'lat-long' pairs representing a box that MySQL can search in.

//    That said, this methodology isn't very far.
    @RequestMapping(value = "/geographic.users.json", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<User> geographicUserSearch(@RequestParam(value="latitude") Double lattitudeCenter,
                                    @RequestParam(value="longitude") Double longitudeCenter,
                                    @RequestParam(value="radius") Double radiusCenter,
                                    @RequestParam(value="user", required = false) Long userID,
                                    @RequestParam(value="business", required = false) Long businessID
                              ) throws Exception {
        Double latitudeLowerbound = lattitudeCenter-radiusCenter;
        System.out.println("lattitudeLowerbound = " + latitudeLowerbound);
        Double latitudeUpperbound = lattitudeCenter+radiusCenter;
        System.out.println("latitudeUpperbound = " + latitudeUpperbound);
        Double longitudeLowerBound = longitudeCenter-radiusCenter;
        System.out.println("longitudeLowerBound = " + longitudeLowerBound);
        Double longitudeUpperbound = longitudeCenter+radiusCenter;
        System.out.println("longitudeUpperbound = " + longitudeUpperbound);
        List <User> users = userDao.findUsersByLatitudeGreaterThanEqualAndLatitudeIsLessThanEqualAndLongitudeIsGreaterThanEqualAndLongitudeIsLessThanEqual(latitudeLowerbound, latitudeUpperbound, longitudeLowerBound, longitudeUpperbound);
        if (userID != null) {
            User user = userDao.getById(userID);
            for (int i = 0; i < users.size(); i++) {
                User currentUser = users.get(i);
                currentUser.setDistance(Haversine.calculateDistanceBetweenTwoLatLngsReturnInMiles(user.getLatitude(), user.getLongitude(), currentUser.getLatitude(), currentUser.getLongitude()));
                users.set(i, currentUser);
            }
        }
        if (businessID != null) {
            Business business = businessDao.getById(businessID);
            for (int i = 0; i < users.size(); i++) {
                User currentUser = users.get(i);
                currentUser.setDistance(Haversine.calculateDistanceBetweenTwoLatLngsReturnInMiles(business.getLatitude(), business.getLongitude(), currentUser.getLatitude(), currentUser.getLongitude()));
                users.set(i, currentUser);
            }
        }


        return users;
    }

    @RequestMapping(value = "/geographic.business.json", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Business> geographicBusinessSearch(@RequestParam(value="latitude") Double lattitudeCenter,
                                    @RequestParam(value="longitude") Double longitudeCenter,
                                    @RequestParam(value="radius") Double radiusCenter,
                                    @RequestParam(value="user", required = false) Long userID
    ) throws Exception {
        Double latitudeLowerbound = lattitudeCenter-radiusCenter;
        System.out.println("lattitudeLowerbound = " + latitudeLowerbound);
        Double latitudeUpperbound = lattitudeCenter+radiusCenter;
        System.out.println("latitudeUpperbound = " + latitudeUpperbound);
        Double longitudeLowerBound = longitudeCenter-radiusCenter;
        System.out.println("longitudeLowerBound = " + longitudeLowerBound);
        Double longitudeUpperbound = longitudeCenter+radiusCenter;
        System.out.println("longitudeUpperbound = " + longitudeUpperbound);
        List <Business> businesses = businessDao.findBusinessesByLatitudeGreaterThanEqualAndLatitudeIsLessThanEqualAndLongitudeIsGreaterThanEqualAndLongitudeIsLessThanEqual(latitudeLowerbound, latitudeUpperbound, longitudeLowerBound, longitudeUpperbound);
        if (userID != null) {
            User user = userDao.getById(userID);
            for (int i = 0; i < businesses.size(); i++) {
                Business currentBusiness = businesses.get(i);
                currentBusiness.setDistance(Haversine.calculateDistanceBetweenTwoLatLngsReturnInMiles(user.getLatitude(), user.getLongitude(), currentBusiness.getLatitude(), currentBusiness.getLongitude()));
                businesses.set(i, currentBusiness);
            }
        }
        return businesses;
    }
}
