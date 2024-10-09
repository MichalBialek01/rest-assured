# REST Assured Test Descriptions

This document provides an overview of the REST Assured tests found in the project, including descriptions of the methods used in the test classes.

## FootballTest.java

The `FootballTest` class contains several test cases that interact with the football-related API. Below are the details of each test and the REST Assured methods used:

### 1. `getAllAreas()`
- **Description**: Retrieves all areas from the `/areas` endpoint.
- **HTTP Method**: `GET`
- **Endpoint**: `/areas`
- **REST Assured Methods**:
  - `given()`: Prepares the request specification (e.g., headers, query parameters).
  - `when()`: Executes the HTTP GET request.
  - `then()`: Verifies the response, though no assertions are applied here.

### 2. `getDetailsOfOneArea()`
- **Description**: Retrieves details of a specific area using the `areas` query parameter.
- **HTTP Method**: `GET`
- **Endpoint**: `/areas?areas=20`
- **REST Assured Methods**:
  - `given()`: Prepares the request specification with a query parameter.
  - `when()`: Executes the HTTP GET request.
  - `then()`: Allows for the validation of the response (no specific assertions provided).

### 3. `getMultipleArenasDetails()`
- **Description**: Retrieves details for multiple areas by providing a comma-separated list of area IDs.
- **HTTP Method**: `GET`
- **Endpoint**: `/areas?areas=2000,2001,2003`
- **REST Assured Methods**:
  - `given()`: Prepares the request with multiple query parameters.
  - `when()`: Executes the HTTP GET request.
  - `then()`: Allows for response validation (no specific assertions provided).

### 4. `getDateFounded()`
- **Description**: Retrieves the founding date of a team using a path parameter and validates it against the expected value.
- **HTTP Method**: `GET`
- **Endpoint**: `/teams/{id}`
- **REST Assured Methods**:
  - `pathParam()`: Inserts a path parameter (team ID).
  - `body()`: Asserts that the `founded` field matches the expected value.

### 5. `getFirstTeamName()`
- **Description**: Retrieves the name of the first team from a list of teams and validates it against the expected value.
- **HTTP Method**: `GET`
- **Endpoint**: `/competitions/{id}/teams`
- **REST Assured Methods**:
  - `pathParam()`: Inserts the competition ID as a path parameter.
  - `body()`: Asserts that the name of the first team matches "Arsenal FC".

### 6. `getAllTeamDataOnly()`
- **Description**: Retrieves all data for a specific team and prints it as a string.
- **HTTP Method**: `GET`
- **Endpoint**: `/teams/57`
- **REST Assured Methods**:
  - `get()`: Executes the GET request and converts the response to a string.

### 7. `getAllTeamData()`
- **Description**: Retrieves all data for a specific team and extracts it as a string.
- **HTTP Method**: `GET`
- **Endpoint**: `/teams/{id}`
- **REST Assured Methods**:
  - `pathParam()`: Inserts the team ID as a path parameter.
  - `contentType()`: Ensures the response is in JSON format.
  - `extract()`: Extracts the response and converts it to a string.

### 8. `extractHeaders()`
- **Description**: Extracts and prints all headers from the response, including specific headers like `Content-Type` and `X-API-Version`.
- **HTTP Method**: `GET`
- **Endpoint**: `/teams/57`
- **REST Assured Methods**:
  - `extract()`: Extracts the response and retrieves headers.

### 9. `extractFirstTeamName()`
- **Description**: Extracts the name of the first team from a list of teams and prints it.
- **HTTP Method**: `GET`
- **Endpoint**: `/competitions/2021/teams`
- **REST Assured Methods**:
  - `jsonPath()`: Extracts the value of the `teams.name[0]` field using GPath.

### 10. `extractAllTeamNames()`
- **Description**: Extracts and prints the names of all teams from a list.
- **HTTP Method**: `GET`
- **Endpoint**: `/competitions/2021/teams`
- **REST Assured Methods**:
  - `path()`: Extracts the list of team names from the response.

## VideoGameTests.java

The `VideoGameTests` class contains tests for interacting with the video game API. Below are the details of the test cases and the REST Assured methods used:

### 1. `getAllGames()`
- **Description**: Retrieves all video games from the API.
- **HTTP Method**: `GET`
- **Endpoint**: `/videogames`
- **REST Assured Methods**:
  - `given()`: Prepares the request.
  - `get()`: Sends a GET request to the `ALL_VIDEO_GAMES` endpoint.

### 2. `creteNewGameByJSON()`
- **Description**: Creates a new video game by sending JSON data.
- **HTTP Method**: `POST`
- **Endpoint**: `/videogames`
- **REST Assured Methods**:
  - `body()`: Sets the request body with the provided JSON.
  - `post()`: Sends a POST request to create a new game.

### 3. `updateGame()`
- **Description**: Updates an existing video game using a PUT request.
- **HTTP Method**: `PUT`
- **Endpoint**: `/videogame/3`
- **REST Assured Methods**:
  - `put()`: Sends a PUT request to update the game data.

