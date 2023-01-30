package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigUtility;
import utilities.UtilityFunctions;

public class LoginPage {

    @FindBy(id="username")
    WebElement userNameFiled;
    @FindBy(id="password")
    WebElement passwordField;
    @FindBy(id="login")
    WebElement loginButton;
    WebDriver driver;


    String userName= ConfigUtility.readeFromConfigFile("config_prod.properties","username");
    String password= ConfigUtility.readeFromConfigFile("config_prod.properties","password");

    UtilityFunctions utilityFunctions;
    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        utilityFunctions=new UtilityFunctions(driver);

    }

    public void login(){
        utilityFunctions.waitUntilElementPresent(userNameFiled);
        userNameFiled.sendKeys(userName);
        utilityFunctions.waitUntilElementPresent(passwordField);
        passwordField.sendKeys(password);
        utilityFunctions.waitUntilElementPresent(loginButton);
        loginButton.click();
    }
}
