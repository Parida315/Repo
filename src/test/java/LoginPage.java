
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends TestBase{

    public static void logIn(LoginUserObject user){
        WebElement userName=driver.findElement(By.id("username"));
        UtilityClass.waitForElementPresent(userName);
        userName.sendKeys(user.getUserName());
        WebElement passWord=driver.findElement(By.id("password"));
        UtilityClass.waitForElementPresent(passWord);
        passWord.sendKeys(user.getPassWord());
        WebElement logInButton=driver.findElement(By.id("login"));
        logInButton.click();
    }

    public static boolean verifyLogOut(){
        String result="";
        WebElement longInButton=driver.findElement(By.id("login"));
        if (longInButton.isDisplayed()){
            System.out.println("LogOut test Pass");
            result="LogOut test Pass";
            UtilityClass.takeScreenShot("LogOut"+UtilityClass.jodaDateTime());
            return true;

        }else {
            System.out.println("LogOutTest Fail");
           result="LogOut test Fail";
           return false;
        }

        //UtilityClass.writeToFile("LogOutTestResult",".txt", result);
       // UtilityClass.writeToExcelFile("ExcelReport.xlsx","Project3",1,7,result);

    }
}
