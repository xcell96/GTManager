package com.xcell.GTManager.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * REST controller responsible for managing user accounts.
 * Exposes one endpoint for viewing the details of one's account.
 */
@Controller
public class AccountController {

    /**
     * Displays the account page for the currently authenticated user.
     * <p>
     * Adds the username and roles of the authenticated user to the model. If no user is authenticated,
     * the username is set to "unknown" and the role list is empty.
     *
     * @param authentication the current authentication context
     * @param model the model used to populate the view
     * @return the name of the account view
     */
    @GetMapping("/account")
    public String account(Authentication authentication, Model model){
        model.addAttribute("username", authentication != null ? authentication.getName() : "unknown");

        List<String> roles = authentication == null
                ? List.of()
                : authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .sorted()
                .toList();

        model.addAttribute("roles", roles);
        return "account";
    }

}
