package me.alfrendosilalahi.jobportal.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import me.alfrendosilalahi.jobportal.service.UserService;

@Controller
@RequiredArgsConstructor
public class JobPostActivityController {
    
    private final UserService userService;

    @GetMapping("/dashboard")
    public String searchJobs(Model model) {
        Object currentUserProfile = userService.getCurrentUserProfile();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            model.addAttribute("username", username);
        }
        model.addAttribute("user", currentUserProfile);

        return "dashboard";
    }

}
