package userManagement;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import core.BaseTest;
import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import pojo.PostRequestBody;
import utils.ExtentReport;
import utils.JsonReader;
import utils.PropertyReader;
import utils.SoftAssertionUtil;

public class GetUsers extends BaseTest {
	SoftAssertionUtil softAssertion=new SoftAssertionUtil();

	@Test(description="validate status code",groups = "SmokeSuite")
	public void getUserData() {
		ExtentReport.extentlog=ExtentReport.extentreport.startTest("validateResponseHasItems","response body should displayed and status code is 200");
		given()

				.when().get("https://reqres.in/api/users?page=2")

				.then().assertThat().statusCode(200);
	}

	 

	@Test(groups="RegressionSuite")
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
	
	@Test
	public void valiadateResponseHasSize() {
		 // Set base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send a GET request and store the response in a variable
       Response response = given()
        	.when()
        	.get("/comments");
        	 
     // Use Hamcrest to check that the response body has a specific size
        assertThat(response.getBody().jsonPath().getList(""), hasSize(500));

	}
	@Test
	public void valiadateListHasSize() {
		  // Set base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send a GET request and store the response in a variable
        Response response = given()
        	.when()
        	.get("/todos");
        // Use Hamcrest to check that the response body is a list of a specific size
        assertThat(response.jsonPath().getList(""),hasSize(200));
	}
	@Test(groups= {"SmokeSuite","RegressionSuite"})
	public void valiadateListContainsItems() {
		ExtentReport.extentlog=ExtentReport.extentreport.startTest("valiadateListContainsItems","status code is 200");
		// Set base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send a GET request and store the response in a variable
        Response response = given()
        	          			.when()
        	          			.get("/users") 
        	          			.then()
        	          			.extract()
        	          			.response();
        // Use Hamcrest to check that the response body contains specific items
        List<String> expectedNames = Arrays.asList("Leanne Graham", "Ervin Howell", "Clementine Bauch", "Patricia Lebsack"
        		, "Chelsey Dietrich", "Mrs. Dennis Schulist", "Kurtis Weissnat", "Nicholas Runolfsdottir V"
        		, "Glenna Reichert", "Clementina DuBuque]");
         System.out.println(response.jsonPath().getList("name"));
       // assertThat(response.jsonPath().getList("name"),hasItems(contains(expectedNames.toArray(new String[0]))));
	}
	@Test
	public void validateListContainsInOrder() {
		  // Set base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send a GET request and store the response in a variable
        Response response = given()
                .when()
                .get("/comments?postId=1")
                .then()
                .extract()
                .response();

        // Use Hamcrest to check that the response body contains specific items in a specific order
        List<String> expectedEmails = Arrays.asList("Eliseo@gardner.biz", "Jayne_Kuhic@sydney.com", "Nikita@garfield.biz","Lew@alysha.tv","Hayden@althea.biz");
        assertThat(response.jsonPath().getList("email"), contains(expectedEmails.toArray(new String[0])));

	}
	@Test
	public void testGetUsersWithQueryParamaters() {
		RestAssured.baseURI="https://reqres.in/api";
		Response response = given()
		.queryParam("page",2)
		.when()
		.get("/users")
		.then()
		.statusCode(200)
		.extract()
        .response();
		// Assert that the response contains 6 users
		 response.then().body("data",hasSize(6));
		 assertThat(response.jsonPath().get("data"),hasSize(6));
		// Assert that the first user in the list has the correct values
		   int StatusCode = response.statusCode();
		 assertEquals(StatusCode ,200);
		 response.then().body("data[0].id", is(7));
		 response.then().body("data[0].email", equalTo("michael.lawson@reqres.in"));
		 response.then().body("data[0].first_name", is("Michael"));
		 response.then().body("data[0].last_name", equalTo("Lawson"));
		 response.then().body("data[0].avatar", equalTo("https://reqres.in/img/faces/7-image.jpg"));
	}
	@Test
	public void testGetUsersWithMultipleQuaryParms() {
		Response res = given()
			.queryParam("page", 2)
			.queryParams("per_page",3)
			.queryParam("rtqsdr", 4)
			.when()
			.get("https://reqres.in/api/users")
			.then()
			.statusCode(200)
			.extract()
			.response();
			 
			 
	}
	 
	@Test
	public void testGetUserListWithHeaders() {
		given()
				.header("Content-Type","application/json")
				.when()
				.get("https://reqres.in/api/users?page=2")
				.then()
				.statusCode(200);
				
		
		
	}
	@Test
	public void testWithTwoHeaders() {
	   given()
	           .header("Authorization", "bearer ywtefdu13tx4fdub1t3ygdxuy3gnx1iuwdheni1u3y4gfuy1t3bx")
	           .header("Content-Type", "application/json")
	           .when()
	           .get("https://reqres.in/api/users?page=2")
	           .then()
	           .statusCode(200);
	   System.out.println("testWithTwoHeaders Executed Successfully");
	}
	
