
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class CategoriesPage extends TestBase{

public static void addCategories(){
    UtilityClass.implicitWait(20);
    WebElement addCategoryLink=driver.findElement(By.linkText("Add Category"));
    UtilityClass.waitForElementPresent(addCategoryLink);
    addCategoryLink.click();

    WebElement statusCheckBox=driver.findElement(By.xpath("//input[@id='status']/following-sibling::img[@title='Enable']"));
    UtilityClass.waitForElementPresent(statusCheckBox);
    statusCheckBox.click();

    WebElement visibleCheckBox=driver.findElement(By.xpath("//input[@id='visible']/following-sibling::img[@title='Enable']"));
    UtilityClass.waitForElementPresent(visibleCheckBox);
    visibleCheckBox.click();

    WebElement categoryName=driver.findElement(By.id("name"));
    categoryName.sendKeys("Fromage");

    WebElement descriptionLink=driver.findElement(By.linkText("Description"));
    descriptionLink.click();
   // sleep(3);
    //driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@tabindex='0']")));

    WebDriverWait wait=new WebDriverWait(driver,20);
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@tabindex='0']")));
    System.out.println();

        try {
            WebElement descriptionContent=descriptionLink.findElement(By.xpath("//html/body[@spellcheck='false']/p"));
            UtilityClass.waitForElementPresent(descriptionContent);
            descriptionContent.sendKeys("Categorie pour Fromage");
        }catch (StaleElementReferenceException ex){
           ex.printStackTrace();
        }finally {
            WebElement descriptionContent=descriptionLink.findElement(By.xpath("//html/body"));
            UtilityClass.waitForElementPresent(descriptionContent);
            descriptionContent.sendKeys("Categorie pour Fromage");
        }


    driver.switchTo().defaultContent();

    WebElement saveButton=driver.findElement(By.id("cat_save"));
    saveButton.click();
}

    public static void main(String[] args) {

    browserName=UtilityClass.readConfigProperties("config-qa.properties", "browsername");
        String url = UtilityClass.readConfigProperties("config-qa.properties", "url");
        String userName = UtilityClass.readConfigProperties("config-qa.properties", "username");
        String password = UtilityClass.readConfigProperties("config-qa.properties", "password");
        LoginUserObject user = new LoginUserObject(userName, password);
        browserSetup(url);
        LoginPage.logIn(user);
        DashboardPage.clickOnCategoriesLink();
        CategoriesPage.addCategories();
    }
}

