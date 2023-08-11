package com.example.movie.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

// 생성자도 만들어줌
@Data
@NoArgsConstructor
public class SearchReq {
    private String query;
    private final String language="ko-KR";

    public SearchReq(String query) {
        this.query = query;
    }
}
