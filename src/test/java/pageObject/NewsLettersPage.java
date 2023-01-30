package pageObject;

import objectClasses.NewsLettersFields;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.UtilityFunctions;

import java.util.List;

public class NewsLettersPage {
    @FindBy(linkText = "Create Newsletter")
    WebElement createNewsletterLink;
    @FindBy(id = "email_subject")
    WebElement newsletterSubject;
    @FindBy(id = "sender_name")
    WebElement senderName;
    @FindBy(id = "sender_email")
    WebElement senderEmail;
    @FindBy(id = "template_id")
    WebElement template;
    @FindBy(linkText = "HTML Content")
    WebElement HTMLContent;
    @FindBy(css = "iframe[aria-describedby='cke_77']")
    WebElement frame;
    @FindBy(css = "html[dir='ltr']>body>p")
    WebElement htmlTextBox;
    @FindBy(linkText = "Plain Text Content")
    WebElement plaintTextContentLink;
    @FindBy(id = "content_text")
    WebElement plainTextContent;

    @FindBy(css = "*[value='Save']")
    WebElement saveButton;
    @FindBy(id = "gui_message")
    WebElement message;
    WebDriver driver;
    UtilityFunctions utilityFunctions;

    public NewsLettersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        utilityFunctions = new UtilityFunctions(driver);
    }

    String NewSubject;

    public void createNewsLetters(NewsLettersFields newsLetter) {

        utilityFunctions.waitUntilElementPresent(createNewsletterLink);
        createNewsletterLink.click();

        utilityFunctions.waitUntilElementPresent(newsletterSubject);
        NewSubject = newsLetter.getNewsletterSubject();
        newsletterSubject.sendKeys(NewSubject);

        utilityFunctions.waitUntilElementPresent(senderName);
        senderName.sendKeys(newsLetter.getSenderName());

        utilityFunctions.waitUntilElementPresent(senderEmail);
        senderEmail.sendKeys(newsLetter.getSenderEmailAddress());

        utilityFunctions.waitUntilElementPresent(template);
        Select selectTemplate = new Select(template);
        selectTemplate.selectByVisibleText("Default Emails");

        utilityFunctions.waitUntilElementPresent(HTMLContent);
        HTMLContent.click();

        utilityFunctions.waitUntilElementPresent(frame);
        driver.switchTo().frame(frame);

        utilityFunctions.waitUntilElementPresent(htmlTextBox);
        htmlTextBox.sendKeys(newsLetter.getHTMLContent());
        driver.switchTo().defaultContent();

        utilityFunctions.waitUntilElementPresent(plaintTextContentLink);
        plaintTextContentLink.click();

        utilityFunctions.waitUntilElementPresent(plainTextContent);
        plainTextContent.sendKeys(newsLetter.getPlainTextContent());


        utilityFunctions.waitUntilElementPresent(saveButton);
        saveButton.click();

    }

    public boolean verifyCreateNewsletter() {
        utilityFunctions.waitUntilElementPresent(message);

        if (message.getText().contains("Newsletter Saved.")) {
            System.out.println(NewSubject + " Newsletter created.");
            return true;
        } else {
            System.out.println(NewSubject + " Failed to create Newsletter.");
            return false;
        }
    }


    public boolean deleteNewsLetters(List<NewsLettersFields> newsLetterList) {
        boolean result=true;
        for(NewsLettersFields newsLetter:newsLetterList){
            WebElement deleteIcon=driver.findElement(By.xpath(String.format("//a[contains(text(),'%s')]/ancestor::tr/td/span/a[3]",newsLetter.getNewsletterSubject())));
            utilityFunctions.waitUntilElementPresent(deleteIcon);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].scrollIntoView()", deleteIcon);

            deleteIcon.click();

            Alert alert = driver.switchTo().alert();
            alert.accept();
            WebElement successMessage = driver.findElement(By.cssSelector("div.success"));
            if (successMessage.isDisplayed()) {
                System.out.printf("%s is deleted, test pass\n", newsLetter.getNewsletterSubject());
                result= true;
            } else {
                System.out.printf("%s is not deleted, test faild\n", newsLetter.getNewsletterSubject());
                result= false;
            }

        }return result;
    }

}

