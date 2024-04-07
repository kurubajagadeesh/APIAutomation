package utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {
	
	//a method which will fetch value from json object using key
	public static String getTestData(String key) throws IOException, ParseException {
		String testDataValue;
		return testDataValue=(String) getJsonData().get(key);
		
	}
	
	//a method as  getJsonData() with return type as JsonObject
	public static JSONObject getJsonData() throws IOException, ParseException {
		 //pass the path of the testdata.json file
		File jsonfile=new File("src/test/resources/testData/testdata.json");
		//convert json file into string
		String json = FileUtils.readFileToString(jsonfile, StandardCharsets.UTF_8);
		 //parse the string into object
		Object obj = new JSONParser().parse(json);
		JSONObject jsonObject=(JSONObject) obj;
		return jsonObject;
		
	}
	public static JSONArray getJsonArray(String key) throws IOException, ParseException {
		JSONObject jsonObject = getJsonData();
		 JSONArray jsonArray = (JSONArray)jsonObject.get(key);
		 return jsonArray;
		 
	}
	public static Object getJsonArrayData(String key,int index) throws IOException, ParseException {
		JSONArray jsonArray = getJsonArray(key);
		return jsonArray.get(index);
		
	}

}
