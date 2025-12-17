package com.xcell.GTManager.controller;

import com.xcell.GTManager.model.services.DimPeopleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dimpeople")
public class DimPeopleController {

    private final DimPeopleService dimPeopleService;

    public DimPeopleController(DimPeopleService dimPeopleService) {
        this.dimPeopleService = dimPeopleService;
    }

    @RequestMapping
    public String list(Model model) {
        model.addAttribute("dimpeople", dimPeopleService.getAll());
        return "dimpeople/list";
    }

}
