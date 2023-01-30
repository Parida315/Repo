package pageObject;

import objectClasses.CustomerFields;
import objectClasses.OrderFields;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.UtilityFunctions;

import java.util.List;


public class OrdersPage {
    @FindBy(linkText = "Create Order")
    WebElement createOrderTab;
    @FindBy(linkText = "Billing")
    WebElement billingTab;
    @FindBy(id = "sum_name")
    WebElement findCustomerField;
    @FindBy(linkText = "Delivery")
     WebElement deliveryTab;
    @FindBy(linkText = "Copy from Billing Address")
     WebElement copyAddress;
    @FindBy(linkText = "Inventory")
    WebElement inventoryTab;
    @FindBy(xpath = "//input[@rel='product_quantity' and @original='1']")
    WebElement quantityField;
    @FindBy(id = "ajax_name")
     WebElement productName;
    @FindBy(css = "td>a[title='Add']")
     WebElement plusIcon;
    @FindBy(css = "a[target='tax-list']>i[title='Add']")
    WebElement plusIcon2;
    @FindBy(css = "input[id='ajax_price']")
    WebElement unitPrice;
    @FindBy(id = "discount_type")
    WebElement discountDrop;
    @FindBy(css = "input[id='discount']")
    WebElement discountField;
    @FindBy(css = "input[id='shipping']")
    WebElement shipping;
    @FindBy(css = "select[rel='tax_id']")
    WebElement taxDrop;
    @FindBy(xpath = "//tr[@class='inline-add']//input[@rel='amount']")
    WebElement taxField;
   @FindBy(css = "*[value='Save']")
     WebElement saveButton;
   @FindBy(css = "div[id='gui_message']")
   WebElement successMessage;
    @FindBy(linkText = "Search Orders")
    WebElement searchOrdersTab;
    @FindBy(id = "search_customer_id")
    WebElement customerNameField;
    @FindBy(css = "*[value='Search']")
    WebElement searchButton;


    WebDriver driver;
    UtilityFunctions utilityFunctions;

    public OrdersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utilityFunctions=new UtilityFunctions(driver);
    }
    public void createOrder(OrderFields order, CustomerFields customer){
        utilityFunctions.waitUntilElementPresent(createOrderTab);
        createOrderTab.click();
        utilityFunctions.waitUntilElementPresent(billingTab);
        billingTab.click();
        utilityFunctions.waitUntilElementPresent(findCustomerField);
        findCustomerField.sendKeys(customer.getEmail());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement nList=driver.findElement(By.cssSelector("ul>li>span[class='jqac-link']"));
        utilityFunctions.waitUntilElementPresent(nList);
        Actions a =new Actions(driver);
        a.moveToElement(nList).click().build().perform();
        utilityFunctions.waitUntilElementPresent(deliveryTab);
        deliveryTab.click();
        utilityFunctions.waitUntilElementPresent(copyAddress);
        copyAddress.click();
        utilityFunctions.waitUntilElementPresent(inventoryTab);
        inventoryTab.click();
        utilityFunctions.waitUntilElementPresent(quantityField);
        quantityField.clear();
        quantityField.sendKeys(String.valueOf(order.getQuantity()));
        utilityFunctions.waitUntilElementPresent(productName);
        productName.sendKeys(order.getProductName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement pList=driver.findElement(By.cssSelector("ul>li>span[class='jqac-link']"));
        utilityFunctions.waitUntilElementPresent(pList);
        Actions ac =new Actions(driver);
        ac.moveToElement(pList).click().build().perform();
        utilityFunctions.waitUntilElementPresent(unitPrice);
        unitPrice.clear();
        unitPrice.sendKeys(String.valueOf(order.getUnitePrice()));
        utilityFunctions.waitUntilElementPresent(plusIcon);
        plusIcon.click();
        utilityFunctions.waitUntilElementPresent(discountDrop);
        Select select=new Select(discountDrop);
        select.selectByValue("p");
        utilityFunctions.waitUntilElementPresent(discountField);
        discountField.sendKeys(String.valueOf(order.getDiscount()));
        utilityFunctions.waitUntilElementPresent(shipping);
        shipping.sendKeys(String.valueOf(order.getShipping()));
        utilityFunctions.waitUntilElementPresent(taxDrop);
        Select select1=new Select(taxDrop);
        select1.selectByVisibleText("Nijat: VAT");
        utilityFunctions.waitUntilElementPresent(taxField);
        taxField.sendKeys(String.valueOf(order.getTotalTax()));
        utilityFunctions.waitUntilElementPresent(plusIcon2);
        plusIcon2.click();
        utilityFunctions.waitUntilElementPresent(saveButton);
        saveButton.click();
    }
    public boolean verifyCreateOrder(){
        utilityFunctions.waitUntilElementPresent(successMessage);
        if(successMessage.getText().contains("Order successfully created."))
            return true;
        else return false;
    }

    public void searchOrder(CustomerFields customer){
        utilityFunctions.waitUntilElementPresent(searchOrdersTab);
        searchOrdersTab.click();
        utilityFunctions.waitUntilElementPresent(customerNameField);
        customerNameField.sendKeys(customer.getFirsName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<WebElement> pList=driver.findElements(By.cssSelector("span[class='jqac-link']"));
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfAllElements(pList));
        Actions ac =new Actions(driver);
        for(WebElement element:pList){
            // System.out.println(element.getText());
            if(element.getText().contains(customer.getFirsName())&&element.getText().contains(customer.getLastName())){
                System.out.println(element.getText()+"---");
                ac.moveToElement(element).click().build().perform();
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }

        utilityFunctions.waitUntilElementPresent(searchButton);
        searchButton.click();
    }

    public boolean verifySearchOrder(){
        utilityFunctions.waitUntilElementPresent(successMessage);
        if (successMessage.getText().contains("Orders have been found that match your search criteria."))
            return true;
        else return false;
    }



}
