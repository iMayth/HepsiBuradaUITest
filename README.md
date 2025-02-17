## **Introduction**:
This framework is designed to automate "HepsiBurada" web-based app testing using Java and Cucumber. 
It leverages the Behavior-Driven Development (BDD) approach, enabling easy collaboration between technical and non-technical stakeholders through feature files written in the Gherkin language.

## **Features**:
**Browser Support**: Configurable for different web browsers.

**BDD with Cucumber**: Write readable and maintainable test scenarios.

**Modular Architecture**: Organized with Page Object Model (POM) for reusable and scalable test design.

**Configurable Properties**: Centralized configuration management using config.properties.

**Pre- and Post-Execution Hooks**: Configure actions before and after tests.



## **Installation**:

1. Clone the Repository with this command.
   ```git clone https://github.com/iMayth/HepsiBuradaUITest.git```
  
2. Install Dependencies.
   Use Maven to install the necessary dependencies: ```mvn clean install```.


## **Project Structure**:
![image](https://github.com/user-attachments/assets/a947cb3f-7db7-43f1-8be7-3cd485841405)



## **Key Components**
## *1.Pages*
**BasePage**: Contains reusable methods like element interactions, scrolling, and waits.

**HomePage**:
Extend BasePage.
Contain locators and page-specific methods to interact with app elements.
Designed to encapsulate actions for their respective feature steps.

## *2.Step Definitions*
**Hooks**: Configures actions before and after execution, such as starting/stopping the driver and cleaning up test data.

**StepDefinitions**: Define Gherkin steps with Java code. Use page classes for implementing test actions.

## *3. Runners*
**Runner**: Configures the test execution with Cucumber options and JUnit annotations.

## *4.Utilities*
**Driver**: Configures and initializes web drivers. Dynamically decides the driver based on properties in config.properties.

**ConfigLoader**: Reads and provides values from config.properties. Centralizes configuration for device, platform, and other settings.

## *5. Features*
Located under src/test/resources/features.
Written in Gherkin syntax to describe test scenarios.

## *Running Tests*
1. **Via Maven**:
Execute all tests: ```mvn clean install```

2. **Run tests by tag**:
```mvn test -Dcucumber.filter.tags="@smokeTestLogin" ```

3. **Via Runner Class**:
Open Runner.java in your IDE. Right-click on the file and select Run 'Runner'.



