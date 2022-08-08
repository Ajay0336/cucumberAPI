Feature: Go Rest Feature

  Background: Create a User
    Given  Creating a user

  @Smoke
  Scenario: Verify that a user is created
    Then the user is created

  @Smoke
  Scenario: Verify that a user is updated
    When updating a user
    Then the user is updated


  @regression
  Scenario: Verify that a user is deleted
    When deleting a user

  @regression
  Scenario Outline: Verify that a user resource not be updated
    When updating the user with invalid details "<userName>" and "<password>"
    Then the user not be updated
    Examples:
      | userName              |  | password |
      | aaaaaaaaaaa@gmail.com |  | aaaaaa   |
      | bbbbbbbbb             |  | Bbbb     |
      | ccccccccccc.com       |  | ccccccc  |
      | ddddddddd@.com        |  | ddddd    |

