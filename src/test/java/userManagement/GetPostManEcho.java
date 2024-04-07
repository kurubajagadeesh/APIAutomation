package userManagement;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import core.BaseTest;
import core.StatusCode;
import io.restassured.response.Response;
import utils.ExtentReport;
import utils.JsonReader;

public class GetPostManEcho extends BaseTest{
	 @Test(groups = "SmokeSuite")
	 public void validateWithTestDataFromJson() throws IOException, ParseException {
		String username = JsonReader.getTestData("username");
		String password = JsonReader.getTestData("password");
		Response res = given()
			.auth() 
			.basic(username, password)
			.when()
			.get("https://postman-echo.com/basic-auth");
			 int activalStausCode = res.getStatusCode();
			 assertEquals(activalStausCode,200);
	 }
	 @Test(groups="RegressionSuite")
	 public void testValidateDigestAuth() {
		 ExtentReport.extentlog=ExtentReport.extentreport.startTest("testValidateDigestAuth", "user should login and status code is 200");
		Response response = given()
		 		.auth()
		 		.digest("postman", "password")
		 	.when()
		 		.get("https://postman-echo.com/digest-auth");
		int activalStatusCode = response.statusCode();
		assertEquals(activalStatusCode,200);
		System.out.println(response.body().asString());
	 }
	 @Test(groups= {"SmokeSuite","RegressionSuite"})
	 public void testValidateBasicAuth() {
		 ExtentReport.extentlog=ExtentReport.extentreport.startTest("testValidateBasicAuth", "user should login and status code is 200");
		Response response = given()
		 		.auth()
		 		.basic("postman", "password")
		 	.when()
		 		.get("https://postman-echo.com/basic-auth");
		int activalStatusCode = response.statusCode();
		assertEquals(activalStatusCode,StatusCode.SUCCESS.code);
		System.out.println(response.body().asString());
	 }

}
