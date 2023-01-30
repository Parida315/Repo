package pageObject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import utilities.ConfigUtility;
import utilities.TestResultListener;

@Listeners(TestResultListener.class)
public class BaseClass {
   public WebDriver driver;
    String url= ConfigUtility.readeFromConfigFile("config_prod.properties","url");
    String browserName=ConfigUtility.readeFromConfigFile("config_prod.properties","browsername");

    @BeforeClass
    public  void browserSetUp(ITestContext context){
        if(browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options=new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            driver=new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get(url);
        }else if (browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options=new FirefoxOptions();
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            driver=new FirefoxDriver(options);
            driver.manage().window().maximize();
            driver.get(url);
        }else if(browserName.equalsIgnoreCase("safari")){
            WebDriverManager.safaridriver().setup();
            SafariOptions options=new SafariOptions();
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            driver=new SafariDriver();
            driver.manage().window().maximize();
            driver.get(url);
        }
        context.setAttribute("driver",driver);
       LoginPage loginPage=new LoginPage(driver);
        loginPage.login();
    }
    @AfterClass
    public  void closeBrowser() {
        DashboardPage dashboardPage=new DashboardPage(driver);
        dashboardPage.logOut();
        driver.close();
        driver.quit();
    }


}
