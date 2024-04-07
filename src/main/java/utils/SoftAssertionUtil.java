package utils;
import org.testng.asserts.SoftAssert;
 

public class SoftAssertionUtil {
	private SoftAssert softAssert;
	 


	   public SoftAssertionUtil() {
	       softAssert = new SoftAssert();
	   }


	   public void assertTrue(boolean condition, String message) {
	       try {
	           softAssert.assertTrue(condition, message);
	       } catch (AssertionError e) {
	           softAssert.fail(message);
	       }
	   }


	   public void assertEquals(Object actual, Object expected, String message) {
	       try {
	           softAssert.assertEquals(actual, expected, message);
	       } catch (AssertionError e) {
	           softAssert.fail(message);
	       }
	   }


	   public void assertNotEquals(Object actual, Object expected, String message) {
	       try {
	           softAssert.assertNotEquals(actual, expected, message);
	       } catch (AssertionError e) {
	           softAssert.fail(message);
	       }
	   }


	   public void assertAll() {
	       softAssert.assertAll();
	   }




	/*softAssertionUtil.assertTrue(page.CURATION_HEADER.isPresent(),
	       " Header should be present");
	softAssertionUtil.assertTrue(page.CURATION_MESSAGE.isPresent(),
	       " Message should be present");
	softAssertionUtil.assertAll();*/

}


