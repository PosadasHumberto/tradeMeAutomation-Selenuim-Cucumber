@TradeMe
Feature: TradeMe UI and API Tests

Rule: Write a nice Rule

Scenario: "As a user, I can see all the car makes on the Make dropdown"
  Given I navigate to the TradeMe Motor page
  Then I can verify that the number of car makes is 79

Scenario Outline: "As a user, I can validate that each mave has the right amount of cars listed"
  Given I navigate to the TradeMe Motor Page
  When I select the car make <Make>
  Then I can see it has <Amount> records in the results

    Examples:
      |Make     |Amount   |
      |Ferari   |47       |
      |BMW      |3390     |
      |Mazda    |5703     |
      |Honda    |2772     |

Scenario: "As a user, I wanto to verify the amount of car makes throught the API"
  Given I send the request to the endpoint
  Then I can see there are 78 car makes