### 4. `deleteGame()`
- **Description**: Deletes a video game using a DELETE request.
- **HTTP Method**: `DELETE`
- **Endpoint**: `/videogame/8`
- **REST Assured Methods**:
  - `delete()`: Sends a DELETE request to remove the game.

### 5. `getGame()`
- **Description**: Retrieves the details of a specific video game by its ID.
- **HTTP Method**: `GET`
- **Endpoint**: `/videogame/{videoGameId}`
- **REST Assured Methods**:
  - `pathParam()`: Inserts the video game ID as a path parameter.

### 6. `testVideoGameSerializationByJson()`
- **Description**: Serializes a `VideoGame` object into JSON and sends it as a POST request.
- **HTTP Method**: `POST`
- **Endpoint**: `/videogames`
- **REST Assured Methods**:
  - `body()`: Serializes the `VideoGame` object and sends it in the request body.

### 7. `testVideoGameXsdSchema()`
- **Description**: Validates the response against an XML schema.
- **HTTP Method**: `GET`
- **Endpoint**: `/videogame/{videoGameId}`
- **REST Assured Methods**:
  - `matchesXsdInClasspath()`: Validates the XML response against the provided XSD schema.

### 8. `testVideoGameJsonSchema()`
- **Description**: Validates the response against a JSON schema.
- **HTTP Method**: `GET`
- **Endpoint**: `/videogame/{videoGameId}`
- **REST Assured Methods**:
  - `matchesJsonSchemaInClasspath()`: Validates the JSON response against the provided JSON schema.

### 9. `convertJsonToPojo()`
- **Description**: Converts the JSON response of a video game into a `VideoGame` POJO (Plain Old Java Object).
- **HTTP Method**: `GET`
- **Endpoint**: `/videogame/{videoGameId}`
- **REST Assured Methods**:
  - `as()`: Converts the response body into a `VideoGame` object.

### 10. `captureResponseTime()`
- **Description**: Captures and prints the response time for retrieving all video games.
- **REST Assured Methods**:
  - `time()`: Captures the response time.

### 11. `asserOnResponseTime()`
- **Description**: Asserts that the response time is less than 2000 milliseconds.
- **REST Assured Methods**:
  - `time()`: Asserts that the response time is within the expected threshold.

## GpathJsonTest.java
The `GpathJsonTest` class demonstrates how to use GPath to extract data from JSON responses in a football-related API. Each method showcases different techniques for filtering and selecting data using GPath expressions.

## Methods Overview

### 1. `extractElementsWithFind()`
- **Description**: Extracts all data for a specific team ("Arsenal FC") using the `find` function in GPath.
- **HTTP Method**: `GET`
- **Endpoint**: `/competitions/2021/teams`
- **GPath Expression**: `teams.find {it.name == 'Arsenal FC'}`
- **Response Handling**: 
  - The method retrieves the data for the team with the name "Arsenal FC" and prints the full data as a map.

---

### 2. `extractSingleField()`
- **Description**: Extracts the name of a player from a team's squad using the player's ID.
- **HTTP Method**: `GET`
- **Endpoint**: `/teams/57`
- **GPath Expression**: `squad.find {it.id == 175905}.name`
- **Response Handling**: 
  - This method retrieves the name of the player whose ID is `175905` and prints it.

---

### 3. `getListOfPlayers()`
- **Description**: Extracts and prints the names of all players whose ID is greater than a specified value.
- **HTTP Method**: `GET`
- **Endpoint**: `/teams/57`
- **GPath Expression**: `squad.findAll {it.id > 175905}.name`
- **Response Handling**: 
  - This method retrieves a list of players whose ID is greater than `175905` and prints their names.

---

### 4. `getByHighestPlayerId()`
- **Description**: Retrieves the name of the player with the highest ID in the squad.
- **HTTP Method**: `GET`
- **Endpoint**: `/teams/57`
- **GPath Expression**: `squad.max {it.id}.name`
- **Response Handling**: 
  - This method finds the player with the highest ID and prints their name.

---

### 5. `getByLowestPlayerId()`
- **Description**: Retrieves the name of the player with the lowest ID in the squad.
- **HTTP Method**: `GET`
- **Endpoint**: `/teams/57`
- **GPath Expression**: `squad.min {it.id}.name`
- **Response Handling**: 
  - This method finds the player with the lowest ID and prints their name.

---

### 6. `multipleParameter()`
- **Description**: Extracts a player based on two criteria: position and nationality.
- **HTTP Method**: `GET`
- **Endpoint**: `/teams/57`
- **GPath Expression**: 
  - `squad.findAll {it.position == 'Goalkeeper'}.find {it.nationality == 'England'}`
- **Response Handling**: 
- This method filters the squad for players in the "Goalkeeper" position and with the nationality "England", then prints the filtered player's data.
 
## Resources
[REST Assured - Getting started docs](https://github.com/rest-assured/rest-assured/wiki/gettingstarted)

[REST Assured Fundamentals](https://www.udemy.com/course/rest-assured-fundamentals/?couponCode=PLOYALTY0923)
