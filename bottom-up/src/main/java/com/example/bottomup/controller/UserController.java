package com.example.bottomup.controller;




import com.example.bottomup.model.User;
import com.example.bottomup.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile/{username}")
    public String getUserProfile(@PathVariable String username, Model model) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user-profile";
        } else {
            return "user-not-found";
        }
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.registerUser(user);
        return "registration-success";
    }
}
