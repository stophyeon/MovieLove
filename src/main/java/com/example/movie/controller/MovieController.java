package com.example.movie.controller;

import com.example.movie.dto.SearchReq;
import com.example.movie.dto.SearchRes;
import com.example.movie.service.MovieSearch;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Controller
public class MovieController {
    private final MovieSearch movieSearch;

    public MovieController(MovieSearch movieSearch) {
        this.movieSearch = movieSearch;
    }

    @PostMapping("/search")
    public String search(SearchReq sq, Model model) throws IOException, ParseException, org.json.simple.parser.ParseException {
        List<SearchRes> sr = movieSearch.searchMovie(sq);
        model.addAttribute("movies",sr);
        return "movies";

    }



}
