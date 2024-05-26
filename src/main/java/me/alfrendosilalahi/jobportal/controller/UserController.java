package me.alfrendosilalahi.jobportal.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.alfrendosilalahi.jobportal.model.User;
import me.alfrendosilalahi.jobportal.model.UserType;
import me.alfrendosilalahi.jobportal.service.UserService;
import me.alfrendosilalahi.jobportal.service.UserTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserTypeService userTypeService;

    private final UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        List<UserType> userTypes = userTypeService.getAllUserTypes();
        model.addAttribute("userTypes", userTypes);
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register/new")
    public String userRegistration(@Valid User user) {
        log.info("User:: {}", user);
        userService.createUser(user);
        return "dashboard";
    }

}
