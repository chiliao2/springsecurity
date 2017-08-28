package com.cdz.jn.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String loginForm() {
        return "login_1";
    }

    @RequestMapping(value = "/403")
    public String accessDenied() {
        return "accessDenied";
    }
}
