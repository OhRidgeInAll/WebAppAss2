package org.example.assignment_2.controller;

import org.example.assignment_2.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("studentName", "Your Name");  // Replace with actual
        model.addAttribute("studentNumber", "12345678"); // Replace with actual
        model.addAttribute("welcomeMessage", "Welcome to Movie Management System");
        model.addAttribute("movieCount", movieService.getMovieCount());
        model.addAttribute("averageRating", movieService.getAverageRating());
        return "index";
    }
}