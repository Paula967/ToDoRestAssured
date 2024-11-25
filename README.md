To-Do API Automation Project
This project automates testing of a To-Do API using RestAssured in Java. It utilizes IntelliJ IDEA as the IDE, TestNG for test execution, Maven Surefire plugin for build management, and Allure for generating reports.

Prerequisites
Java JDK 8+ installed and configured in the environment.
IntelliJ IDEA with Maven support.
Maven installed and configured.
Allure CLI installed for report generation.
Basic knowledge of RESTful APIs, Postman, and Java.
Setup Instructions
1. Clone the Repository
bash
Copy code
git clone <repository-url>
cd todo-api-automation
2. Configure the pom.xml
Add the required dependencies:

xml
Copy code
<dependencies>
    <!-- RestAssured Dependency -->
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>5.3.0</version>
    </dependency>

    <!-- TestNG Dependency -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.8.0</version>
        <scope>test</scope>
    </dependency>

    <!-- Allure TestNG Adapter -->
    <dependency>
        <groupId>io.qameta.allure</groupId>
        <artifactId>allure-testng</artifactId>
        <version>2.21.0</version>
    </dependency>
</dependencies>
Add the Surefire plugin to execute TestNG tests:

xml
Copy code
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.1.2</version>
            <configuration>
                <suiteXmlFiles>
                    <suiteXmlFile>testng.xml</suiteXmlFile>
                </suiteXmlFiles>
            </configuration>
        </plugin>
    </plugins>
</build>
3. Create the Test Suite (testng.xml)
Define the test classes and methods in the testng.xml file:

xml
Copy code
<suite name="ToDoAPITestSuite">
    <test name="API Tests">
        <classes>
            <class name="tests.CreateTaskTest" />
            <class name="tests.GetTaskTest" />
            <class name="tests.UpdateTaskTest" />
            <class name="tests.DeleteTaskTest" />
        </classes>
    </test>
</suite>
4. Create the Test Classes
Follow the structure:

src/test/java
tests
CreateTaskTest.java
GetTaskTest.java
UpdateTaskTest.java
DeleteTaskTest.java
utils
BaseTest.java
Example for CreateTaskTest.java:

java
Copy code
package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateTaskTest {
    @Test
    public void testCreateTask() {
        Response response = RestAssured.given()
                .baseUri("https://api.todo.com")
                .header("Authorization", "Bearer your_access_token")
                .header("Content-Type", "application/json")
                .body("{ \"title\": \"Sample Task\", \"dueDate\": \"2024-11-30\" }")
                .post("/tasks");

        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertNotNull(response.jsonPath().getString("id"));
    }
}
5. Execute Tests
Run the tests using Maven:

bash
Copy code
mvn clean test
6. Generate Allure Report
Run the following command to generate the Allure results:
bash
Copy code
allure serve target/allure-results
The report will open in your default browser.
Features Covered
Create Task: Adds a new task to the To-Do API.
Get Task: Fetches details of a specific task.
Update Task: Modifies details of an existing task.
Delete Task: Removes a task from the system.
Future Enhancements
Add test cases for edge cases and validations.
Integrate with CI/CD pipelines for continuous testing.
Enhance logging and error handling.
