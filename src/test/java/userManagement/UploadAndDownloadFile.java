package userManagement;

import java.io.File;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class UploadAndDownloadFile {
	
	@Test
	public void fileUploadExample() {
		File file=new File("src/test/resources/testData/demo.txt");
		Response res = given()
			.multiPart("file",file)
			.when()
			.post("\"https://example.com/upload\"");
	     System.out.println(res.getStatusCode());
			
	}
 

}
