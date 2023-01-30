package utilities;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class UtilityFunctions {

    WebDriver driver;
    public UtilityFunctions(WebDriver driver){
        this.driver=driver;
    }
    int timeOut=Integer.parseInt(ConfigUtility.readeFromConfigFile("config_prod.properties","timeout"));
    public  void waitUntilElementPresent(WebElement element){
        WebDriverWait wait=new WebDriverWait(driver,timeOut);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public  void waitUntilElementsPresent(List<WebElement> elements){
        WebDriverWait wait=new WebDriverWait(driver,timeOut);
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitAlertPresent(){
        WebDriverWait wait=new WebDriverWait(driver,timeOut);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public  String getCurrentTime(){
        DateTime time=new DateTime();
        DateTimeFormatter format=DateTimeFormat.forPattern("yyMMddHHmmss");
        return  time.toString(format);
    }
    public void takeScreenShot(WebDriver driver,String fileName){
        File imageFile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
         String filePath=ConfigUtility.readeFromConfigFile("config_prod.properties","screenfolder")
                 +File.separator+fileName+getCurrentTime()+".png";
        try {
            FileUtils.copyFile(imageFile,new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
