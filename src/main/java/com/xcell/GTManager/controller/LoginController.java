package com.xcell.GTManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * REST controller responsible for logging app users in.
 */
@Controller
public class LoginController {

    /**
     * Displays the login page.
     * @return the name of the view for the login page
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
