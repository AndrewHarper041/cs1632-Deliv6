/*
 * Tests from US-7
 */

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.runners.MethodSorters;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.util.HashMap;
import static org.junit.Assert.*;

@SuppressWarnings("deprecation")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoadTimesAllTests 
{
	private static Selenium selenium;
	private static JavascriptExecutor js;

	@BeforeClass
	public static void setUp() throws Exception 
	{
		WebDriver driver = new FirefoxDriver();
		js = (JavascriptExecutor)driver;
		String baseUrl = "http://deliverable6test.appspot.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	/*
	 * Tests the initial load time of ursl to ensure the time stays under threshold.
	 * Threshold choose is the 2 seconds metric from user stories, twice the 'doesnt interrupt user flow' common metric
	 * 
	 * Some pages take as much as 22 seconds due to large amount of content, and will always fail to load in time 
	 * Without large optimizations to the site
	 * 
	 * There are secondary 'utility' tests, to see if anything takes abnormally long, which were where picked with the goal of isolation on what end (server/client) the issue is occuring
	 * Main test is time from request start to dom is interactive for user
	 * 
	 * Load times received from JavaScript window.perormance.timing come in the form of a list of 
	 * various metrics. More info here: http://www.w3.org/TR/navigation-timing/#sec-navigation-timing-interface
	 * 
	 * NOTE:
	 * The return value of the JS call is a particular type of map that can not easily be turned into a java object
	 * No way to resolve the issue was found, with the exception of streaming in individual performance fields,
	 * Which seemed equally as hacky as pulling them out as Strings. Which I did.
	 * String is in {key=value, key1=value1, etc}  form, so parseing is easy, splitting individual key value pairs apart
	 * with split along ',' and then retrieving the key/value by splitting on '='
	 * Solution is enitirely non portable as well as likely to break in the future, so hardly a good idea.
	 */
	
	//Tests main page load time. Total load time required is 2 seconds, close to the time 
	@Test
	public void A_testMainPageLoadTime() throws Exception 
	{
		selenium.open("/");
		
		//Get the tracked performance methods using javasc
		Object timings = js.executeScript("var performance = window.performance || {};" + 
	            "var timings = performance.timing || {};"+
	            "return timings;");
		
		String timingsString = timings.toString();
		HashMap<String,Long> timingsMap = new HashMap<String,Long>();
		
		//Remove unneeded chars from string
		timingsString = timingsString.replace("{", "");
		timingsString = timingsString.replace("}", "");
		timingsString = timingsString.replace(" ", "");
		
		//For loop, iterates over timingsString as array deliminated by commas, and extracts keys and values into usable types
		for (String tmp: timingsString.split(","))
		{
			//Split the key/value pair along '=', and read values into hashmap
			String[] keyVal = tmp.split("=");
			
			if(isNumeric(keyVal[1])) //make sure second string is parsable
				timingsMap.put(keyVal[0], Long.parseLong(keyVal[1]));
		}
		
		//The reasonable 50ms metric is tested here, no one event should take longer
		assertTrue(timingsMap.get("responseEnd") - timingsMap.get("responseStart") <= 50); //checks time it takes to recieve request response data, from when the response first begins
		assertTrue(timingsMap.get("domContentLoadedEventEnd") - timingsMap.get("domContentLoadedEventStart") <= 50); //checks time from beginning of dom load 
		assertTrue(timingsMap.get("loadEventEnd") - timingsMap.get("loadEventStart") <= 50); //checks time for loading data to complete 
		
		//Here is the 'main' test, checking that from the time the client sends response, untill the dom is interactive by user or 'done' by the user perspective
		assertTrue(timingsMap.get("domInteractive") - timingsMap.get("requestStart") <= 2000); //2000 is used to check it is quick enough to keep user attention
	}
	
	//Tests the more content heavy deck builder page loads reasonably.
	//Page is very, very slow.
	@Test
	public void B_testDeckMakerLoadTime() throws Exception 
	{	
		selenium.open("/deckbuilder?class=Rogue"); //load arbitrary deck builder page to test (all images same size, which deck builder shouldnt matter)
		
		//Get the tracked performance methods using javasc
		Object timings = js.executeScript("var performance = window.performance || {};" + 
	            "var timings = performance.timing || {};"+
	            "return timings;");
				
		String timingsString = timings.toString();
		HashMap<String,Long> timingsMap = new HashMap<String,Long>();
		
		//Remove unneeded chars from string
		timingsString = timingsString.replace("{", "");
		timingsString = timingsString.replace("}", "");
		timingsString = timingsString.replace(" ", "");
		
		//For loop, iterates over timingsString as array deliminated by commas, and extracts keys and values into usable types
		for (String tmp: timingsString.split(","))
		{
			//Split the key/value pair along '=', and read values into hashmap
			String[] keyVal = tmp.split("=");
			
			if(isNumeric(keyVal[1])) //make sure second string is parsable
				timingsMap.put(keyVal[0], Long.parseLong(keyVal[1]));
		}
		
		//The reasonable 50ms metric is tested here, no one event should take longer
		assertTrue(timingsMap.get("responseEnd") - timingsMap.get("responseStart") <= 50); //checks time it takes to recieve request response data, from when the response first begins
		assertTrue(timingsMap.get("domContentLoadedEventEnd") - timingsMap.get("domContentLoadedEventStart") <= 50); //checks time from beginning of dom load 
		assertTrue(timingsMap.get("loadEventEnd") - timingsMap.get("loadEventStart") <= 50); //checks time for loading data to complete 

		//Here is the 'main' test, checking that from the time the client sends response, untill the dom is complete or 'loaded' by the user perspective
		assertTrue(timingsMap.get("domInteractive") - timingsMap.get("requestStart") <= 2000); //2 seconds
	}
	
	//Tests the all deck lists load time
	//Has variable content and may be slow
	@Test
	public void C_testAllDecklistsLoadTest() throws Exception 
	{	
		selenium.open("/alldecklists"); //load all decksmpage
		
		//Get the tracked performance methods using javasc
		Object timings = js.executeScript("var performance = window.performance || {};" + 
	            "var timings = performance.timing || {};"+
	            "return timings;");
				
		String timingsString = timings.toString();
		HashMap<String,Long> timingsMap = new HashMap<String,Long>();
		
		//Remove unneeded chars from string
		timingsString = timingsString.replace("{", "");
		timingsString = timingsString.replace("}", "");
		timingsString = timingsString.replace(" ", "");
		
		//For loop, iterates over timingsString as array deliminated by commas, and extracts keys and values into usable types
		for (String tmp: timingsString.split(","))
		{
			//Split the key/value pair along '=', and read values into hashmap
			String[] keyVal = tmp.split("=");
			
			if(isNumeric(keyVal[1])) //make sure second string is parsable
				timingsMap.put(keyVal[0], Long.parseLong(keyVal[1]));
		}
		
		//The reasonable 50ms metric is tested here, no one event should take longer
		assertTrue(timingsMap.get("responseEnd") - timingsMap.get("responseStart") <= 50); //checks time it takes to recieve request response data, from when the response first begins
		assertTrue(timingsMap.get("domContentLoadedEventEnd") - timingsMap.get("domContentLoadedEventStart") <= 50); //checks time from beginning of dom load 
		assertTrue(timingsMap.get("loadEventEnd") - timingsMap.get("loadEventStart") <= 50); //checks time for loading data to complete 

		//Here is the 'main' test, checking that from the time the client sends response, untill the dom is complete or 'loaded' by the user perspective
		assertTrue(timingsMap.get("domInteractive") - timingsMap.get("requestStart") <= 2000); //2 seconds
	}
	
	//Tests the about page load time
	//Similar methodolgy to other load time tests
	//Content light and should load very fast
	@Test
	public void D_testAllDecklistsLoadTest() throws Exception 
	{	
		selenium.open("/about"); //load about
		
		//Get the tracked performance methods using javasc
		Object timings = js.executeScript("var performance = window.performance || {};" + 
	            "var timings = performance.timing || {};"+
	            "return timings;");
				
		String timingsString = timings.toString();
		HashMap<String,Long> timingsMap = new HashMap<String,Long>();
		
		//Remove unneeded chars from string
		timingsString = timingsString.replace("{", "");
		timingsString = timingsString.replace("}", "");
		timingsString = timingsString.replace(" ", "");
		
		//For loop, iterates over timingsString as array deliminated by commas, and extracts keys and values into usable types
		for (String tmp: timingsString.split(","))
		{
			//Split the key/value pair along '=', and read values into hashmap
			String[] keyVal = tmp.split("=");
			
			if(isNumeric(keyVal[1])) //make sure second string is parsable
				timingsMap.put(keyVal[0], Long.parseLong(keyVal[1]));
		}
		
		//The reasonable 50ms metric is tested here, no one event should take longer
		assertTrue(timingsMap.get("responseEnd") - timingsMap.get("responseStart") <= 50); //checks time it takes to recieve request response data, from when the response first begins
		assertTrue(timingsMap.get("domContentLoadedEventEnd") - timingsMap.get("domContentLoadedEventStart") <= 50); //checks time from beginning of dom load 
		assertTrue(timingsMap.get("loadEventEnd") - timingsMap.get("loadEventStart") <= 50); //checks time for loading data to complete 

		//Here is the 'main' test, checking that from the time the client sends response, untill the dom is complete or 'loaded' by the user perspective
		assertTrue(timingsMap.get("domInteractive") - timingsMap.get("requestStart") <= 2000); //2 seconds 
	}
	
	//Helper function to check if a string is of numeric value
	private boolean isNumeric(String str) 
	{
		if (str == null) 
	        return false;
		
	    int sz = str.length();
	    
	    for (int i = 0; i < sz; i++) 
	        if (Character.isDigit(str.charAt(i)) == false) 
	            return false;
	        
	    return true;
	}

	@AfterClass
	public static void tearDown() throws Exception {
		selenium.stop();
	}
	
}