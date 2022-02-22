# je-t api

## Tech info
Restassured, JUnit 5, Gradle\
Requires [Java 11](https://openjdk.java.net/install/), (OpenJDK is fine)

## Installation
Clone and change directory to the project:
```
git clone git@github.com:andrei-z/je-t_api.git
cd je-t_api
```
The tests are located at `src/test/java`

## Running tests
To run the tests execute from root level:
```
je-t_api % ./gradlew clean test
```

## Reports
Once the test run is completed, gradle html report can be found at:
```
je-t_api/build/reports/tests/test/index.html
```