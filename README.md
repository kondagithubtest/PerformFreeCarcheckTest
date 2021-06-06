# Perform Free Car Check Test

## Prerequisites


* Java 1.8
* maven 3.8.1
* Cucumber JVM
* Selenium Webdriver
* opencsv
* Browser- chrome- verion- 91.0.4472.77
* OS- Windows 10 64bit




###  Solution Design

* Input text file contains the vehicle registration numbers with other text content
* Output text file contains vehicle details and these details are passed through cucumber scenario outline.

* Input text file is parsed and extracted vehicle registration numbers
* looped through each registration number
* Verified vehicle details on cartaxcheck.co.uk web site
* Actual values are verified with the expected values which are present in the car_output.txt
* Solution is designed in such a way that in future multiple input and output files can be given in the scenario outline and * * the logic works without any change


### Command to execute the automation from command line

`mvn clean test`

### Reports location
* Reports will be placed under target/site/cucumber-pretty/index.html


## Evidences of the report

* A sample video of execution is there under the evidences folder