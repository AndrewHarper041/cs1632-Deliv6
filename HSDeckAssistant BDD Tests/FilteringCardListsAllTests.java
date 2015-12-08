/*
 * Tests from US-3
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
import org.openqa.selenium.Keys;


@SuppressWarnings("deprecation")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FilteringCardListsAllTests {
	private static Selenium selenium;

	@BeforeClass
	public static void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://deliverable6test.appspot.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	//US-3 ScenarioA test
	@Test
	public void testFilterByName() throws Exception {
		selenium.open("/deckbuilder?class=Priest");
		selenium.type("css=input.search", "Circle of Healing");
		selenium.keyPress("css=input.search", "\n");
		Thread.sleep(500);
		assertTrue(selenium.getText("//div[@id='object']/a").matches("^[\\s\\S]*Circle of Healing[\\s\\S]*$"));
	}
	
	//US-3 ScenarioB test
	@Test
	public void testFilterByType() throws Exception {
		selenium.open("/deckbuilder?class=Rogue");
		selenium.type("css=input.search", "weapon");
		selenium.keyPress("css=input.search", "\n");
		Thread.sleep(500);
		assertTrue(selenium.getText("//div[@id='object']/a").matches("^[\\s\\S]*Weapon[\\s\\S]*$"));
		assertTrue(selenium.getText("//div[@id='object']/a[2]").matches("^[\\s\\S]*Weapon[\\s\\S]*$"));
		assertTrue(selenium.getText("//div[@id='object']/a[3]").matches("^[\\s\\S]*Weapon[\\s\\S]*$"));
		assertTrue(selenium.getText("//div[@id='object']/a[4]").matches("^[\\s\\S]*Weapon[\\s\\S]*$"));
	}
	
	//US-3 ScenarioC test
	@Test
	public void testFilterByTribe() throws Exception {
		selenium.open("/deckbuilder?class=Priest");
		selenium.type("css=input.search", "murloc");
		selenium.keyPress("css=input.search", "\n");
		Thread.sleep(500);
		assertFalse(selenium.getText("id=object").matches("^[\\s\\S]*Beast[\\s\\S]*$"));
		assertFalse(selenium.getText("id=object").matches("^[\\s\\S]*Mech[\\s\\S]*$"));
		assertFalse(selenium.getText("id=object").matches("^[\\s\\S]*Dragon[\\s\\S]*$"));
		assertTrue(selenium.getText("id=object").matches("^[\\s\\S]*Murloc[\\s\\S]*$"));
	}
	
	//US-3 ScenarioD test
	@Test
	public void testFilterByMechanics() throws Exception {
		selenium.open("/deckbuilder?class=Priest");
		selenium.type("css=input.search", "deathrattle");
		selenium.keyPress("css=input.search", "\n");
		Thread.sleep(500);
		assertFalse(selenium.getText("id=object").matches("^[\\s\\S]*u'Battlecry'[\\s\\S]*$"));
		assertFalse(selenium.getText("id=object").matches("^[\\s\\S]*u'Stealth'[\\s\\S]*$"));
		assertFalse(selenium.getText("id=object").matches("^[\\s\\S]*u'Taunt'[\\s\\S]*$"));
		assertTrue(selenium.getText("id=object").matches("^[\\s\\S]*u'Deathrattle'[\\s\\S]*$"));
	}
	
	//US-3 ScenarioE test
	@Test
	public void testFilterByClassNeutral() throws Exception {
		selenium.open("/deckbuilder?class=Priest");
		selenium.click("css=#seekneutralcards > img");
		selenium.click("//div[@id='deckbuilder']/div[4]/li[7]");
		assertTrue(selenium.getText("//div[@id='object']/a").matches("^[\\s\\S]*Murloc Tinyfin[\\s\\S]*$"));
		selenium.click("css=#seekclasscards > img");
		selenium.click("css=div.pagination > li");
		assertTrue(selenium.getText("//div[@id='object']/a").matches("^[\\s\\S]*Circle of Healing[\\s\\S]*$"));
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		selenium.stop();
	}
	
}