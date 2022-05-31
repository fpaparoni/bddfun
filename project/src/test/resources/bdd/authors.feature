Feature: Authors

  Scenario: 001 - Save author OK
    Given a username 'federico'
    And an email 'federico.paparoni@xyz.mail'
    And a bio 'Federico Paparoni wants to write something about Cucumber & Testcontainer'
    When I submit this information to save a new user
    Then I receive a correct response

  Scenario: 002 - Save author without username KO
    Given an email 'federico.paparoni@xyz.mail'
    And a bio 'Federico Paparoni wants to write something about Cucumber & Testcontainer'
    When I submit this information to save a new user
    Then I receive an error
    
  Scenario: 003 - Get author KO
    Given a system without the author with username 'goofy'
    When I read the author with username 'goofy'
    Then I receive an error
    
  Scenario: 004 - Get author OK
    Given a system with the author with username 'pluto'
    When I read the author with username 'pluto'
    Then I receive a correct response