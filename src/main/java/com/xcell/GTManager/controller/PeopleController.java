package com.xcell.GTManager.controller;

import com.xcell.GTManager.dto.PersonDto;
import com.xcell.GTManager.enums.EEducationLevel;
import com.xcell.GTManager.enums.EKinship;
import com.xcell.GTManager.enums.ESex;
import com.xcell.GTManager.model.services.DegreeService;
import com.xcell.GTManager.model.services.HouseholdService;
import com.xcell.GTManager.model.services.PeopleService;
import com.xcell.GTManager.model.tables.Degree;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller responsible for managing the People table.
 * <p>
 * Exposes endpoints for creating, deleting, retrieving and modifying records.
 * Uses the PeopleService to perform CRUD operations.
 * Uses the DegreeService to add or remove degrees from people.
 * Uses the HouseholdService to populate the dropdown list of households in the creation/editing form.
 */
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

    /**
     * Displays a list of all records in the People table.
     *
     * @param model the model used to pass data to the view
     * @return the name of the view to populate
     */
    @GetMapping
    public String list(Model model){
        model.addAttribute("people", peopleService.getAll());
        return "people/list";
    }

    /**
     * Displays a form for creating a new record in the People table.
     * <p>
     * For restrictive values, dropdown menus are created to select from.
     *
     * @param model the model used to pass data to the view
     * @return the name of the view for the form
     */
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

    /**
     * Creates a new record in the People table.
     * <p>
     * Redirects to the updated list of all records after creation.
     *
     * @param person the data to create the record with
     * @return a redirect to the list of all records
     */
    @PostMapping
    public String create(@ModelAttribute("person") PersonDto person){
        peopleService.create(person);
        return "redirect:/people";
    }

    /**
     * Displays a form for editing an existing record in the People table.
     * <p>
     * For restrictive values, dropdown menus are created to select from.
     *
     * @param id the id of the record to edit
     * @param model the model used to pass data to the view
     * @return the name of the view for the form
     */
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

    /**
     * Updates an existing record in the People table.
     * <p>
     * Redirects to the updated list of all records after update.
     *
     * @param id the id of the record to update
     * @param person the data to update the record with
     * @return a redirect to the list of all records
     */
    @PostMapping("/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute("person") PersonDto person){
        peopleService.update(id, person);
        return "redirect:/people";
    }

    /**
     * Deletes an existing record from the People table.
     * <p>
     * Redirects to the updated list of all records after deletion.
     *
     * @param id the id of the record to delete
     * @return a redirect to the list of all records
     */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id){
        peopleService.delete(id);
        return "redirect:/people";
    }

    /**
     * Displays a detailed view of a single record in the People table.
     * <p>
     * The detailed view includes the degrees a person holds and the historical records of the person.
     *
     * @param id the id of the record to display details for
     * @param model the model used to pass data to the view
     * @return the name of the view to populate
     */
    @GetMapping("/{id}")
    public String details(@PathVariable Integer id, Model model){
        model.addAttribute("person", peopleService.getCurrent(id));
        model.addAttribute("history", peopleService.getHistory(id));
        model.addAttribute("degrees", degreeService.getForPerson(id));
        return "people/details";
    }

    /**
     * Displays a form for creating a new degree for a person.
     *
     * @param id the id of the person to award the degree to
     * @param model the model used to pass data to the view
     * @return the name of the view for the form
     */
    @GetMapping("/{id}/degrees/new")
    public String createDegreeForm(@PathVariable Integer id, Model model){
        model.addAttribute("person", peopleService.getCurrent(id));
        model.addAttribute("degree", new Degree());
        model.addAttribute("formAction", "/people/" + id + "/degrees");
        return "people/degree-form";
    }

    /**
     * Creates a new degree for a person.
     * <p>
     * Redirects to the updated person record after creation.
     *
     * @param id the id of the person to award the degree to
     * @param degree the data to create the degree with
     * @return a redirect to the list of all people
     */
    @PostMapping("/{id}/degrees")
    public String createDegree(@PathVariable Integer id, @ModelAttribute("degree") Degree degree){
        degreeService.createForPerson(id, degree);
        return "redirect:/people/" + id;
    }

}
