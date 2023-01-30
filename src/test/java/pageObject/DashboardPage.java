package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.UtilityFunctions;

public class DashboardPage {
    WebDriver driver;
    @FindBy(linkText = "Customer List")
    WebElement customerLink;

    @FindBy(linkText = "Categories")
    WebElement categoryLink;
    @FindBy(css= "i.fa.fa-sign-out")
    WebElement logoutLink;
    @FindBy(id = "nav_products")
    WebElement productsLink;
    @FindBy(linkText = "Manufacturers")
    WebElement manufacturerLink;
    @FindBy(linkText = "Newsletters")
    WebElement newslettersLink;
    @FindBy(linkText = "Orders")
     WebElement orderLink;
    UtilityFunctions utilityFunctions;


    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utilityFunctions=new UtilityFunctions(driver);
    }

    public void clickOnCustomerLink(){
        utilityFunctions.waitUntilElementPresent(customerLink);
        customerLink.click();
    }
    public void clickOnCategoryLink(){
        utilityFunctions.waitUntilElementPresent(categoryLink);
        categoryLink.click();
    }
    public void clickOnProductsLink(){
        utilityFunctions.waitUntilElementPresent(productsLink);
        productsLink.click();
    }

    public void clickOnManufacturerLink(){
        utilityFunctions.waitUntilElementPresent(manufacturerLink);
        manufacturerLink.click();
    }

    public void logOut(){
        utilityFunctions.waitUntilElementPresent(logoutLink);
        logoutLink.click();
    }

    public void clickOnNewsletterLink(){
        utilityFunctions.waitUntilElementPresent(newslettersLink);
        newslettersLink.click();
    }

    public void clickOnOrderLink(){
        utilityFunctions.waitUntilElementPresent(orderLink);
        orderLink.click();
    }

    public boolean verifyLogin(){
        utilityFunctions.waitUntilElementPresent(logoutLink);
        if(logoutLink.isDisplayed())
            return true;
        else
            return false;
    }
}
