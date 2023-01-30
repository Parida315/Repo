package testCases;

import objectClasses.CustomerFields;
import objectClasses.OrderFields;
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

public class CreateOrderTest extends BaseClass {

    @Test(dataProvider = "orderData")
    public void createOrderTest(OrderFields order, CustomerFields customer){
        DashboardPage dashboardPage=new DashboardPage(driver);
        OrdersPage ordersPage=new OrdersPage(driver);
        dashboardPage.clickOnOrderLink();
        ordersPage.createOrder(order,customer);
        Assert.assertTrue(ordersPage.verifyCreateOrder());
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
        JSONObject obj1 = (JSONObject) jsonObject.get("orderinfo");
        String prodName=(String)obj1.get("product-name");
        String shipping=(String.valueOf(obj1.get("shipping")));
        String unitPrice=(String.valueOf(obj1.get("unitprice")));
        String quantity=(String.valueOf(obj1.get("quantity")));
        String totalTax=(String.valueOf(obj1.get("totaltax")));
        String discount=(String.valueOf(obj1.get("discount")));
        CustomerFields customer=new CustomerFields(firstName,lastName,email);
        OrderFields order=new OrderFields(quantity,unitPrice,discount,shipping,totalTax,prodName);
        data[0]=new Object[]{order,customer};
        return data;
    }
}
