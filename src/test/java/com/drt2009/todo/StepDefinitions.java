package com.drt2009.todo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import io.cucumber.java.en.*;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

public class StepDefinitions {

  private Response response;
  private int todoId;


  @Given("Service is up")
  public void service_is_up() {
  }
  @Given("There is a todo item already created")
  public void thereIsATodoItem() {
    response = iPostANewTodoItemWithADescriptionOf("PremadeTestTodoItem");
    todoId = response.getBody().jsonPath().getInt("id");
  }


  @When("I post a new todo item with a description of {string}")
  public Response iPostANewTodoItemWithADescriptionOf(String description) {
    String endpoint = "http://localhost:8080/todo";
      Map<String,String> body = Map.of("description", description);
     response = given().body(body).post(endpoint);
     return response;
  }
  @When("I request the item by the id")
  public void iRequestTheItemByTheId() {
    String endpoint = "http://localhost:8080/todo/"+todoId;
    response = given().post(endpoint);
  }

  @Then("a {int} is received")
  public void aIsReceived(Integer expectedStatusCode) {
      response.then().statusCode(expectedStatusCode);
  }
  @Then("the todo item is returned with an Id")
  public void theTodoItemIsReturnedWithAnId() {
    response.then().body("$", hasKey("id"));
  }
  @Then("the todo item is returned for it")
  public void theTodoItemIsReturnedForIt() {
    response.then().body("id", equalTo(todoId));
  }

  @Then("cleanup")
  public void cleanup(){
    response = null;
    todoId = 0;
  }


}
