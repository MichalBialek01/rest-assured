package com.example.restassuredtests.gpathTests;

import com.example.restassuredtests.config.FootballConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;

public class GpathJsonTest extends FootballConfig {
    @Description("Pobieramy wszystkie dane jednego teamu o określonej nazwie. Definiujemy to przy pomocy find i it")
    @Test
    public void extractElementsWithFind() {
        Response response = get("competitions/2021/teams");
        Map<String, ?> allDataForSingleTeam = response.path("teams.find {it.name == 'Arsenal FC' }");

        System.out.println(allDataForSingleTeam);
    }

    @Description("Wyciąganie pojedynczej wartości")
    @Test
    public void extractSingleField() {
        Response response = get("teams/57");
        String playerNameById = response.path("squad.find {it.id == 175905}.name");
        System.out.println("Player name :" + playerNameById);
    }

    @Description("Find all")
    @Test
    public void getListOfPlayers() {
        Response response = get("teams/57");
        List<String> playersName = response.path("squad.findAll {it.id > 175905}.name");
        for (String player : playersName) {
            System.out.println(player);
        }
    }

    @Description("Pobieranie najwięszkej wartości - w tym przypadku id ")
    @Test
    public void getByHighestPlayerId(){
        Response response = get("teams/57");
        String playerName = response.path("squad.max {it.id}.name ");
        System.out.println(playerName);
    }
    @Test
    public void getByLowestPlayerId(){
        Response response = get("teams/57");
        String playerName = response.path("squad.max {it.id}.name ");
        System.out.println(playerName);
    }
    @Test
    public void sumAllPlayersId(){
        Response response = get("teams/57");
        String playerName = response.path("squad.collect {it.id}.sum() ");
        System.out.println(playerName);
    }

    @Test
    public void multipleParameter(){
        String position = "Goalkeeper";
        String nationality = "England";

        Response response = get("teams/57");
        Map<String, ? > certainPlayers = response.path
                ("squad.findAll {it.position == '%s'}.find {it.nationality == '%s'}",position,nationality);
        System.out.println(certainPlayers);
    }
}