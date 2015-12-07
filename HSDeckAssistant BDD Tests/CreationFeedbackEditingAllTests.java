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
public class CreationFeedbackEditingAllTests {
	private static Selenium selenium;

	@BeforeClass
	public static void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://deliverable6test.appspot.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
		
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
	}

	@Test
	public void A_testAddCard() throws Exception {
		selenium.open("/");
		selenium.click("xpath=(//img[@id='hero_select_img'])[5]");
		selenium.waitForPageToLoad("30000");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/EX1_621.png')]");
		assertEquals("Circle of Healing", selenium.getText("css=div.deckcardname"));
	}
	
	@Test
	public void B_testRemoveCard() throws Exception {
		selenium.open("/");
		selenium.click("xpath=(//img[@id='hero_select_img'])[5]");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=img.builder_card");
		assertTrue(selenium.getText("id=decklist").matches("^[\\s\\S]*Circle of Healing[\\s\\S]*$"));
		selenium.click("css=div.deckcardname");
		assertFalse(selenium.getText("id=decklist").matches("^[\\s\\S]*Circle of Healing[\\s\\S]*$"));
	}
	
	@Test
	public void C_testDeckListTooSmall() throws Exception {
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
		selenium.type("id=deck_name_box", "Test Priest One");
		selenium.click("xpath=(//button[@type='button'])[5]");
		Thread.sleep(1000);
		selenium.click("id=writeupsave");
		assertEquals("You cannot save a deck smaller than 30 cards!", selenium.getAlert());
	}
	
	@Test
	public void D_testDeckListNoTitle() throws Exception {
		selenium.open("/");
		selenium.click("xpath=(//img[@id='hero_select_img'])[5]");
		selenium.waitForPageToLoad("30000");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/EX1_621.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/EX1_621.png')]");
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
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/CS2_235.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/CS2_235.png')]");
		selenium.click("css=#btn-next > img");
		selenium.click("//div[@id='deckbuilder']/div[4]/li[2]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/AT_013.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/AT_013.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/CS2_004.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/CS2_004.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/GVG_009.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/GVG_009.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/BRM_004.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/BRM_004.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/AT_016.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/AT_016.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/AT_015.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/AT_015.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/CS2_236.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/CS2_236.png')]");
		selenium.click("xpath=(//button[@type='button'])[5]");
		Thread.sleep(1000);
		selenium.click("id=writeupsave");
		assertEquals("You cannot save a deck without a name!", selenium.getAlert());
	}
	
	@Test
	public void E_testSuccessfulCreation() throws Exception {
		selenium.open("/");
		selenium.click("xpath=(//img[@id='hero_select_img'])[5]");
		selenium.waitForPageToLoad("30000");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/EX1_621.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/EX1_621.png')]");
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
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/CS2_235.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/CS2_235.png')]");
		selenium.click("css=#btn-next > img");
		selenium.click("//div[@id='deckbuilder']/div[4]/li[2]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/AT_013.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/AT_013.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/CS2_004.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/CS2_004.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/GVG_009.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/GVG_009.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/BRM_004.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/BRM_004.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/AT_016.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/AT_016.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/AT_015.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/AT_015.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/CS2_236.png')]");
		selenium.click("//img[contains(@src,'http://wow.zamimg.com/images/hearthstone/cards/enus/original/CS2_236.png')]");
		selenium.type("id=deck_name_box", "Test Priest One");
		selenium.click("xpath=(//button[@type='button'])[5]");
		selenium.type("id=writeupbody", "This is a test description.");
		selenium.click("id=writeupsave");
		selenium.waitForPageToLoad("2000");
		assertEquals("Test Priest One", selenium.getText("link=Test Priest One"));
	}
	
	@Test
	public void F_testCommentOnDeckList() throws Exception {
		selenium.open("/");
		selenium.click("link=deliv6test");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Test Priest One");
		selenium.waitForPageToLoad("30000");
		selenium.type("name=comment", "This is a test comment.");
		selenium.click("css=input[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		assertEquals("This is a test comment.", selenium.getText("//div[@id='checkdeckrightpanel']/div[2]/div"));
		assertEquals("deliv6test", selenium.getText("//div[@id='checkdeckrightpanel']/div[2]/b"));
	}
	
	@Test
	public void G_testLikeDeckList() throws Exception {
		selenium.open("/");
		selenium.click("link=deliv6test");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Test Priest One");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Click to Like");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.getText("css=i").matches("^[\\s\\S]*You like this deck\\.[\\s\\S]*$"));
		assertEquals("Click to Unlike", selenium.getText("link=Click to Unlike"));
	}
	
	@Test
	public void H_testUnLikeDeckList() throws Exception {
		selenium.open("/");
		selenium.click("link=deliv6test");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Test Priest One");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.getText("css=i").matches("^[\\s\\S]*You like this deck\\.[\\s\\S]*$"));
		assertEquals("Click to Unlike", selenium.getText("link=Click to Unlike"));
		selenium.click("link=Click to Unlike");
		selenium.waitForPageToLoad("30000");
		assertEquals("Click to Like", selenium.getText("link=Click to Like"));
	}
	
	@Test
	public void I_testOpenCorrectEditPage() throws Exception {
		selenium.open("/");
		selenium.click("link=deliv6test");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Edit");
		selenium.waitForPageToLoad("30000");
		Thread.sleep(1000);
		assertEquals("Test Priest One", selenium.getAttribute("id=deck_name_box@value"));
		assertEquals("Northshire Cleric", selenium.getText("//li[9]/a/div[2]/div[2]"));
		selenium.click("xpath=(//button[@type='button'])[5]");
		Thread.sleep(500);
		assertEquals("This is a test description.", selenium.getText("id=writeupbody"));
	}
	
	@Test
	public void J_testEditTitle() throws Exception {
		selenium.open("/");
		selenium.click("link=deliv6test");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Edit");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=deck_name_box", "Test Priest One EDIT");
		selenium.click("xpath=(//button[@type='button'])[5]");
		Thread.sleep(1000);
		selenium.click("id=writeupsave");
		Thread.sleep(2000);
		assertEquals("Test Priest One EDIT", selenium.getText("link=Test Priest One EDIT"));
	}
	
	@Test
	public void K_testEditDeckContents() throws Exception {
		selenium.open("/");
		selenium.click("link=deliv6test");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Edit");
		selenium.waitForPageToLoad("30000");
		selenium.click("//a[@id='tooltip-container']/div[2]/div[2]");
		selenium.click("css=#seekneutralcards > img");
		selenium.click("//div[@id='deckbuilder']/div[4]/li[7]");
		selenium.click("css=img.builder_card");
		selenium.click("xpath=(//button[@type='button'])[5]");
		Thread.sleep(1000);
		selenium.click("id=writeupsave");
		Thread.sleep(2000);
		selenium.click("css=td.sorting_1 > a");
		selenium.waitForPageToLoad("30000");
		assertEquals("Murloc Tinyfin", selenium.getText("css=#LOEA10_3 > #tooltip-container > div.content > div.deckcardname"));
	}
	
	@Test
	public void L_testEditDeckDescription() throws Exception {
		selenium.open("/");
		selenium.click("link=deliv6test");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Edit");
		selenium.waitForPageToLoad("30000");
		selenium.click("xpath=(//button[@type='button'])[5]");
		Thread.sleep(1000);
		selenium.type("id=writeupbody", "This is a test description. With an edit!");
		selenium.click("id=writeupsave");
		Thread.sleep(2000);
		selenium.click("css=td.sorting_1 > a");
		selenium.waitForPageToLoad("30000");
		assertEquals("This is a test description. With an edit!", selenium.getText("id=writeup_area"));
	}

	
	@AfterClass
	public static void tearDown() throws Exception {
		selenium.stop();
	}
	
}