Feature: Test of petnecessity

  Scenario Outline: Create an account with a long lastname
    Given I am at petnecessity page using "<browser>"
    When I create an account with "<email>"
    Then The account is successifully created
    And I recieve an email

    Examples:
      | browser  | email |
      | chrome  | tomas123@mailnesia.com |


  Scenario Outline: Error messages

      Then The "<errormessage>" is displayed

    Examples:
      | firstName   | lastname    | errormessage      |
      | Tomas       | ""          | lastname missing  |