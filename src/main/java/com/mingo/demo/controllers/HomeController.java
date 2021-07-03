package com.mingo.demo.controllers;

import com.mingo.demo.daos.BusinessRepository;
import com.mingo.demo.daos.UserRepository;
import com.mingo.demo.models.Business;
import com.mingo.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired private UserRepository userDao;
    @Autowired private BusinessRepository businessDao;

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
        model.addAttribute("user", user);
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("business", business);
        model.addAttribute("allBusinesses", allBusinesses);
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

}
