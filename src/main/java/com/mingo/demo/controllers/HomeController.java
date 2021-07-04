package com.mingo.demo.controllers;

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

@Controller
public class HomeController {


    @Autowired private UserRepository userDao;
    @Autowired private BusinessRepository businessDao;
    @Autowired private MessageRepository messageDao;
    @Autowired private CategoryRepository categoryDao;
    @Autowired private InterestRepository interestDao;

    @GetMapping("/")
    public String hello() {
        return "home";
    }

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

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute User user) {
        userDao.save(user);
        return "redirect:/test";
    }


    @RequestMapping(value = "/user.json", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<User> userSearch() throws Exception {
        List<User> users = userDao.findAll();

        return users;
    }

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

    @PostMapping("/message")
    public String messageSend(@ModelAttribute Message message, @RequestParam(value="sender") long sender, @RequestParam(value="receiver") long receiver) {
        message.setSender(userDao.getById(sender));
        message.setReceiver(userDao.getById(receiver));
        message.setSent(Timestamp.from(Instant.now()));
        message.setStatus(MessageStatus.DELIVERED);
        messageDao.save(message);
        return "redirect:/test";
    }

    @RequestMapping(value = "/message.json", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Message> messageSearch() throws Exception {
        List<Message> messages = messageDao.findAll();

        return messages;
    }

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

    @PostMapping("/interest-create")
    public String interestCreate(@ModelAttribute Interest interest) {
        interestDao.save(interest);
        return "redirect:/test";
    }

    @RequestMapping(value = "/interest.json", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Interest> interstSearch() throws Exception {
        List<Interest> interests = interestDao.findAll();

        return interests;
    }

}
