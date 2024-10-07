package com.example.restassuredtests.restTests;

import com.example.restassuredtests.config.VideoGameConfig;
import com.example.restassuredtests.endpoints.VideoGameEndpoints;
import com.example.testClasses.VideoGame;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

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
    public void updateGame() {
        given()
                .body(testJson)
                .when()
                .put("videogame/3")
                .then();
    }

    @Test
    public void deleteGame() {
        given().accept(ContentType.TEXT)
                .when().delete("videogame/8")
                .then();
    }

    @Test
    public void getGame() {
        given()
                .pathParam("videoGameId", 1)
                .when()
                .get(VideoGameEndpoints.VIDEO_GAME)
                .then();
    }

    @Description(value = "Trzeba ręcznie generować gettery i settery oraz NoArgsConstrcuctor" +
            "Ponieważ jest to wymagane przez Json parsera." +
            "Opcjonalnie można dodać pola:" +
            "    @JsonProperty(\"age\")\n żęby Jackson wiedział jak parsować")

//    https://www.jsonschema2pojo.org/
    @Test
    public void testVideoGameSerializationByJson() {
        VideoGame testVideoGame = new VideoGame("Comedy", "Shrek", "Mature",  "2002-01-02", 99);


        given().body(testVideoGame)
                .when().post(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then();
    }

    //    Walidacja z XML Schema (XSD)
//    https://www.freeformatter.com/xsd-generator.html#before-output
//    Wygenerowany XSD czasami wymaga korekcji kolejności elementów do json'a
    @Test
    public void testVideoGameXsdSchema() {
        given()
                .pathParam("videoGameId", 5)
                .accept(ContentType.XML.toString())
                .when()
                .get("/videogame/{videoGameId}")
                .then()
                .body(RestAssuredMatchers.matchesXsdInClasspath("VideoGameSchema.xsd"));
    }

    //    Walidacja z JSON Schema (sprawdzanie czy otrzymany JSON pokrywa się z get)
//    https://json-schema.org/learn/getting-started-step-by-step
//    Wymagana zależność json-schema-validator
//    Date ustawiono jako regex
//    JSON to JSON Schema
    @Test
    public void testVideoGameJsonSchema() {
        given()
                .pathParam("videoGameId", 4)
                .accept(ContentType.JSON.toString())
                .when()
                .get(VideoGameEndpoints.VIDEO_GAME)
                .then()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("VideoGameSchema.json"));
    }
//    Pobieranie JSON i porównywanie z klasą POJO

    @Test
    public void convertJsonToPojo() {
        Response response =
                given()
                        .pathParam("videoGameId", 4)
                        .when()
                        .get(VideoGameEndpoints.VIDEO_GAME);
//        Działa bo mamy noArgsConstructor - serializacja
        VideoGame videoGame = response.getBody().as(VideoGame.class);
    }

    //    Pomiar czasu
//    https://github.com/rest-assured/rest-assured/wiki/Usage#measuring-response-time
    @Test
    public void captureResponseTime() {
        long responseTime =
                get(VideoGameEndpoints.ALL_VIDEO_GAMES).time();
        System.out.println(responseTime);
    }

    @Test
    public void asserOnResponseTime() {
        get(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then().assertThat().time(lessThan(2000L));
    }


}
