package org.example.assignment_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        // Add student information
        model.addAttribute("studentName", "Your Name Here");
        model.addAttribute("studentNumber", "12345678");
        model.addAttribute("welcomeMessage", "Welcome to Movie Management System");

        return "index";
    }
}
