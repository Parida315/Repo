package testCases;

import objectClasses.CustomerFields;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.BaseClass;
import pageObject.DashboardPage;
import pageObject.OrdersPage;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SearchOrderTest extends BaseClass {

    @Test(dataProvider = "orderData")
    public void searchOrderTest( CustomerFields customer){
       DashboardPage dashboardPage=new DashboardPage(driver);
        OrdersPage ordersPage=new OrdersPage(driver);
        dashboardPage.clickOnOrderLink();
        ordersPage.searchOrder(customer);
        Assert.assertTrue(ordersPage.verifySearchOrder());
    }


    @DataProvider(name = "orderData")
    public Object[][] ordData() {
        JSONParser jsonParser = new JSONParser();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("order_test_data.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonParser.parse(fileReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Object[][] data=new Object[1][];

        JSONObject obj = (JSONObject) jsonObject.get("customer");
        String firstName=(String)obj.get("customer-firstname");
        String lastName=(String)obj.get("customer-lastname");
        String email=(String)obj.get("customer-email");
        CustomerFields customer=new CustomerFields(firstName,lastName,email);
        data[0]=new Object[]{customer};
        return data;
    }
}
