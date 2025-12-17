package com.xcell.GTManager.controller;

import com.xcell.GTManager.dto.HouseholdDto;
import com.xcell.GTManager.model.services.HouseholdService;
import com.xcell.GTManager.model.tables.Household;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/households")
public class HouseholdController {

    private final HouseholdService householdService;

    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("households", householdService.getAll());
        return "households/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("household", new Household());
        model.addAttribute("formAction", "/households");
        return "households/form";
    }

    @PostMapping
    public String create(@ModelAttribute HouseholdDto household) {
        householdService.create(household);
        return "redirect:/households";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("household", householdService.getCurrent(id));
        model.addAttribute("formAction", "/households/" + id);
        return "households/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute HouseholdDto household) {
        householdService.update(id, household);
        return "redirect:/households";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        householdService.delete(id);
        return "redirect:/households";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Integer id, Model model){
        model.addAttribute("household", householdService.getCurrent(id));
        model.addAttribute("memberCount", householdService.getMemberCount(id));
        model.addAttribute("members", householdService.getMembers(id));
        model.addAttribute("history", householdService.getHistory(id));
        return "households/details";
    }
}
