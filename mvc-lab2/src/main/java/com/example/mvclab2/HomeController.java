package com.example.mvclab2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping
    public String home(Model model) {
        model.addAttribute("message", "Hello, Please enter your name");
        return "welcome";
    }

    @PostMapping("/greet")
    public String greet(@RequestParam("name") String name, Model model) {
        model.addAttribute("message", "Hello, Please enter your name");
        model.addAttribute("name", name);
        return "welcome";
    }
}
