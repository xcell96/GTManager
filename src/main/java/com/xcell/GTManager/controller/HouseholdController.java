package com.xcell.GTManager.controller;

import com.xcell.GTManager.dto.HouseholdDto;
import com.xcell.GTManager.model.services.HouseholdService;
import com.xcell.GTManager.model.tables.Household;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller responsible for managing the Households table.
 * <p>
 * Exposes endpoints for creating, deleting, retrieving and modifying records.
 * Uses the HouseholdService to perform CRUD operations.
 */
@Controller
@RequestMapping("/households")
public class HouseholdController {

    private final HouseholdService householdService;

    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    /**
     * Displays a list of all records in the Households table.
     *
     * @param model the model used to pass data to the view
     * @return the name of the view to populate
     */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("households", householdService.getAll());
        return "households/list";
    }

    /**
     * Displays a form for creating a new record in the Households table.
     *
     * @param model the model used to pass data to the view
     * @return the name of the view for the form
     */
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("household", new Household());
        model.addAttribute("formAction", "/households");
        return "households/form";
    }

    /**
     * Creates a new record in the Households table.
     * <p>
     * Redirects to the updated list of all records after creation.
     *
     * @param household the data to create the record with
     * @return a redirect to the list of all records
     */
    @PostMapping
    public String create(@ModelAttribute HouseholdDto household) {
        householdService.create(household);
        return "redirect:/households";
    }

    /**
     * Displays a form for editing an existing record in the Households table.
     *
     * @param id the id of the household record to edit
     * @param model the model used to pass data to the view
     * @return the name of the view for the form
     */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("household", householdService.getCurrent(id));
        model.addAttribute("formAction", "/households/" + id);
        return "households/form";
    }

    /**
     * Updates an existing record in the Households table.
     * <p>
     * Redirects to the updated list of all records after update.
     *
     * @param id the id of the household record to update
     * @param household the data to update the record with
     * @return a redirect to the list of all records
     */
    @PostMapping("/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute HouseholdDto household) {
        householdService.update(id, household);
        return "redirect:/households";
    }

    /**
     * Deletes an existing record from the Households table.
     * <p>
     * Redirects to the updated list of all records after deletion.
     *
     * @param id the id of the household record to delete
     * @return a redirect to the list of all records
     */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        householdService.delete(id);
        return "redirect:/households";
    }

    /**
     * Displays details for a specific household record.
     * <p>
     * In addition to usual records, the details contain the household's member count, a list of members
     * and the list of historical records bound to that specific household.
     *
     * @param id the id of the household record to display details for
     * @param model the model used to pass data to the view
     * @return the name of the view to populate
     */
    @GetMapping("/{id}")
    public String details(@PathVariable Integer id, Model model){
        model.addAttribute("household", householdService.getCurrent(id));
        model.addAttribute("memberCount", householdService.getMemberCount(id));
        model.addAttribute("members", householdService.getMembers(id));
        model.addAttribute("history", householdService.getHistory(id));
        return "households/details";
    }
}
