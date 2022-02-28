package seleniumProject;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestCase {
	public WebDriver webDriver;
	
	@BeforeClass
	public void setUpEnv() {
		Path path = Paths.get("src", "drivers","chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", path.toAbsolutePath().toString());
		
		// Create an entity for the browser
		webDriver = new ChromeDriver();
		webDriver.manage().window().maximize();
	}
	
	@Test(description = "Search 'Hello World' via Google")
	public void testGoogleSearchByName() {
		webDriver.get("https://www.google.ca/");
		
		WebElement element = webDriver.findElement(By.name("q"));
		element.sendKeys("Hello World");
		element.submit();
	}
	
	@Test(description = "Search 'Java Books' via Amazon")
	public void testAmazonSearchByID() {
		webDriver.get("https://www.amazon.ca/");
		
		webDriver.findElement(By.id("twotabsearchtextbox")).sendKeys("Java Books");
		webDriver.findElement(By.id("nav-search-submit-button")).click();
	}
	
	@Test(description = "Open navbar item by name: 'Deals Store'")
	public void testAmazonSearchByXpath() {
		webDriver.get("https://www.amazon.ca/");
		webDriver.findElement(By.xpath("//a[@class=\"nav-a  \" and text()=\"Deals Store\"]")).click();
	}
	
	@Test(description = "Open the third navbar item")
	public void testAmazonSearchByCSS(){
		webDriver.get("https://www.amazon.ca/");
		webDriver.findElement(By.cssSelector("div[id=\"nav-xshop\"]>a[data-csa-c-slot-id=\"nav_cs_2\"]")).click();
	}
	
	@Test(description = "Open Products -> SystemsExpert")
	public void testAlgoExpertActions() throws InterruptedException {
		webDriver.get("https://www.algoexpert.io/product");
		Actions actions = new Actions(webDriver);
		WebElement el = webDriver.findElement(By.cssSelector("button[class=\"_5UPPBnCWXOlkYAUQEhOMY\"]"));
		actions.moveToElement(el).perform();
		Thread.sleep(1000);
		WebElement link = webDriver.findElement(By.cssSelector("a[class=\"_27iwxL_VaWdT7SQ3MjipBi _2vWIl7mnMXmGaJyqMehMKU\"]"));
		actions.moveToElement(link).click().perform();
	}
	
	@Test(description="Open MEN -> Sweatshirts & Hoodies")
	public void testUniqloActions() {
		webDriver.get("https://www.uniqlo.com/ca/en/");
		Actions actions = new Actions(webDriver);
		WebElement el = webDriver.findElement(By.cssSelector("a[class=\"fr-global-nav-item px-s\"]:nth-child(2)"));
		actions.moveToElement(el).perform();
		WebElement link = webDriver.findElement(By.cssSelector("div[style=\"width: 25%;\"]:nth-child(2) li[class=\"fr-accordion-list-item\"]:nth-child(2) > a"));
		actions.moveToElement(link).click().perform();
	}
	
	@Test(description = "Open Google Map -> Select Start Direction -> Select Destination -> Show the routes")
	public void testGoogleMap() throws InterruptedException {
		webDriver.get("https://www.google.ca/maps/");
		Actions actions = new Actions(webDriver);
		
		Thread.sleep(4000);
		WebElement el = webDriver.findElement(By.xpath("/html/body/div[3]/div[9]/div[1]/div[3]"));
		actions.moveToElement(el).contextClick().pause(2000).perform();
		WebElement start = webDriver.findElement(By.cssSelector("li[data-index=\"1\"] div[class=\"nbpPqf-menu-x3Eknd-text\"]"));
		actions.moveToElement(start).click();
		actions.clickAndHold();
		actions.moveByOffset(100, 200);
		actions.release();
		
		actions.contextClick().pause(3000).perform();
		
		WebElement end = webDriver.findElement(By.cssSelector("li[data-index=\"1\"] div[class=\"nbpPqf-menu-x3Eknd-text\"]"));
		actions.moveToElement(end).click().perform();

		
	}
	
	@AfterClass
	public void tearDownEnv() throws InterruptedException {
		Thread.sleep(3000);
		webDriver.quit();
	}

}
