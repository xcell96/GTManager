package com.xcell.GTManager.controller;

import com.xcell.GTManager.dto.PersonDto;
import com.xcell.GTManager.enums.EEducationLevel;
import com.xcell.GTManager.enums.EKinship;
import com.xcell.GTManager.enums.ESex;
import com.xcell.GTManager.model.services.DegreeService;
import com.xcell.GTManager.model.services.HouseholdService;
import com.xcell.GTManager.model.services.PeopleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final DegreeService degreeService;
    private final HouseholdService householdService;

    public PeopleController(
            PeopleService peopleService,
            DegreeService degreeService,
            HouseholdService householdService
    ) {
        this.peopleService = peopleService;
        this.degreeService = degreeService;
        this.householdService = householdService;
    }

    @GetMapping
    public String list(Model model){
        model.addAttribute("people", peopleService.getAll());
        return "people/list";
    }

    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("person", new PersonDto());
        model.addAttribute("households", householdService.getAll());
        model.addAttribute("sexValues", ESex.values());
        model.addAttribute("kinshipValues", EKinship.values());
        model.addAttribute("educationValues", EEducationLevel.values());
        model.addAttribute("formAction", "/people");
        return "people/form";
    }

    @PostMapping
    public String create(@ModelAttribute("person") PersonDto person){
        peopleService.create(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model){
        model.addAttribute("person", peopleService.getCurrent(id));
        model.addAttribute("households", householdService.getAll());
        model.addAttribute("sexValues", ESex.values());
        model.addAttribute("kinshipValues", EKinship.values());
        model.addAttribute("educationValues", EEducationLevel.values());
        model.addAttribute("formAction", "/people/" + id);
        return "people/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute("person") PersonDto person){
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id){
        peopleService.delete(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Integer id, Model model){
        model.addAttribute("person", peopleService.getCurrent(id));
        model.addAttribute("history", peopleService.getHistory(id));
        return "people/details";
    }

}
