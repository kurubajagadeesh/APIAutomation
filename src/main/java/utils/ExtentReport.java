package utils;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReport {
	public static ExtentReports extentreport = null;

	public static ExtentTest extentlog;

    

	public static void initialize(String path) {

		if (extentreport == null) {

			extentreport = new ExtentReports(path, true);

			extentreport.addSystemInfo("Host Name", System.getProperty("user.name"));

			extentreport.addSystemInfo("Environment", "QA");
			extentreport.addSystemInfo("OS", "Windows11");

			extentreport.loadConfig(new File(System.getProperty("user.dir") + "src/main/resources/extentconfig.xml"));

		}

	}

}
