package org.example.assignment_2.controller;

import jakarta.validation.Valid;
import org.example.assignment_2.service.MovieService;
import org.example.assignment_2.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public String listMovies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Double minRating,
            Model model) {

        List<Movie> movies;

        if ((title != null && !title.isEmpty()) ||
                (genre != null && !genre.isEmpty()) ||
                minRating != null) {

            movies = movieService.searchMovies(title, genre, minRating);
        } else {
            movies = movieService.getAllMovies();
        }

        model.addAttribute("movies", movies);
        model.addAttribute("movieCount", movieService.getMovieCount());
        model.addAttribute("averageRating", movieService.getAverageRating());
        model.addAttribute("searchTitle", title);
        model.addAttribute("searchGenre", genre);
        model.addAttribute("minRating", minRating);

        return "movie-list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "movie-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Movie> movie = movieService.getMovieById(id);
        if (movie.isPresent()) {
            model.addAttribute("movie", movie.get());
            return "movie-form";
        }
        return "redirect:/movies";
    }

    @PostMapping("/save")
    public String saveMovie(@Valid @ModelAttribute("movie") Movie movie,
                            BindingResult result, Model model) {

        if (result.hasErrors()) {
            System.out.println("Form does not match formatting");
            return "movie-form";
        }

        System.out.println("DEBUG: Saving movie: " + movie.getTitle());
        movieService.saveMovie(movie);

        return "redirect:/movies";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return "redirect:/movies";
    }
}
