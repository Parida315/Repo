import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends DashboardPage {

    public static boolean addMultiProduct(List<ProductsPageObject> productList) {
        String result = "";

        List<String> resultF = new ArrayList<>();
        boolean testResult=true;

        for (ProductsPageObject product : productList) {

            WebElement addProductLink = driver.findElement(By.xpath("//a[text()='Add Product']"));
            UtilityClass.waitForElementPresent(addProductLink);
            addProductLink.click();

            WebElement productName = driver.findElement(By.id("name"));
            productName.sendKeys(product.getProductName());

            WebElement manufacturer = driver.findElement(By.id("manufacturer"));
            UtilityClass.waitForElementPresent(manufacturer);
            Select selectManufacturer = new Select(manufacturer);
            selectManufacturer.selectByIndex(2);

            WebElement condition = driver.findElement(By.id("condition"));
            UtilityClass.waitForElementPresent(condition);
            Select selectCondition = new Select(condition);
            selectCondition.selectByValue("new");

            WebElement weightField = driver.findElement(By.id("product_weight"));
            weightField.sendKeys(product.getProductWeight());

            WebElement productCodeField = driver.findElement(By.cssSelector("input[id='product_code']"));
            productCodeField.sendKeys(product.getProductCode());

            WebElement productCodeCheckBox = driver.findElement(By.xpath("(//img[@title='Disable'])[2]"));
            productCodeCheckBox.click();

            WebElement productWidth = driver.findElement(By.id("product_width"));
            productWidth.sendKeys(product.getProductWidth());

            WebElement dimensionUnit = driver.findElement(By.id("dimension_unit"));
            Select selectDimensionUnit = new Select(dimensionUnit);
            selectDimensionUnit.selectByValue("cm");

            WebElement productHeight = driver.findElement(By.id("product_height"));
            productHeight.sendKeys(product.getProductHeight());

            WebElement productDepth = driver.findElement(By.id("product_depth"));
            productDepth.sendKeys(product.getProductDepth());

            WebElement stockLevelField = driver.findElement(By.id("stock_level"));
            stockLevelField.sendKeys(product.getStockLevel());

            WebElement stockLevelWarningField = driver.findElement(By.id("stock_warning"));
            stockLevelWarningField.sendKeys(product.getStockLevelWarning());

            WebElement UPCCode = driver.findElement(By.id("upc"));
            UPCCode.sendKeys(product.getUPCCode());

            WebElement EANCode = driver.findElement(By.id("ean"));
            EANCode.sendKeys(product.getEANCode());

            WebElement JANCode = driver.findElement(By.id("jan"));
            JANCode.sendKeys(product.getJANCode());

            WebElement ISBNCode = driver.findElement(By.id("isbn"));
            ISBNCode.sendKeys(product.getISBNCode());

            WebElement GTINCode = driver.findElement(By.id("gtin"));
            GTINCode.sendKeys(product.getGTINCode());

            WebElement MPNCode = driver.findElement(By.id("mpn"));
            MPNCode.sendKeys(product.getMPNCode());


            WebElement googleCategory = driver.findElement(By.xpath("//a[@class=\"chosen-single\"]/span[text()=\"Please Select …\"]"));
            UtilityClass.waitForElementPresent(googleCategory);
            googleCategory.click();

            //driver.findElement(By.xpath("//div[@id=\"google_category_chosen\"]/child::a")).click();
            // driver.findElement(By.xpath("//div[@id=\"google_category_chosen\"]/child::a/span")).click();
            // driver.findElement(By.xpath("//a[@class=\"chosen-single\"]/span")).click();

            List<WebElement> googleOption = driver.findElements(By.cssSelector("li[class=\"active-result\"]"));
            googleOption.get(3).click();


            WebElement pricingLink = driver.findElement(By.cssSelector("div[id=\"tab_pricing\"]"));
            pricingLink.click();

            WebElement salePrice = driver.findElement(By.id("sale_price"));
            salePrice.sendKeys(product.getSalePrice());

            WebElement saveButton = driver.findElement(By.xpath("//input[@value=\"Save\"]"));
            saveButton.click();
            WebElement successMessage = driver.findElement(By.cssSelector(".success"));

            if (successMessage.isDisplayed()) {
                System.out.printf("Product %s is added, test Pass\n", product.getProductName());
                result = String.format("Product %s is added, test Pass", product.getProductName());
                UtilityClass utilityClass=new UtilityClass();
                utilityClass.takeScreenShot(product.getProductName()+UtilityClass.jodaDateTime());
                 testResult=true;
            } else {
                System.out.printf("Product %s is not added, test Fail\n", product.getProductName());
                result = String.format("Product %s is not added, test Fail", product.getProductName());
                testResult= false;
            }

            resultF.add(result);
        }
        UtilityClass.writeToFile("AddProductTestResult", ".txt", resultF.toString());
        return testResult;
    }
    public static void addMultiProduct(String fileName, String sheetName) {
        List<String> resultF = new ArrayList<>();
        String result = null;

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        //System.out.println(rowCount);
        int colomnCount = sheet.getRow(0).getLastCellNum();
       // System.out.println(colomnCount);
                int i=1;
            for (int r = 1; r <=rowCount; r++) {
            List<String> product = new ArrayList<>();
            for (int c = 0; c < colomnCount; c++) {
                XSSFCell cell = sheet.getRow(r).getCell(c);
                String value = null;
                switch (cell.getCellType()) {
                    case STRING:
                        value = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        value = String.valueOf(cell.getNumericCellValue());
                        break;
                }
                product.add(value);
            }
            WebElement productsLink = driver.findElement(By.id("nav_products"));
            productsLink.click();


            WebElement addProductLink = driver.findElement(By.cssSelector("#tab_control > div:nth-child(2)"));
            addProductLink.click();

            WebElement productNameField = driver.findElement(By.id("name"));
            productNameField.sendKeys(product.get(0));

            WebElement manufacturer = driver.findElement(By.id("manufacturer"));
            Select selectManufacturer = new Select(manufacturer);
            selectManufacturer.selectByIndex(2);


            WebElement condition = driver.findElement(By.id("condition"));
            Select selectCondition = new Select(condition);
            selectCondition.selectByValue("new");

            WebElement productCodeField = driver.findElement(By.cssSelector("input[id='product_code']"));
            productCodeField.sendKeys(product.get(1));

            WebElement weightField = driver.findElement(By.id("product_weight"));
            weightField.sendKeys(product.get(2));

            WebElement productWidth = driver.findElement(By.id("product_width"));
            productWidth.sendKeys(product.get(3));

            WebElement dimensionUnit = driver.findElement(By.id("dimension_unit"));
            Select selectDimensionUnit = new Select(dimensionUnit);
            selectDimensionUnit.selectByValue("cm");

            WebElement productHeight = driver.findElement(By.id("product_height"));
            productHeight.sendKeys(product.get(4));

            WebElement productDepth = driver.findElement(By.id("product_depth"));
            productDepth.sendKeys(product.get(5));

            WebElement stockLevelField = driver.findElement(By.id("stock_level"));
            stockLevelField.sendKeys(product.get(6));

            WebElement stockLevelWarningField = driver.findElement(By.id("stock_warning"));
            stockLevelWarningField.sendKeys(product.get(7));

            WebElement UPCCode = driver.findElement(By.id("upc"));
            UPCCode.sendKeys(product.get(8));

            WebElement EANCode = driver.findElement(By.id("ean"));
            EANCode.sendKeys(product.get(9));

            WebElement JANCode = driver.findElement(By.id("jan"));
            JANCode.sendKeys(product.get(10));

            WebElement ISBNCode = driver.findElement(By.id("isbn"));
            ISBNCode.sendKeys(product.get(11));

            WebElement GTINCode = driver.findElement(By.id("gtin"));
            GTINCode.sendKeys(product.get(12));

            WebElement MPNCode = driver.findElement(By.id("mpn"));
            MPNCode.sendKeys(product.get(13));


            //WebElement googleCategory=driver.findElement(By.xpath("//a[@class=\"chosen-single\"]/span[text()=\"Please Select …\"]"));
            //googleCategory.click();

            driver.findElement(By.xpath("//div[@id=\"google_category_chosen\"]/child::a")).click();
            // driver.findElement(By.xpath("//div[@id=\"google_category_chosen\"]/child::a/span")).click();
            // driver.findElement(By.xpath("//a[@class=\"chosen-single\"]/span")).click();

            UtilityClass.staticWait(1);

            List<WebElement> googleOption = driver.findElements(By.cssSelector("li[class=\"active-result\"]"));
            googleOption.get(3).click();
            UtilityClass.staticWait(1);

            WebElement pricingLink = driver.findElement(By.cssSelector("div[id=\"tab_pricing\"]"));
            pricingLink.click();

            WebElement salePrice = driver.findElement(By.id("sale_price"));
            salePrice.sendKeys(product.get(14));

            WebElement saveButton = driver.findElement(By.xpath("//input[@value=\"Save\"]"));
            saveButton.click();
            WebElement successMessage = driver.findElement(By.cssSelector(".success"));

            if (successMessage.isDisplayed()) {
                System.out.printf("Product %s is added, test Pass\n", product.get(0));
                result = String.format("Product %s is added, test Pass", product.get(0));

                UtilityClass.takeScreenShot(product.get(0)+UtilityClass.jodaDateTime());


            } else {
                System.out.printf("Product %s is not added, test Fail\n", product.get(0));
                result = String.format("Product %s is not added, test Fail", product.get(0));
            }

            resultF.add(result);
                UtilityClass.writeToExcelFile("ExcelReport.xlsx","Project3",i,1,result);
                i++;

            }
        UtilityClass.writeToFile("AddProductTestResult", ".txt", resultF.toString());

    }



    public static void deleteProduct(List<ProductsPageObject> productList) {
        List<String > resultF=new ArrayList<>();
        int i=1;
        for (ProductsPageObject product : productList) {
            WebElement checkBox = driver.findElement(By.xpath(String.format("//a[text()='%s']/preceding::td[1]", product.getProductName())));
            checkBox.click();
            WebElement withSelected = driver.findElement(By.cssSelector("select[class=\"textbox\"]"));
            UtilityClass.waitForElementPresent(withSelected);
            Select select = new Select(withSelected);
            select.selectByVisibleText("Delete");
            WebElement save = driver.findElement(By.cssSelector("input[value=\"Save\"]"));
            save.click();
            WebElement successMessage = driver.findElement(By.xpath("//div[@class=\"success\"]"));
            String message = successMessage.getText();
            String result="";
            if (message.contains("Product(s) successfully deleted.")) {
                System.out.printf("Product %s is deleted, test Pass\n", product.getProductName());
                result=String.format("Product %s is deleted, test Pass", product.getProductName());
            } else {
                System.out.printf("Product %s is not deleted, test Fail\n", product.getProductName());
                result=String.format("Product %s is not deleted, test Fail", product.getProductName());
            }
           resultF.add(result);
            UtilityClass.writeToExcelFile("ExcelReport.xlsx","Project3",i,6,result);
            i++;
        }
        UtilityClass.writeToFile("DeleteProductTestResult" ,".txt", resultF.toString());
    }

    public static void deleteProduct(String fileName, String sheetName) {
        List<String > resultF=new ArrayList<>();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        int i=1;
        for (int r = 1; r <=rowCount; r++) {
            XSSFCell cell = sheet.getRow(r).getCell(0);
            String value = null;
            switch (cell.getCellType()) {
                case STRING:
                    value = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    value = String.valueOf(cell.getNumericCellValue());
                    break;
            }
            WebElement checkBox = driver.findElement(By.xpath(String.format("//a[text()='%s']/preceding::td[1]", value)));
            checkBox.click();
            WebElement withSelected = driver.findElement(By.cssSelector("select[class=\"textbox\"]"));
            UtilityClass.waitForElementPresent(withSelected);
            Select select = new Select(withSelected);
            select.selectByVisibleText("Delete");
            WebElement save = driver.findElement(By.cssSelector("input[value=\"Save\"]"));
            save.click();
            WebElement successMessage = driver.findElement(By.xpath("//div[@class=\"success\"]"));
            String message = successMessage.getText();
            String result="";
            if (message.contains("Product(s) successfully deleted.")) {
                System.out.printf("Product %s is deleted, test Pass\n", value);
                result=String.format("Product %s is deleted, test Pass", value);
            } else {
                System.out.printf("Product %s is not deleted, test Fail\n", value);
                result=String.format("Product %s is not deleted, test Fail", value);
            }
            resultF.add(result);
            UtilityClass.writeToExcelFile("ExcelReport.xlsx","Project3",i,6,result);
            i++;
        }
        UtilityClass.writeToFile("DeleteProductTestResult" ,".txt", resultF.toString());
    }
}
