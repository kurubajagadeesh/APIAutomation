package userManagement;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import core.BaseTest;
import io.restassured.response.Response;
import utils.ExtentReport;
import utils.JsonReader;

public class GetErgast extends BaseTest{
	@Test(groups = {"SmokeSuite"})
	public void testResponseBodyGetPathParms() {
		ExtentReport.extentlog = ExtentReport.extentreport.startTest("testResponseBodyGetPathParms","valiadate status code is 200");

				 
		Response res = given()
			.pathParam("raceSeason", 2017)
			.when()
			.get("http://ergast.com/api/f1/{raceSeason}/circuits.json"); //RestAssured 
			int activalStatusCode = res.statusCode();
			assertEquals(activalStatusCode,200);
			System.out.println(res.body().asString());
			
					
	}
	//@Test
	public void getUserData() throws IOException, ParseException {
		System.out.println(JsonReader.getJsonArrayData("languages", 0));
		
	}
	@Test
	public void getJsonArray() throws IOException, ParseException {
		JSONArray jsonArray = JsonReader.getJsonArray("contact");
		Iterator<String> iterator = jsonArray.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

}
