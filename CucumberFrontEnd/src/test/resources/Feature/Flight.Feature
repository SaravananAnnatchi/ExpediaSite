@Smoke
Feature: Verify Flight booking on Expedia.com

  Scenario: Roundtrip booking
    Given User on Expedia page
    When User click on Flight tab
    Then Enters departing city "Stockholm" and arrival city "Paris"
    Then Pick the Departure Date and Return Date
    Then select number of passengers
    And click on Submit Button
    Then Verify the number of products returned on search result page
    Then Verify the number of stops

  @Roundtrip@Oneway
  Scenario: Result page sorting
    Given User on Expedia page
    When User click on Flight tab
    Then Enters departing city "Stockholm" and arrival city "Paris"
    Then Pick the Departure Date and Return Date
    Then select number of passengers
    And click on Submit Button
    Then Verify the results sorted by "Price (Lowest)"
    Then select the price sorting by "Price (Highest)" in the dropdown
    Then Verify the results sorted by "Price (Highest)"

  @Roundtrip
  Scenario: select flights
    Given User on Expedia page
    When User click on Flight tab
    Then Enters departing city "Stockholm" and arrival city "Paris"
    Then Pick the Departure Date and Return Date
    Then select number of passengers
    And click on Submit Button
    Then Select the flights

  @Roundtrip
  Scenario Outline: Verify Checkout page
    Given User on Expedia page
    When User click on Flight tab
    Then Enters departing city "Stockholm" and arrival city "Paris"
    Then Pick the Departure Date and Return Date
    Then select number of passengers
    And click on Submit Button
    Then Select the flights
    And Navigate to check out page
    And Select the <Title> and Enter the <Firstname> and <Lastname>
    And Select <Country> and enter <PhoneNumber>
    And Uncheck the alert checkbox and Select the <Passport> and click on more options

    Examples:
      |Title    | Firstname | Lastname  | Country   | PhoneNumber | Passport  |
      |Mr.      |Saravanan  | Annatchi  | Sweden +46| 707930179   |J6635672   |

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
