import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DashboardPage extends LoginPage {


    public static boolean verifyLogin() {
        WebElement logOutLink = driver.findElement(By.cssSelector("i.fa.fa-sign-out"));
        String result = "";

        if (logOutLink.isDisplayed()) {
            System.out.println("Login test Pass");
            result = "Login test Pass";
            UtilityClass.takeScreenShot("Login"+UtilityClass.jodaDateTime());
            return true;
        } else {
            System.out.println("Login test Fail");
            result = "Login test Fail";
            return false;
        }
      // UtilityClass.writeToFile("LoginTestResult", ".txt", result);
       // UtilityClass.writeToExcelFile("ExcelReport.xlsx","Project3",1,0,result);


    }

    public static void logOut() {
        WebElement logOut = driver.findElement(By.xpath("//span[@class='user_info']/a[2]"));
        logOut.click();
    }

    public static void clickOnProductsLink() {
        WebElement productsLink = driver.findElement(By.id("nav_products"));
        UtilityClass.waitForElementPresent(productsLink);
        productsLink.click();
    }


    public static void clickOnCategoriesLink() {
        WebElement categoriesLink = driver.findElement(By.id("nav_categories"));
        UtilityClass.waitForElementPresent(categoriesLink);
        categoriesLink.click();
    }

    public static void clickOnReviewsLink() {
        WebElement reviewsLink = driver.findElement(By.xpath("//a[text()='Reviews']"));
        UtilityClass.waitForElementPresent(reviewsLink);
        reviewsLink.click();
    }

    public static void clickOnBulkPriceChangeLink() {
        WebElement bulkPriceChangeLink = driver.findElement(By.xpath("//a[text()='Bulk Price Change']"));
        UtilityClass.waitForElementPresent(bulkPriceChangeLink);
        bulkPriceChangeLink.click();
    }
}
