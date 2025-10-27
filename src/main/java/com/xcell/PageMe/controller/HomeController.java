package com.xcell.PageMe.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    private final ResourceLoader resourceLoader;

    public HomeController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/")
    public Resource index() {
        return resourceLoader.getResource("classpath:/static/index.html");
    }
}
