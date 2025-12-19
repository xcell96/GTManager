package com.xcell.GTManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * REST controller responsible for exposing the home page of the web application.
 */
@Controller
public class HomeController {

    /**
     * Displays the home page of the web application.
     * @return the name of the view
     */
    @GetMapping({"/", "/home"})
    public String home(){
        return "index";
    }
}
