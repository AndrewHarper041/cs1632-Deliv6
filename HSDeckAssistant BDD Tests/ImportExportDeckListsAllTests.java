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
public class ImportExportDeckListsAllTests {
	private static Selenium selenium;

	@BeforeClass
	public static void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://deliverable6test.appspot.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testImportValidList() throws Exception {
		selenium.open("/");
		selenium.click("xpath=(//img[@id='hero_select_img'])[5]");
		selenium.waitForPageToLoad("30000");
		selenium.click("xpath=(//button[@type='button'])[3]");
		selenium.type("id=import_textarea", "2 Holy Smite\n2 Twilight Whelp\n2 Power Word: Shield\n2 Northshire Cleric\n2 Wyrmrest Agent\n1 Shadow Word: Pain\n1 Eydis Darkbane\n1 Blackwing Technician\n2 Velen's Chosen\n1 Shadow Word: Death\n2 Twilight Guardian\n1 Vol'jin\n2 Azure Drake\n2 Blackwing Corruptor\n2 Holy Nova\n1 Lightbomb\n1 Cabal Shadow Priest\n1 Chillmaw\n1 Chromaggus\n1 Ysera");
		selenium.click("id=import_submit");
		assertEquals("Import Complete!", selenium.getText("id=process_p"));
		selenium.click("css=button.btn.btn-default");
		assertEquals("Holy Smite", selenium.getText("//a[@id='tooltip-container']/div[2]/div[2]"));
		assertEquals("Ysera", selenium.getText("xpath=(//a[@id='tooltip-container']/div[2]/div[2])[20]"));
	}
	
	@Test
	public void testImportOversizedList() throws Exception {
		selenium.open("/");
		selenium.click("xpath=(//img[@id='hero_select_img'])[5]");
		selenium.waitForPageToLoad("30000");
		selenium.click("xpath=(//button[@type='button'])[3]");
		selenium.type("id=import_textarea", "2 Holy Smite\n2 Twilight Whelp\n2 Power Word: Shield\n2 Northshire Cleric\n2 Wyrmrest Agent\n1 Shadow Word: Pain\n1 Eydis Darkbane\n1 Blackwing Technician\n2 Velen's Chosen\n1 Shadow Word: Death\n2 Twilight Guardian\n1 Vol'jin\n2 Azure Drake\n2 Blackwing Corruptor\n2 Holy Nova\n1 Lightbomb\n1 Cabal Shadow Priest\n1 Chillmaw\n1 Chromaggus\n1 Ysera\n1 Bill Laboon\n2 Lightwarden");
		selenium.click("id=import_submit");
		assertEquals("You tried to import more than 30 cards! Your 2 Lightwarden(s) will not be imported.", selenium.getAlert());
		assertEquals("Import Complete!", selenium.getText("id=process_p"));
		selenium.click("css=button.btn.btn-default");
		assertEquals("Holy Smite", selenium.getText("//a[@id='tooltip-container']/div[2]/div[2]"));
		assertEquals("Ysera", selenium.getText("xpath=(//a[@id='tooltip-container']/div[2]/div[2])[20]"));
	}
	
	@Test
	public void testImportInvalidList() throws Exception {
		selenium.open("/");
		selenium.click("xpath=(//img[@id='hero_select_img'])[5]");
		selenium.waitForPageToLoad("30000");
		selenium.click("xpath=(//button[@type='button'])[3]");
		selenium.type("id=import_textarea", "2 Holy Smite\n1 Bill Laboon\n1 Northshire Cleric\n7 Mollywumpus\n1 Ysera\n1 Deathwing");
		selenium.click("id=import_submit");
		assertEquals("Import Complete!", selenium.getText("id=process_p"));
		selenium.click("css=button.btn.btn-default");
		assertTrue(selenium.getText("id=inner_list").matches("^[\\s\\S]*Holy Smite[\\s\\S]*$"));
		assertFalse(selenium.getText("id=inner_list").matches("^[\\s\\S]*Bill Laboon[\\s\\S]*$"));
		assertTrue(selenium.getText("id=inner_list").matches("^[\\s\\S]*Northshire Cleric[\\s\\S]*$"));
		assertFalse(selenium.getText("id=inner_list").matches("^[\\s\\S]*Mollywumpus[\\s\\S]*$"));
		assertTrue(selenium.getText("id=inner_list").matches("^[\\s\\S]*Ysera[\\s\\S]*$"));
		assertTrue(selenium.getText("id=inner_list").matches("^[\\s\\S]*Deathwing[\\s\\S]*$"));
	}
	
	@Test
	public void testExportDeckList() throws Exception {
		selenium.open("/");
		selenium.click("xpath=(//img[@id='hero_select_img'])[5]");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=img.builder_card");
		selenium.click("css=img.builder_card");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/EX1_332.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/EX1_332.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/AT_055.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/AT_055.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/CS1_130.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/CS1_130.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/CS1_129.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/CS1_129.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/GVG_012.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/GVG_012.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/CS2_003.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/CS2_003.png')]");
		selenium.click("css=a[type=\"Minion\"] > img.builder_card");
		selenium.click("css=a[type=\"Minion\"] > img.builder_card");
		selenium.type("id=deck_name_box", "Test Priest Export");
		selenium.click("xpath=(//button[@type='button'])[4]");
		assertEquals("2 Circle of Healing 2 Silence 2 Flash Heal 2 Holy Smite 2 Inner Fire 2 Light of the Naaru 2 Mind Vision 2 Northshire Cleric", selenium.getText("id=deck_export_area"));
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		selenium.stop();
	}
	
}