package com.example.restassuredtests.restTests;

import com.example.restassuredtests.config.FootballConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class FootballTest extends FootballConfig {
    @Test
    public void getAllAreas(){
        given()
                .when().get("/areas")
                .then();
    }
    @Test
    public void getDetailsOfOneArea(){
        given().queryParam("areas",20)
                .when()
                .get("/areas");
    }

    @Test
    public void getMultipleArenasDetails(){
        String exampleAreas = "2000,2001,2003";

        given()
                .queryParam("areas",exampleAreas)
        .when()
                .get("areas");
    }
    @Test
    public void getDateFounded(){
        given()
        .when()
                .pathParam("id",57)
                .get("teams/{id}")
                .then()
                .body("founded",equalTo(1886));
    }

    @Description("List all teams for a particular competition -> /v4/competitions/{id}/teams")
    @Test
    public void getFirstTeamName(){
        given()
        .when()
                .pathParam("id",2021)
                .get("competitions/{id}/teams")
        .then()
                .body("teams.name[0]",equalTo("Arsenal FC"));
    }
    @Description(value = "Grabbing all response body")
    @Test
    public void getAllTeamDataOnly(){
        String responseBody =
                get("teams/57")
                        .asString();
        System.out.println(responseBody);

    }

    @Test
    public void getAllTeamData() {

        Response response =
                given()
                .when()
                    .pathParam("id", 57)
                    .get("teams/{id}")
                .then()
                    .contentType(ContentType.JSON)
                    .extract().response();
        String jsonResponseAsString = response.asString();
    }

    @Description(value = "Checking headers")
    @Test
    public void extractHeaders(){
        Response response =
                get("teams/57")
                        .then()
                        .extract().response();

        Headers allHeaders = response.getHeaders();
        String contentTypeHeader = response.getContentType();

        String apiVersionHeader = response.getHeader("X-API-Version");
    }
    @Description(value = "JSON Path - extract explicit data from body")
    @Test
    public void extractFirstTeamName(){
        String firstTeamName  =
                get("competitions/2021/teams").jsonPath().getString("teams.name[0]");
        System.out.println(firstTeamName);
    }
    @Description(value = "JSON Path - extract explicit data from body all teams ")
    @Test
    public void extractAllTeamNames(){
        Response response  =
                get("competitions/2021/teams")
                        .then()
                                .extract().response();

        List<String> teamNames = response.path("teams.name");
        for (String teamName : teamNames) {
            System.out.println(teamName);
        }
    }

}
