Feature: Test of petnecessity

  Scenario Outline: Create an account
    Given I am at petnecessity page using "<browser>"
    When I create an account
    Then The account is successifully created
    Examples:
      | browser  |
      | chrome  |
      | firefox  |


  Scenario Outline: Login thru different Browsers
    Given
    When
    Then

    Examples:
      | test7@mailnesia.com |

