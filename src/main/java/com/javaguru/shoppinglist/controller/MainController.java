package com.javaguru.shoppinglist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@Transactional
@EnableWebMvc
public class MainController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        model.addAttribute("title", "Shopping List");
        model.addAttribute("greeting", "Welcome to our shop!");
        return "index";
    }

    @RequestMapping("/403")
    public String accessDenied(ModelMap model) {
        model.addAttribute("error", "Access Denied");
        model.addAttribute("code", "403");
        return "403";
    }
}