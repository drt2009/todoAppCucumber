Feature: Todo Item

  Scenario: Create a valid todo item
    Given Service is up
    When I post a new todo item with a description of "todo item"
    Then a 201 is received
    And the todo item is returned with an Id

  Scenario:
    Given There is a todo item already created
    When I request the item by the id
    Then a 200 is recieved
    And the todo item is returned for it