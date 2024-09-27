package com.example.restassuredtests.restTests;

import com.example.restassuredtests.config.VideoGameConfig;
import com.example.restassuredtests.endpoints.VideoGameEndpoints;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class VideoGameTests extends VideoGameConfig {


    public static final String testJson = """
            {
              "category": "Platform",
              "name": "Mario",
              "rating": "Mature",
              "releaseDate": "2012-05-04",
              "reviewScore": 85
            }
            """;

    @Test
    public void getAllGames() {
        given()
                .when().get(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then();
    }

    @Test
    public void creteNewGameByJSON() throws JsonProcessingException {
        given()
                .body(testJson)
                .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then();
    }


    @Test
    public void updateGame(){
        given()
                .body(testJson)
        .when()
                .put("videogame/3")
        .then();
    }

    @Test
    public void deleteGame(){
        given().accept(ContentType.TEXT)
        .when().delete("videogame/8")
        .then();
    }

    @Test
    public void getGame(){
        given()
                .pathParam("videoGameId",1)
        .when()
                .get(VideoGameEndpoints.VIDEO_GAME)
        .then();
    }
}
