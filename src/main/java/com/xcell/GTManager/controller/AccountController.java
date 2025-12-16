package com.xcell.GTManager.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AccountController {

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
