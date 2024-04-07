package userManagement;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

import org.testng.annotations.Test;

import core.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ExtentReport;

public class GetJsonPlaceHolder extends BaseTest {
	@Test(groups="SmokeSuite")
	public void validateGetResponseBody() {
		ExtentReport.extentlog=ExtentReport.extentreport.startTest("validateGetResponseBody","response body should displayed and status code is 200");
		// Set base URI for the API
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		given()

				.when().get("/todos/1").then().assertThat().statusCode(200).body(not(isEmptyString()))
				.body("title", equalTo("delectus aut autem")).body("userId", equalTo(1));

	}
	@Test(groups ="RegressionSuite" )
	public void validateResponseHasItems() {
		ExtentReport.extentlog=ExtentReport.extentreport.startTest("validateResponseHasItems","response body should displayed and status code is 200");
		// Set base URI for the API
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		Response response = given()
								.when()
								.get("/posts");
				//			.then()
				//			.extract()
				//			.response();
		// Use Hamcrest to check that the response body contains specific items

		assertThat(response.jsonPath().getList("title"),
				hasItems("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "qui est esse"));

		// assertEquals(response.jsonPath().getList("title"),hasItems("sunt aut facere
		// repellat", "qui est esse"));

		// Validate that the response body is not empty
		assertThat(response.getBody().asString(), not(isEmptyString()));

		// Validate that the response contains a specific value
		assertThat(response.getBody().asString(), containsString("qui est esse"));

		// Validate that the response has a specific JSON attribute
		assertThat(response.getBody().jsonPath().get("[1].title"), equalTo("qui est esse"));

		// Validate that the response has a specific XML element
		
	//	assertThat(response.getBody().xmlPath().get("title"), equalTo("qui est esse"));
	}

}
