package com.example.testClasses;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoGame {

    @JsonProperty("category")
    private String category;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("rating")
    private String rating;
    @JsonProperty("releaseDate")
    private String releaseDate;
    @JsonProperty("reviewScore")
    private Integer reviewScore;


    public VideoGame(String category, String name, String rating, String releaseDate, Integer reviewScore) {
        this.category = category;
        this.name = name;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.reviewScore = reviewScore;
    }
}
