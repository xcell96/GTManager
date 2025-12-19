package com.xcell.GTManager.controller;

import com.xcell.GTManager.model.services.DimHouseholdService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * REST controller responsible for strictly viewing historical records of households ({@link com.xcell.GTManager.model.tables.DimHousehold}).
 * <p>
 * Uses the {@link DimHouseholdService} to perform data access operations.
 */
@Controller
@RequestMapping("/dimhouseholds")
public class DimHouseholdController {

    private final DimHouseholdService dimHouseholdService;

    public DimHouseholdController(DimHouseholdService dimHouseholdService) {
        this.dimHouseholdService = dimHouseholdService;
    }

    /**
     * Displays a list of all historical records of households.
     *
     * @param model the model used to pass data to the view
     * @return the name of the view to populate
     */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("dimhouseholds", dimHouseholdService.getAll());
        return "dimhouseholds/list";
    }

    /**
     * Displays the details of a single historical record of a household.
     * <p>
     * Also displays how many and what members the household had at the time of the record.
     *
     * @param sk the surrogate key that represents the historical household record
     * @param model the model used to pass data to the view
     * @return the name of the view to populate
     */
    @GetMapping("/{sk}")
    public String details(@PathVariable Integer sk, Model model) {
        model.addAttribute("household", dimHouseholdService.getBySk(sk));
        model.addAttribute("memberCount", dimHouseholdService.getMemberCount(sk));
        model.addAttribute("members", dimHouseholdService.getMembers(sk));
        return "dimhouseholds/details";
    }
}
