package th.co.framework.qa.ui.util;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.itextpdf.text.DocumentException;
import com.paulhammant.ngwebdriver.NgWebDriver;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
import th.co.framework.qa.ui.base.Setting;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.List;
import java.util.NoSuchElementException;

public class Common {

    public void addInputDataToReport(Map<String, Object> mapData,ExtentTest childReport) throws IOException, DocumentException {
        for (Map.Entry<String, Object> inputData : mapData.entrySet()) {
            if (inputData.getValue() != null) {
                childReport.log(Status.INFO, "Input: "+inputData.getKey() + ": " + TextColor.blueColor(inputData.getValue() == null ? "" : inputData.getValue().toString()));
            }
        }
    }

    public void addJsonDataToReport(String jsonString, String bodyType, ExtentTest childReport) {
        childReport.log(Status.INFO, bodyType);
        childReport.log(Status.INFO, MarkupHelper.createCodeBlock(jsonString, CodeLanguage.JSON));
    }

    public void addLogs(ITestContext context,String description, String expectedResult) throws IOException, DocumentException {
        context.getCurrentXmlTest().addParameter(Setting.AddLogsParameter.DESCRIPTION, description);
        context.getCurrentXmlTest().addParameter(Setting.AddLogsParameter.EXPECTED_RESULT, expectedResult);
    }


    public void screenCaptureFromBase64(ExtentTest childReport,WebDriver driver) throws IOException, DocumentException, AWTException, InterruptedException {
        Thread.sleep(1000);
        childReport.info(Setting.KEY_REPORT_SCREEN_CAPTURE + TextColor.greenColor(Setting.RESULT_TYPE.PASS.toString()), MediaEntityBuilder.createScreenCaptureFromBase64String(encodeFileToBase64Binary(driver)).build());
    }

    public void addExpectedResultToReport(ArrayList<String> expectedResult,ExtentTest childReport) throws IOException, DocumentException {
        for (String actualResult : expectedResult) {
            childReport.log(Status.INFO, Setting.KEY_REPORT_EXPECTED_RESULT + TextColor.greenColor(actualResult.replace("<br>", "")));
        }
    }

    public String getItemFromLocalStorage(String key, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript(String.format("return window.localStorage.getItem('%s');", key));
    }

    public Boolean isDisabled(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript("return arguments[0].hasAttribute(\"disabled\");", element);
    }

    public static String isPass(boolean pass) {
        return pass ? "PASS" : "FAILED";
    }

