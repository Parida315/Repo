import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class UtilityClass   extends TestBase{


    public static String readConfigProperties(String configFileName, String key) {
        Properties properties = new Properties();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(configFileName);
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(key);
    }

    public static void writeToFile(String fileName, String extension, String content) {
        String folderName = readConfigProperties("config-qa.properties", "foldername");
        String filePath = folderName + File.separator + fileName + extension;
        File file = new File(filePath);
        try {
            FileUtils.writeStringToFile(file, content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String jodaDateTime() {
        DateTime dateTime = new DateTime();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd-HH-mm-ss");
        return dateTime.toString(formatter);
    }

    public static String getCurrentUser(){
        return System.getProperty("user.name");
    }

    private static int timeout = Integer.parseInt(readConfigProperties("config-qa.properties", "timeout"));

    public static void staticWait(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException a) {
            a.printStackTrace();
        }
    }

    public static void implicitWait(int timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    public static void waitForElementPresent(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void fluentWait(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(element));


    }

    public static void waitAlert(int second) {
        WebDriverWait wait = new WebDriverWait(driver, second);
        wait.until(ExpectedConditions.alertIsPresent());
    }


    public static void takeScreenShot(String imageName) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File screenFile = screenshot.getScreenshotAs(OutputType.FILE);
        String folder = UtilityClass.readConfigProperties("config-qa.properties", "screenShot");
        try {
            FileUtils.copyFile(screenFile, new File(folder + File.separator + imageName + ".jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> readFromExcelFile(String fileName, String sheetName, int rowNum, int columnNum) {
        List<String> product = new ArrayList<>();
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
        int columnCount = sheet.getRow(0).getLastCellNum();

        for (int r = rowNum; r <= rowCount; r++) {
            for ( int c = columnNum; c < columnCount; c++) {
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
        }
        return product;
    }

    public static void writeToExcelFile(String fileName, String sheetName, int rowNum, int columnNum, String content) {
        String folderName=readConfigProperties("config-qa.properties", "foldername");
        String folderPath=System.getProperty("user.dir")+File.separator+folderName;
        File folder=new File(folderPath);
        if(!folder.exists()){
            folder.mkdir();
        }

        String filePath=folder+File.separator+fileName;
        File excelFile = new File(filePath);




        FileInputStream inputStream= null;
        try {
            inputStream = new FileInputStream(excelFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        XSSFWorkbook workbook= null;
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFSheet sheet=workbook.getSheet(sheetName);
        if (sheet == null)
         sheet=workbook.createSheet(sheetName);


        XSSFRow row=sheet.getRow(rowNum);
        if (row == null)
         row=sheet.createRow(rowNum);

        XSSFCell cell=row.getCell(columnNum);
        if (cell == null)
       cell = row.createCell(columnNum);

             cell.setCellValue(content);
            FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(excelFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public static void writeToExcelFile(String fileName, String sheetName,  List<String> content){
        File file=new File(fileName);
        XSSFWorkbook workbook=new XSSFWorkbook();
        if (file.exists()){
            FileInputStream inputStream= null;
            try {
                inputStream = new FileInputStream(fileName);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                workbook=new XSSFWorkbook(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
       // workbook=new XSSFWorkbook();
        XSSFSheet sheet=workbook.createSheet(sheetName);
        for(int r=0;r<content.size();r++){
            XSSFRow row=sheet.createRow(r);
            String[] cellContent=content.get(r).split(",");
            for(int c=0;c<cellContent.length;c++){
                XSSFCell cell=row.createCell(c);
                cell.setCellValue(cellContent[c]);
            }
        }
        FileOutputStream outputStream= null;
        try {
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}