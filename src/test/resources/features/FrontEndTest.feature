Feature: FrontEnd Test

  Scenario Outline: Open and run app

    Given User is logged in

    Then Add new user with Username as "<userFirstName>"

    And Edit user

    And Show user details

    Then Delete user

    Then User is logged out

    Examples:
      |userFirstName|
      |Calin12|

