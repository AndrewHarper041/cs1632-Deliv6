/*
 * Tests from US-1
 */

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import static org.junit.Assert.*;
import java.util.regex.Pattern;
import static org.apache.commons.lang3.StringUtils.join;

@SuppressWarnings("deprecation")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoggingInAllTests {
	private static Selenium selenium;

	@BeforeClass
	public static void setUp() throws Exception 
	{
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://deliverable6test.appspot.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	//US-1 ScenarioA test
	@Test
	public void A_testNoUserAccount() throws Exception 
	{
		selenium.open("/");
		selenium.click("link=Login");
		selenium.waitForPageToLoad("30000");
		assertEquals("Create account", selenium.getText("link=Create account"));
	}
	
	//US-1 ScenarioB test
	@Test
	public void B_testInvalidEmail() throws Exception 
	{
		selenium.open("/");
		selenium.click("link=Login");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=Email", "deliverable6testerddddd");
		selenium.click("id=next");
		Thread.sleep(500);
		assertEquals("Sorry, Google doesn't recognize that email.", selenium.getText("id=errormsg_0_Email"));
	}
	
	//US-1 ScenarioC test
	@Test
	public void C_testInvalidPassword() throws Exception {
		selenium.open("/");
		selenium.click("link=Login");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=Email", "deliverable6tester");
		selenium.click("id=next");
		selenium.type("id=Passwd", "thisiswrong");
		selenium.click("id=signIn");
		selenium.waitForPageToLoad("30000");
		
		assertEquals("The email and password you entered don't match.", selenium.getText("id=errormsg_0_Passwd"));
	}
	
	//US-1 ScenarioD test
	@Test
	public void D_testSuccessfulLogin() throws Exception 
	{
		selenium.open("/");
		selenium.click("link=Login");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=Email", "deliverable6tester");
		selenium.click("id=next");
		selenium.type("id=Passwd", "laboond6");
		selenium.click("id=signIn");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=approve_button");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=deliv6test");
		selenium.waitForPageToLoad("30000");
		assertEquals("deliv6test", selenium.getText("css=h3"));
		assertEquals("deliv6test", selenium.getText("link=deliv6test"));
	}
	
	@AfterClass
	public static void tearDown() throws Exception 
	{
		selenium.stop();
	}
	
}