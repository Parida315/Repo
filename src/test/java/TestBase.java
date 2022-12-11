import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class TestBase {
    public static WebDriver driver;


    public static String browserName;

    public static void browserSetup(String url) {
        if (driver == null) {
            if (browserName.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                driver.navigate().to(url);

            } else if (browserName.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new FirefoxDriver(options);
                driver.manage().window().maximize();
                driver.get(url);

            }
        }
    }


    public static void closeBrowser() {
        driver.close();
        driver.quit();
    }

}
