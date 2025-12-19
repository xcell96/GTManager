package com.xcell.GTManager.controller;

import com.xcell.GTManager.model.services.DimPeopleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * REST controller responsible for strictly viewing the historical records of people ({@link com.xcell.GTManager.model.tables.DimPerson}).
 * <p>
 * Uses the {@link DimPeopleService} to perform data access operations.
 */
@Controller
@RequestMapping("/dimpeople")
public class DimPeopleController {

    private final DimPeopleService dimPeopleService;

    public DimPeopleController(DimPeopleService dimPeopleService) {
        this.dimPeopleService = dimPeopleService;
    }

    /**
     * Displays a list of all historical records of people.
     *
     * @param model the model used to pass data to the view
     * @return the name of the view to populate
     */
    @RequestMapping
    public String list(Model model) {
        model.addAttribute("dimpeople", dimPeopleService.getAll());
        return "dimpeople/list";
    }

}
