package com.drt2009.todo;

import static io.restassured.RestAssured.given;

import io.cucumber.java.en.*;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

public class StepDefinitions {

    private RequestSpecification request;
    private Response response;


  @Given("Service is up")
    public void service_is_up() {
    }

    @When("I post a new todo item with a description of {string}")
    public void iPostANewTodoItemWithADescriptionOf(String description) {
      String endpoint = "http://localhost:8080/todo";
        Map<String,String> body = Map.of("description", description);
        request = given().body(body);
        response = request.post(endpoint);

    }
    @Then("a {int} is received")
    public void aIsReceived(Integer expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

}
