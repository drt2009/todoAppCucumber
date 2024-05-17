Feature: Todo Item

  Scenario: Call the hello world
    Given Service is up
    When I post a new todo item with a description of "todo item"
    Then a 201 is received