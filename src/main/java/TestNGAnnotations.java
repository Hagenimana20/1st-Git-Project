import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGAnnotations {
	WebDriver driver;

	By USER_NAME_FIELD = By.xpath("//input[@id='username']");
	By PASSWORD_FIELD = By.xpath("//input[@id='password']");
	By LOGIN_BUTTON_FIELD = By.xpath("//button[@name='login']");
	By DASHBOARD_FIELD = By.xpath("//h2[contains(text(),' Dashboard ')]");
	By CUSTOMERS_FIELD = By.xpath("//span[contains(text(),'Customers')]");
	By ADD_CUSTOMERS_FIELD = By.xpath("//a[contains(text(),'Add Customer')]");
	By FULL_NAME_FIELD = By.xpath("//input[@id='account']");
	By COUNTRY_FIELD = By.xpath("//select[@id='country']");
	By COMPANY_FIELD = By.xpath("//select[@id='cid']");
	By GROUP_FIELD = By.xpath("//select[@id='group']");
	By ADRESS_FIELD=By.xpath("//input[@id='address']");

	@BeforeMethod
	public void init() {

		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://techfios.com/billing/?ng=login/");
	}

	@Test(priority = 1)
	public void login() throws InterruptedException {

		driver.findElement(USER_NAME_FIELD).sendKeys("demo@techfios.com");
		driver.findElement(PASSWORD_FIELD).sendKeys("abc123");
		driver.findElement(LOGIN_BUTTON_FIELD).click();

		String Dashboard_Header = driver.findElement(DASHBOARD_FIELD).getText();
		Assert.assertEquals(Dashboard_Header, "Dashboard", "Page unavailable");

		
		driver.findElement(CUSTOMERS_FIELD).click();
		driver.findElement(ADD_CUSTOMERS_FIELD).click();
		//driver.findElement(FULL_NAME_FIELD).sendKeys("Gerard HAGENIMANA");
		
		generateRandomNo(99);		
		//generateRandomNo(999);
		
		SelectFromDropDown(COMPANY_FIELD, "Tesla");
		SelectFromDropDown(COUNTRY_FIELD, "Rwanda");
		SelectFromDropDown(GROUP_FIELD, "Selenium");
		
	}

	private void SelectFromDropDown(By locator, String visibleText) throws InterruptedException {
		
		Select sel=new Select(driver.findElement(locator));
		sel.selectByVisibleText(visibleText);
		
		
		Thread.sleep(2000);
	}
	public int generateRandomNo(int boundryNo ) {
		Random rnd = new Random();
		int RandomNumber = rnd.nextInt(boundryNo);		
		driver.findElement(FULL_NAME_FIELD).sendKeys("Gerry" + RandomNumber);		
		driver.findElement(ADRESS_FIELD).sendKeys("Dallas" + RandomNumber);
		return RandomNumber ;
	}	
	@AfterMethod

	private void tearDown() {
		driver.close();
		driver.quit();

	}

	

}
