package it.lessons.best_of_the_year.controller;

import java.util.Arrays;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.lessons.best_of_the_year.model.Movie;
import it.lessons.best_of_the_year.model.Song;

@Controller
public class BestOfTheYearController {
    
	@GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "Beatrice"); 
        return "index";
    }
	
	private List<Movie> getBestMovies() {
	    return Arrays.asList(
	        new Movie(1, "Matrix"),
	        new Movie(2, "Ghost"),
	        new Movie(3, "Alice in Wonderland")
	    );
	}

	private List<Song> getBestSongs() {
	    return Arrays.asList(
	        new Song(1, "Oasis - Don't look back in anger"),
	        new Song(2, "The Strokes - Under Cover of Darkness"),
	        new Song(3, "Cage The Elephant - Metaverse")
	    );
	}
	
	@GetMapping("/movies")
	public String movies(Model model) {
	    List<Movie> movies = getBestMovies();
	    model.addAttribute("movies", movies);
	    return "movies";
	}

	@GetMapping("/songs")
	public String songs(Model model) {
	    List<Song> songs = getBestSongs();
	    model.addAttribute("songs", songs);
	    return "songs";
	}

	@GetMapping("/movies/{id}")
	public String movieById(@PathVariable int id, Model model) {
	    List<Movie> movies = getBestMovies();

	    Movie selectedMovie = null;
	    for (Movie movie : movies) {
	        if (movie.getId() == id) {
	            selectedMovie = movie;
	            break;
	        }
	    }
	    
	    if (selectedMovie == null) {
            model.addAttribute("errorMessage", "Movie not found");
            return "error"; 
        }
	    
	    model.addAttribute("movie", selectedMovie);
	    return "movie";
	}
	
	@GetMapping("/songs/{id}")
	public String songById(@PathVariable int id, Model model) {
	    List<Song> songs = getBestSongs();

	    Song selectedSong = null;
	    for (Song song : songs) {
	        if (song.getId() == id) {
	            selectedSong = song;
	            break;
	        }
	    }
	    
	    if (selectedSong == null) {
            model.addAttribute("errorMessage", "Song not found");
            return "error"; 
        }
	    
	    model.addAttribute("song", selectedSong);
	    return "song";
	}
}