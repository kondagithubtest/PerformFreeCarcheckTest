Feature: Vehicle details check feature 

	@CarDetailsCheck
  Scenario Outline: Verify car details on cartaxchek website
    Given I have list of car registration numbers "<car_input_txt>"
    And I have list of expected car details "<car_output_txt>"
    When I check car details in the cartax check website
    Then I verified actual car details with expected details
    Examples:
      | car_input_txt                         | car_output_txt                         |
      | src/test/resources/data/car_input.txt | src/test/resources/data/car_output.txt |