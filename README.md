# RestAPITesting
![image](https://github.com/amarCausevic/RestAPITesting/assets/37142287/9d875b8e-fa55-462c-b234-597d6ad8cfe7)

![image](https://github.com/amarCausevic/RestAPITesting/assets/37142287/7d5f13ed-12b2-48e6-bd1d-e1e1125a9d4e)

![image](https://github.com/amarCausevic/RestAPITesting/assets/37142287/1ebdf581-ab0a-44fb-ba0e-f78674dd96b7)

## For whom?
This reposatory was created for getting basic concept of Rest API testing with RestAssured and Cucumber/Gherkin Syntax frameworks in Java. 

## Concept?
Concept of this repository is based on https://restful-booker.herokuapp.com/apidoc/index.html#api-Booking-UpdateBooking page, where API for booking is exposed. It is true that this is the most basic endpoints exposed to the public but at the end of the day, someone can grab and learn concept of API testing with provided website.

## Structure?
```
SRC
  main
  test
    java
      org.booking
        ACTIONS (Which contains DAO DATA-ACTIONS-OBJECTS with all actions supporting SPEC file, lets say that this is something like controller)
        ENUMS
        FEATURES (Containing all test case scenarios using Gherking Syntax)
        MODEL (All DTO that are being used to SERIALIZE OR DESRIALIZE during testing)
        OPTIONS (TestRunner class which has different cucumber optiones defined for running tests)
        SPECS (Containing all steps which will call all DAO classes)
      utils
        ACTIONS (Contaning all custom and generalized method used accross projects)
        ENUMS
        MODEL (Containg DTOs which are used accross projects)
```
##Run tests
```
mvn clean install
//Whole test suite
or
mvn test
//Whole test suite
or
//Specific feature file
mvn test -Dcucumber.options="src/test/java/org/booking/features/Login.feature"
```
![image](https://github.com/amarCausevic/RestAPITesting/assets/37142287/808add30-788c-403e-ae9a-981a28d91eb5)

## Cucumber Reports
In this project CUCUMBER REPORTS is also included in the properties file inside TEST package to run this, you will need a dependency called Cucumber JVM, and simply execute all test in terminal as mvn test, CUCUMBER_PUBLISH_TOKEN=, also must defind correct collection created on CUCUMBER REPOTRTS web site, runner will automatically send all information to its server and automate a report for user for that specific run!

![image](https://github.com/amarCausevic/RestAPITesting/assets/37142287/a4f2f359-a13c-4693-a7b2-322887751460)

## General class structure
SPEC CLASS -> EXTENDS -> DAO CLASS -> EXTENDS -> REST BUILDER CLASS

In that sense REST BUILDER CLASS is "SUPER" class. Currently I see no need to implement interfaces but I am going to introduce this into next interation!

## Test runner options
```
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/org/booking/features",
    glue = "org/booking/specs",
    plugin = {"pretty", "html:target/html-reports"},
    stepNotifications = true)
public class TestRunner {

}
```
> plugin = {"pretty", "html:target/html-reports"} -> This will execute and create and HTML report into src/target folder

> stepNotifications = true -> This will trigger that notification and spec steps are displayed in the runner

## TODO
- Implement better logging
- Implement refactoring for @Before hook
- Implement CI/CD if you see its nesseccery
- Implement better structure to use interface in some cases
- Check all TODOs and resolve the potential issue
- Create a scheduled pipeline where tests are executed daily
- Check if only one feature file is better to have reather then multiple since all cases can be coverd in on feature file

