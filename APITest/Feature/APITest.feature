Feature: API Test
  This Feature is to test an API

  Scenario: Validate Failed Login
    Given MobileNumber: 114044885367
    And Date of Birth: 01-01-1979
    And Postal Code: EC88AA
    And Reference Number: 9090801
    When a request to an API is made
    Then failed Login should be returned

  Scenario: Validate Not all answer Login
    Given MobileNumber: 114044885347
    And Date of Birth: 01-01-1971  
    And Postal Code: 
    And Reference Number: 9090808
    When a request to an API is made
    Then error Login should be returned

  Scenario: Validate Success Login
    Given MobileNumber: 114044885347
    And Date of Birth: 01-01-1971
    And Postal Code: EC88AB
    And Reference Number: 9090808
    When a request to an API is made
    Then successful Login should be returned 