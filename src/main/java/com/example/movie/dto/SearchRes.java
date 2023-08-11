package com.example.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchRes {

    private String title;
    private String poster;
    private Long id;
    private double vote_average;

}
