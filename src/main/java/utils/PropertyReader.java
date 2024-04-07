package utils;

import java.io.FileInputStream;
 
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	public static String propertyReader(String filePath,String key) {
		String value = null;
	       
        //Inputstream is required while loading into properties
		try(InputStream file=new FileInputStream(filePath))
		{
			 
			// object creation for Property class
			
			Properties prop=new Properties();
			
			// load a properties file
			
			prop.load(file);
			//getProperty will fetch the value according to the key
			
            value=prop.getProperty(key);
		}   catch (IOException e) {
			 
			e.printStackTrace();
		}
		return value;
	}

}
