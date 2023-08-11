package com.example.movie.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter

// @data setter 쓰면 안되서 안됨
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movies_id; //db는 _ java 대문자
    private Long apiId;
    private String title;
    private String poster_path;

    @Builder

    public Movie(Long movies_id, Long apiId, String title, String poster_path) {
        this.movies_id = movies_id;
        this.apiId = apiId;
        this.title = title;
        this.poster_path = poster_path;
    }
}
