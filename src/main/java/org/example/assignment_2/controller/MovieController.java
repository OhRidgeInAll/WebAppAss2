package org.example.assignment_2.controller;

import org.example.assignment_2.service.MovieService;
import org.example.assignment_2.model.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies/search")
    public String searchMovies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Double minRating,
            Model model) {

        List<Movie> movies;

        if (title != null && !title.isEmpty()) {
            movies = movieService.searchByTitle(title);
        } else if (genre != null && !genre.isEmpty()) {
            movies = movieService.searchByGenre(genre);
        } else if (minRating != null) {
            movies = movieService.getMoviesByMinRating(minRating);
        } else {
            movies = movieService.getAllMovies();
        }

        model.addAttribute("movies", movies);
        model.addAttribute("searchTitle", title);
        model.addAttribute("searchGenre", genre);
        model.addAttribute("minRating", minRating);

        return "movie-list"; // Reuse the same template!
    }

}
