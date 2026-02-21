package org.example.assignment_2.service;

import org.example.assignment_2.data.MovieRepository;
import org.example.assignment_2.model.Movie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Get all movies
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // Get movie by ID
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    // Save movie (create or update)
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // Delete movie by ID
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    // Search movies by title
    public List<Movie> searchByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    // Search movies by genre
    public List<Movie> searchByGenre(String genre) {
        return movieRepository.findByGenreContainingIgnoreCase(genre);
    }

    // Search movies by director
    public List<Movie> searchByDirector(String director) {
        return movieRepository.findByDirectorContainingIgnoreCase(director);
    }

    // Get movies with minimum rating
    public List<Movie> getMoviesByMinRating(Double minRating) {
        return movieRepository.findByRatingGreaterThanEqual(minRating);
    }

    // Advanced search with multiple criteria
    public List<Movie> searchMovies(String title, String genre, Double minRating) {
        return movieRepository.searchMovies(title, genre, minRating);
    }

    // Get movie count
    public long getMovieCount() {
        return movieRepository.count();
    }

    // Get average rating
    public double getAverageRating() {
        List<Movie> movies = getAllMovies();
        if (movies.isEmpty()) return 0.0;

        double sum = movies.stream()
                .mapToDouble(Movie::getRating)
                .sum();

        return Math.round((sum / movies.size()) * 10.0) / 10.0;
    }
}
