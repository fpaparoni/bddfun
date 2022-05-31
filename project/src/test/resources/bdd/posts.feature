Feature: Posts

  Background: Author already saved
    Given an author with username 'federico' already saved
    And a post by author with username 'federico' already saved
    
  Scenario: 001 - Save post OK
    Given the title 'Hello world'
    And the body 'Great article'
    And the author with username 'federico'
    When I submit this post to save it
    Then I receive a correct response

  Scenario: 002 - Save post KO
    Given the title 'Hello world'
    And the author with username 'federico'
    When I submit this post to save it
    Then I receive an error
    
  Scenario: 003 - Get post by author
    When I read the posts by author 'federico'
    Then I receive a correct response
    And all the posts are by 'federico'
    