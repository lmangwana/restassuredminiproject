package TestClasses;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

/**
 * This class contains test methods to validate the response status and headers
 * of a REST API using RestAssured. It also includes tests to read and validate
 * the JSON response body.
 */

public class ValidateResponseStatus {

	/**
	 * This test method validates the status line of the response received from the
	 * server. It sends a GET request to the server and checks if the status code is
	 * 200.
	 */

	@Test(enabled = false)
	public void validateResponseStatus() {
		RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
		// Get the RequestSpecification of the request to be sent to the server.
		RequestSpecification httpRequest = RestAssured.given();

		// Call RequestSpecification.get() method to get the response.
		// Make sure you specify the resource name.
		Response response = httpRequest.get("");

		// Print the whole status line of the response received from the server
		System.out.println("Status received => " + response.getStatusLine());
		// System.out.println("Response=>" + response.prettyPrint());

		// Gets the status code from the status line and stores it in the statusCode
		// variable
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	/**
	 * This test method validates the headers of the response received from the
	 * server. It sends a GET request to the server and prints all the headers in
	 * the response.
	 */

	@Test(enabled = false)
	public void validateAllResponseHeader() {
		RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("");

		Headers allHeaders = response.headers();

		// Headers allHeaders2 = response.getHeaders();

		// let's print the JSON object first using toString()
		System.out.println("Header object: \n" + allHeaders.toString());

	}

	/**
	 * This test method validates a particular header in the response received from
	 * the server. It sends a GET request to the server and checks if the 'Server'
	 * header has the expected value.
	 */

	@Test(enabled = false)
	public void validateServerHeader() {
		RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("");

		String server = response.header("Server");
		System.out.println("Returned Server value =>" + server);

		Assert.assertEquals(server /* actual value */, "nginx/1.17.10 (Ubuntu)" /* expected value */);

	}

	/**
	 * This test method prints the response body of a GET request to a specific
	 * endpoint on the server. It sends a GET request to the '/Hyderabad' endpoint
	 * and prints the body of the response.
	 */

	@Test(enabled = true)
	public void weatherMessageBody() {
		RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");

		ResponseBody responseBody = response.getBody();

		System.out.println("Response body line => " + responseBody.asString());

		// System.out.println("Response body using pretty print =>
		// "+response.prettyPrint());
	}

	/**
	 * This test method extracts specific data from the JSON response body using
	 * JsonPath. It sends a GET request to the '/Hyderabad' endpoint, extracts the
	 * 'City' and 'Temperature' from the response body, and prints them.
	 */

	@Test(enabled = false)
	public void extractCityAndTemperature() {
		RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");

		ResponseBody responseBody = response.getBody();

		JsonPath jsonPathObject = responseBody.jsonPath();
		String city = jsonPathObject.get("City");
		String temperature = jsonPathObject.get("Temperature");

		System.out.println("The weather in " + city + ", currently is " + temperature + ".");
	}
}
