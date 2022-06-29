package concepts;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNG {
	
WebDriver driver;	

String browser;
String url;

By USER_NAME_FIELD=By.id("username");
By PASWORD_FIELD=By.id("password");
By LOGIN_FIELD=By.name("login");
By DASHBOARD_FIELD=By.xpath("//h2[contains(text(),' Dashboard ')]");


// save our Test Data
String username="demo@techfios.com";
String Password="abc123";

@BeforeClass
public void readFile() {
	try {
		InputStream input=new FileInputStream("src\\main\\java\\config\\config.properties");
		Properties prop=new Properties();
				prop.load(input);
		 browser=prop.getProperty("browser");
		 url= prop.getProperty("url");
	}catch(Exception e) {
		e.printStackTrace();
	}
}

	@BeforeMethod
	public void init() {		
		
		if(browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
			driver=new ChromeDriver();
			
		}else if(browser.equalsIgnoreCase("Firefox")){
			System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
			driver=new FirefoxDriver();
		}
			
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get(url);
			
	}
	@Test
	public void login() {
		driver.findElement(USER_NAME_FIELD).sendKeys(username);
		driver.findElement(PASWORD_FIELD).sendKeys(Password);
			driver.findElement(LOGIN_FIELD).click();
		
		String Dashboard_Header=driver.findElement(DASHBOARD_FIELD).getText();
		Assert.assertEquals(Dashboard_Header, "Dashboard","Wrong Page");
	}
	
	@AfterMethod
	public void TearDown() {
		driver.close();
		driver.quit();
		
	}
}
