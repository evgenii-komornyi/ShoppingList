package com.javaguru.shoppinglist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping(method = RequestMethod.GET)
    public String homePage() {
        return "index";
    }
    @RequestMapping(value = "/main")
    public String getMainPage() {
        return "templates/main";
    }
}