	 @Test
	    public void testWithMapHeaders() {
	        // Set base URI for the API
	        RestAssured.baseURI = "https://reqres.in/api";

	        // Create a Map to hold headers
	        Map<String, String> headers = new HashMap<>();
	        headers.put("Content-Type", "application/json");
	        headers.put("Authorization", "Bearer <your_token_here>");

	        // Send a GET request with headers
	        given()
	                .headers(headers)
	                .when()
	                .get("/users?page=2")
	                .then()
	                .statusCode(200)
	                .body("page", equalTo(2))
	                .body("data[0].first_name", equalTo("Michael"))
	                .body("data[0].last_name", equalTo("Lawson"));
	    }
	 @Test
	 public void testResponseHeaders() {
		    // Set base URI for the API
	        RestAssured.baseURI = "https://reqres.in/api";
	       Response response = given()
	        		.when()
	        		.get("/users?page=2")
	        		.then()
	        		.extract()
	        		.response();
	       Headers headers = response.getHeaders();
	       for(Header h:headers) {
	    	   System.out.println(h.getName()+" : "+h.getValue());
	       }
	 }
	 @Test
	 public void testUseCookies() {
		 RestAssured.baseURI = "https://reqres.in/api";
		 Cookie cookies=new Cookie.Builder("cookieKey1", "cookieValue1")
				 .setComment("usecookie")
				 .build();
		 given()
		 		.cookie(cookies)
		 		.when()
		 		.get("/users?page=2")
		 		.then()
		 		.statusCode(200);
				 
	 }
	 @Test
	    public void testFetchHeaders() {
	        given()
	            .when()
	                .get("https://reqres.in/api/users?page=2")
	            .then()
	                .assertThat()
	                    .statusCode(200)
	                    .header("Content-Type", equalTo("application/json; charset=utf-8"))
	                    .header("Cache-Control", equalTo("max-age=14400"))
	                    .headers("Server", "cloudflare", "CF-RAY", notNullValue())
	                    .log().all();
	    }
	 @Test
	 public void testFetchCookies() {
		Response res = given()
		 	.when()
		 		.get("https://reqres.in/api/users?page=2")
		 		.then()
		 			.extract()
		 			.response();
		Map<String, String> cookies = res.getCookies();
		Cookies cookies1 = res.getDetailedCookies();
		cookies1.getValue("server");
//		   assertEquals(cookies1.getValue("server"), "cloudflare");
//		   assertThat(cookies, hasKey("JSESSIONID"));
//		   assertThat(cookies, hasValue("ABCDEF123456"));
	 }
	  
	 
	 @Test
	 public void verifyStatusCodeDelete() {


		  Response resp = given().delete("https://reqres.in/api/users/2");
		  assertEquals(resp.getStatusCode(),StatusCode.NO_CONTENT.code);
		  
	 }
	  
	 @Test
		public void valiadateWithTestDataFromPropertiesFile() {
		 String serverAddress=PropertyReader.propertyReader("config.properties", "serverAddress");
			Response res = given()
					.queryParam("page",2)

					.when()
					.get(serverAddress);
			int activalStatusCode = res.getStatusCode();
			assertEquals(activalStatusCode,200);

					 
		}
	 @Test
		public void valiadateWithTestDataFromPropertiesFile_JsonFile() throws IOException, ParseException {
		 ExtentReport.extentlog = ExtentReport.extentreport.startTest("valiadateSoftAssertion",
		            "Validate the status code 200");
		 String serverAddress=PropertyReader.propertyReader("config.properties", "server");
		 String endPoint=JsonReader.getTestData("endPoint");
			Response res = given()
					.queryParam("page",2)

					.when()
					.get(serverAddress+endPoint);
			int activalStatusCode = res.getStatusCode();
			 
			assertEquals(activalStatusCode,200);

					 
		}
	 @Test
	 public void  valiadateSoftAssertion() {
		 ExtentReport.extentlog = ExtentReport.extentreport.startTest("valiadateSoftAssertion",
		            "Validate the status code 200");
			RestAssured.baseURI="https://reqres.in/api";
			Response response = given()
			.queryParam("page",2)
			.when()
			.get("/users")
			.then()
			.statusCode(200)
			.extract()
	        .response();
			// Assert that the response contains 6 users
			 response.then().body("data",hasSize(6));
			 assertThat(response.jsonPath().get("data"),hasSize(6));
			// Assert that the first user in the list has the correct values
			   int StatusCode = response.statusCode();
			 assertEquals(StatusCode ,200);
			 softAssertion.assertEquals(StatusCode, 200, "Status Code should be 200");
			 softAssertion.assertAll();
		}
	 @Test(description = "Validate the status code for GET users endpoint", enabled = true, groups = {"SmokeSuite", "RegressionSuite"})
	 public void validatePostWithJsonAsFile() throws IOException {
	    ExtentReport.extentlog = ExtentReport.extentreport.startTest("validateResponseBodyPojoPost",
	            "Validate the status code for GET users endpoint");
	    List<String> listLanguages=new ArrayList<>();
	    listLanguages.add("java");
	    listLanguages.add("python");
	    PostRequestBody postRequest=new PostRequestBody();
	    postRequest.setUserName("morpheus");
	    postRequest.setJob("leader");
	    postRequest.setLanguages(listLanguages);
	    Response resp = given()
	            .body(postRequest)
	            .header("Content-type", "application/json")
	            .when()
	            .post("https://reqres.in/api/users");
	    //assertEquals(resp.statusCode(), 200); //Testng
	    assertEquals(resp.statusCode(), 201); //Testng
	 }




}
