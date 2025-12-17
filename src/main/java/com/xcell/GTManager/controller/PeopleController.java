package com.xcell.GTManager.controller;

import com.xcell.GTManager.model.services.DegreeService;
import com.xcell.GTManager.model.services.PeopleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final DegreeService degreeService;

    public PeopleController(PeopleService peopleService, DegreeService degreeService) {
        this.peopleService = peopleService;
        this.degreeService = degreeService;
    }

    @GetMapping
    public String list(Model model){
        model.addAttribute("people", peopleService.getAll());
        return "people/list";
    }

}