    public static String getDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm ss a").format(new Date());
    }

    public String encodeFileToBase64Binary(WebDriver driver) throws IOException {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
        return new String(encoded, StandardCharsets.US_ASCII);
    }

    private String rootEncodeFileToBase64Binary() throws IOException, AWTException {
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "png", os);
        byte[] encoded = Base64.encodeBase64(os.toByteArray());
        return new String(encoded, StandardCharsets.US_ASCII);
    }


    public void takeScreenshotFull(String name) throws IOException, AWTException {

        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "png", os);
        File outputfile = new File(name + ".png");
        ImageIO.write(image, "png", outputfile);
        FileUtils.copyFile(outputfile, new File(System.getProperty("user.dir") + "/reports/" + name));
    }

    public  void fnHighLighter(WebDriver driver, WebElement objElement) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", objElement);
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(25));
    }

    public  void fnUnHighLighter(WebDriver driver, WebElement objElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 0px;');", objElement);
    }

    public  void fnClickButton(WebDriver driver, WebElement objButton, String strButtonName) {
        NgWebDriver ngDriver = new NgWebDriver((JavascriptExecutor) driver);
        ngDriver.waitForAngularRequestsToFinish();
        if (objButton.isEnabled()) {
            fnHighLighter(driver, objButton);
            fnUnHighLighter(driver, objButton);
            objButton.click();
            ngDriver.waitForAngularRequestsToFinish();
        } else {
            throw new NoSuchElementException("Element: " + strButtonName + " Not Exist");
        }
    }

    public void fnClickDropdown(WebDriver driver, WebElement objDropdown, String strDropdownName) {
        NgWebDriver ngDriver = new NgWebDriver((JavascriptExecutor) driver);
        ngDriver.waitForAngularRequestsToFinish();
        if (objDropdown.isEnabled()) {
            fnHighLighter(driver, objDropdown);
            fnUnHighLighter(driver, objDropdown);
            objDropdown.click();
            ngDriver.waitForAngularRequestsToFinish();
        } else {
            throw new NoSuchElementException("Element: " + strDropdownName + " Not Exist");
        }
    }

    public void fnDoubleClickDropdown(WebDriver driver, WebElement objDropdown, String strDropdownName) {
        NgWebDriver ngDriver = new NgWebDriver((JavascriptExecutor) driver);
        ngDriver.waitForAngularRequestsToFinish();
        if (objDropdown.isEnabled()) {
            fnHighLighter(driver, objDropdown);
            fnUnHighLighter(driver, objDropdown);
            Actions act = new Actions(driver);
            act.doubleClick(objDropdown).perform();
            ngDriver.waitForAngularRequestsToFinish();
        } else {
            throw new NoSuchElementException("Element: " + strDropdownName + " Not Exist");
        }
    }


    public void fnSetTextInTextArea(WebDriver driver, WebElement objTextField, String strTextValue, String strTextFieldName) throws Exception {
        NgWebDriver ngDriver = new NgWebDriver((JavascriptExecutor) driver);
        ngDriver.waitForAngularRequestsToFinish();
        fnHighLighter(driver, objTextField);
        objTextField.sendKeys(strTextValue);
        if (!(objTextField.getAttribute("value").trim().equals(strTextValue))) objTextField.sendKeys(strTextValue);
        if (objTextField.getAttribute("value").trim().equals(strTextValue)) {
            fnUnHighLighter(driver, objTextField);
        } else {
            throw new Exception("Text: " + strTextFieldName + " Not Match");
        }
    }

    public  void fnSelectDropdown(WebDriver driver, WebElement objDropdown, String strValue, String strDropdownName) {
        NgWebDriver ngDriver = new NgWebDriver((JavascriptExecutor) driver);
        ngDriver.waitForAngularRequestsToFinish();
        if (objDropdown.isEnabled()) {
            fnHighLighter(driver, objDropdown);
            Select dropdown = new Select(objDropdown);
            dropdown.selectByVisibleText(strValue);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            ngDriver.waitForAngularRequestsToFinish();
            fnUnHighLighter(driver, objDropdown);
            System.out.println("Select Dropdown: " + strValue + " on Name: " + strDropdownName);
        } else {
            throw new NoSuchElementException("Dropdown: " + strDropdownName + " Not Enable");
        }

    }

    public int fnGetNumberOfColumnsInWebTable(WebDriver driver, WebElement objTable) {
        int col = 0;
        NgWebDriver ngDriver = new NgWebDriver((JavascriptExecutor) driver);
        ngDriver.waitForAngularRequestsToFinish();
        fnHighLighter(driver, objTable);
        List<WebElement> header = objTable.findElements(By.tagName("th"));
        col = header.size();
        fnUnHighLighter(driver, objTable);
        System.out.println("Get Total Column: " + col);
        return col;
    }

    public int fnGetNumberOfRowsInWebTable(WebDriver driver, WebElement objTable) {
        int rows = 0;
        NgWebDriver ngDriver = new NgWebDriver((JavascriptExecutor) driver);
        ngDriver.waitForAngularRequestsToFinish();
        fnHighLighter(driver, objTable);
        List<WebElement> rowsList = objTable.findElements(By.tagName("tr"));
        rows = rowsList.size();
        rows = rows - 1;
        fnUnHighLighter(driver, objTable);
        System.out.println("Get Total Row: " + rows);
        return rows;
    }

    public String fnGetCellDataInWebTable(WebDriver driver, WebElement objTable, String columnName, int rowNum) throws Exception {
        String cellData = "";
            try {
                NgWebDriver ngDriver = new NgWebDriver((JavascriptExecutor) driver);
                ngDriver.waitForAngularRequestsToFinish();
                fnHighLighter(driver, objTable);

                List<WebElement> header = objTable.findElements(By.tagName("th"));
                int count=0;
                int colIndex=0;
                for(WebElement h:header) {
                    if(h.getText().trim().equalsIgnoreCase(columnName)) {
                        colIndex = count;
                        break;
                    }
                    count=count+1;
                }

                List<WebElement> rowsList = objTable.findElements(By.tagName("tr"));
                WebElement rowElement =  rowsList.get(rowNum);

                List<WebElement> cellElements = rowElement.findElements(By.tagName("td"));
                WebElement cellElement =  cellElements.get(colIndex);
                fnHighLighter(driver, cellElement);
                cellData = cellElement.getText();
                fnUnHighLighter(driver, cellElement);
            }catch (Exception e) {
                e.printStackTrace();
            }
        return cellData;
    }

}
