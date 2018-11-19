@Smoke
Feature: Verify Flight booking on Expedia.com to check


  @Oneway
  Scenario: OneWay booking
    Given User on Expedia page
    When User click on Flight tab
    And Click on OneWay Button
    Then Enters departing city "Stockholm" and arrival city "Paris"
    Then Pick the Departure Date
    Then select number of passengers
    And click on Submit Button
    Then Verify the number of products returned on search result page
    Then Verify the number of stops