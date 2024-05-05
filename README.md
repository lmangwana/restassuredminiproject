# restassuredminiproject

This project validates the response status and headers of a REST API using RestAssured. It also includes tests to read and validate the JSON response body.

## Technologies Used

- TestNG
- Maven
- Java
- RestAssured

## Project Structure

### src/test/java

#### package testCases

- ValidateResponseStatus: This class contains test methods to validate the response status and headers of a REST API using RestAssured. It also includes tests to read and validate the JSON response body.

  Here’s a high-level description of the test:

  - **validateResponseStatus (@Test)**: This test method validates the status line of the response received from the server. It sends a GET request to the server and checks if the status code is 200.

  - **validateAllResponseHeader (@Test)**: This test method validates the headers of the response received from the server. It sends a GET request to the server and prints all the headers in the response.

  - **validateServerHeader (@Test)**: This test method validates a particular header in the response received from the server. It sends a GET request to the server and checks if the 'Server' header has the expected value.

  - **weatherMessageBody (@Test)**: This test method prints the response body of a GET request to a specific endpoint on the server. It sends a GET request to the '/Hyderabad' endpoint and prints the body of the response.

  - **extractCityAndTemperature (@Test)**: This test method extracts specific data from the JSON response body using JsonPath. It sends a GET request to the '/Hyderabad' endpoint, extracts the 'City' and 'Temperature' from the response body, and prints them.

## Getting Started

### Dependencies

- Java
- TestNG
- Maven
- RestAssured

### Installing

1. Clone the repo: `git clone https://github.com/lmangwana/restassuredminiproject`
2. Navigate to the project directory: `cd restassuredminiproject`
3. Install dependencies: `mvn install`

### Executing program

Run the program using `mvn test`

## Authors

lmangwana
