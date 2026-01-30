package com.xcell.GTManager.controller;

import com.xcell.GTManager.model.services.DimPeopleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

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
     * Displays a list of all historical records of people with optional filtering.
     *
     * @param fromDate optional start date for filtering (format: yyyy-MM-ddTHH:mm)
     * @param toDate optional end date for filtering (format: yyyy-MM-ddTHH:mm)
     * @param personId optional person ID for filtering
     * @param householdId optional household ID for filtering
     * @param model the model used to pass data to the view
     * @return the name of the view to populate
     */
    @GetMapping
    public String list(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate,
            @RequestParam(required = false) Integer personId,
            @RequestParam(required = false) Integer householdId,
            Model model
            ) {

        model.addAttribute("dimpeople", dimPeopleService.search(fromDate, toDate, personId, householdId));

        // Add filter values to maintain them in the form
        model.addAttribute("currentFromDate", fromDate);
        model.addAttribute("currentToDate", toDate);
        model.addAttribute("currentPersonId", personId);
        model.addAttribute("currentHouseholdId", householdId);

        return "dimpeople/list";
    }

}
