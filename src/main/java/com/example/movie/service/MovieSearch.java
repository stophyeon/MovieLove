package com.example.movie.service;


import com.example.movie.dto.SearchReq;
import com.example.movie.dto.SearchRes;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MovieSearch {

    private final String key;
    {key = "11166be7a23d049da628847010a8fd01";}
    public List<SearchRes> searchMovie(SearchReq searchReq) throws IOException, ParseException, org.json.simple.parser.ParseException {
        String url = "https://api.themoviedb.org/3/search/movie";
        URI uri = UriComponentsBuilder.fromUriString(url)
                .queryParam("query",searchReq.getQuery())
                .queryParam("api_key",key)
                .queryParam("language",searchReq.getLanguage())
                .build()
                .encode() // uri는 encode 해줘야함 String 으로 안됨
                .toUri(); //uri로 encode해줌

        // restapi 틀 = resttemplate
        RestTemplate restTemplate = new RestTemplate();
        // api로 위 uri 를 response에 담김 전부
        ResponseEntity<?> response = restTemplate.getForEntity(uri, String.class);

        // 가공하기 ~
        List<SearchRes> movies = new ArrayList<>();

        // parser에 response 넣어줌
        JSONParser jsonParser = new JSONParser(); // json 형식 받아올 준비
        // 데이터를 object에 넣어줌
        JSONObject jsonObject = (JSONObject) jsonParser.parse(Objects.requireNonNull(response.getBody()).toString());
        // 원하는 정보가 array에 담음
        JSONArray jsonArray = (JSONArray) jsonObject.get("results"); // 가공 준비 끝 ~

        // 안에 있는 정보들을 searchres 형태로 바꿔줌
        for (Object o : jsonArray){
            JSONObject jsonObject1 = (JSONObject) o;
            Long id = (Long) jsonObject1.get("id");
            String title = (String) jsonObject1.get("title");
            String poster = "https://image.tmdb.org/t/p/w500" + jsonObject1.get("poster_path");
            double vote_average = (double) jsonObject1.get("vote_average");
            movies.add(new SearchRes(title,poster,id,vote_average));

        }
        return movies;

}}
