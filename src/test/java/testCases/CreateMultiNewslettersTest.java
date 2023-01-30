package testCases;

import objectClasses.NewsLettersFields;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.BaseClass;
import pageObject.DashboardPage;
import pageObject.NewsLettersPage;


import java.util.ArrayList;
import java.util.List;

public class CreateMultiNewslettersTest extends BaseClass {

    DashboardPage dashboardPage;
    NewsLettersPage newsLettersPage;
    @Test(dataProvider = "newsletterData")
    public void createMultiNewslettersTest(NewsLettersFields newsletter){
        dashboardPage =new DashboardPage(driver);
        newsLettersPage =new NewsLettersPage(driver);
        dashboardPage.clickOnNewsletterLink();
        newsLettersPage.createNewsLetters(newsletter);
        Assert.assertTrue(newsLettersPage.verifyCreateNewsletter());
    }

    List<NewsLettersFields> newsLettersList=new ArrayList<>();
    @Test
    public void deleteMultiNewsletters(){
        dashboardPage.clickOnNewsletterLink();
        Assert.assertTrue( newsLettersPage.deleteNewsLetters(newsLettersList));
    }
    @DataProvider(name="newsletterData")
    public Object[][] newsData(){
        Object[][]  data=new Object[3][];

        for (int i=0;i< data.length;i++){
            NewsLettersFields newsLettersFields=new NewsLettersFields(RandomStringUtils.randomAlphabetic(8),
                    RandomStringUtils.randomAlphabetic(4), RandomStringUtils.randomAlphabetic(4)+"@yahoo.fr",
                    RandomStringUtils.randomAlphabetic(30),RandomStringUtils.randomAlphabetic(50));
            data[i]= new Object[]{newsLettersFields};


            newsLettersList.add(newsLettersFields);
        }
        return data;
    }
}
