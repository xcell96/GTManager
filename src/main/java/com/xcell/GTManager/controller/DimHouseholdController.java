package com.xcell.GTManager.controller;

import com.xcell.GTManager.model.services.DimHouseholdService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dimhouseholds")
public class DimHouseholdController {

    private final DimHouseholdService dimHouseholdService;

    public DimHouseholdController(DimHouseholdService dimHouseholdService) {
        this.dimHouseholdService = dimHouseholdService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("dimhouseholds", dimHouseholdService.getAll());
        return "dimhouseholds/list";
    }

    @GetMapping("/{sk}")
    public String details(@PathVariable Integer sk, Model model) {
        model.addAttribute("household", dimHouseholdService.getBySk(sk));
        model.addAttribute("memberCount", dimHouseholdService.getMemberCount(sk));
        model.addAttribute("members", dimHouseholdService.getMembers(sk));
        return "dimhouseholds/details";
    }
}
