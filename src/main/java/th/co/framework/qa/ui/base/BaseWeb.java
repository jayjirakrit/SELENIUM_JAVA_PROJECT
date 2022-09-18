package th.co.framework.qa.ui.base;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import th.co.framework.qa.common.Browser;
import th.co.framework.qa.ui.model.ConfigModel;
import th.co.framework.qa.ui.util.Common;
import th.co.framework.qa.ui.util.GetJsonDataUi;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class BaseWeb {

    public  WebDriver driver;
    public  WebDriverWait wait;
    public  ExtentSparkReporter sparkHtmlReporter;
    public  ExtentReports extent;
    public  ExtentTest parentReport;
    public  ExtentTest childReport;
    public String uiReportName = "";
    public  String baseUrlUi = "";

    private void configuration(String webBrowserType,boolean isHeadless) throws MalformedURLException {
        baseUrlUi = "http://the-internet.herokuapp.com";

        switch (webBrowserType) {
            case Setting.WebBrowser.IE:
                driver = new Browser(isHeadless).internetExplorer();
                break;
            case Setting.WebBrowser.CHROME:
                driver = new Browser(isHeadless).googleChrome();
                break;
            case Setting.WebBrowser.FIREFOX:
                driver = new Browser(isHeadless).firefox();
                break;
            case Setting.WebBrowser.MICROSOFT_EDGE:
                driver = new Browser(isHeadless).edge();
                break;
            case Setting.WebBrowser.SAFARI:
                driver = new Browser(isHeadless).safari();
                break;
        }
        ConfigModel dataModel = new Gson().fromJson(String.valueOf(GetJsonDataUi.getConfig()), ConfigModel.class);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(dataModel.implicitly_wait));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(dataModel.page_load_timeout));
        wait = new WebDriverWait(driver, Duration.of(dataModel.web_driver_wait, ChronoUnit.SECONDS));
    }

    @Parameters({"web_browser", "suite_name","is_headless"})
    @BeforeTest
    public void beforeTestBase(String webBrowserType, String suiteName,boolean isHeadless) throws DocumentException, IOException {
        configuration(webBrowserType,isHeadless);
        String date = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss_a").format(new Date());
        uiReportName = "UI-" + webBrowserType.toUpperCase() + "-" + suiteName.toUpperCase() + "-" + date + ".html";
        sparkHtmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + uiReportName);
        sparkHtmlReporter.config().setTheme(Theme.STANDARD);
        sparkHtmlReporter.config().setCss("css-string");
        sparkHtmlReporter.config().setEncoding("utf-8");
        sparkHtmlReporter.config().setJs("js-string");
        sparkHtmlReporter.config().setTimelineEnabled(true);
        sparkHtmlReporter.config().setProtocol(Protocol.HTTPS);
        sparkHtmlReporter.config().setDocumentTitle(Setting.TEAM_NAME + " Application Report build: " + Setting.DATE_TIME);
        sparkHtmlReporter.config().setReportName("Report name: " + Setting.TEAM_NAME + " Application Report build: " + Setting.DATE_TIME);
        extent = new ExtentReports();
        extent.attachReporter(sparkHtmlReporter);
        extent.setSystemInfo("OS", System.getProperty("os.name").toLowerCase());
        extent.setSystemInfo("Project", Setting.PROJECT_NAME);
        extent.setSystemInfo("Team", Setting.TEAM_NAME);
        extent.setSystemInfo("URL", baseUrlUi);
        extent.setSystemInfo("Build number", System.getProperty("buildNumber"));
        extent.setSystemInfo("Test Version", "1.1.0");
        extent.setSystemInfo("Date Time", Setting.DATE_TIME);
        extent.setSystemInfo("Web browser", webBrowserType.toUpperCase());
        sparkHtmlReporter.config().setProtocol(Protocol.HTTPS);
        sparkHtmlReporter.config().setReportName(Setting.TEAM_NAME + " Application Report build: " + Setting.DATE_TIME);
        sparkHtmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
        extent.attachReporter(sparkHtmlReporter);

    }

    @Parameters({"web_browser"})
    @AfterTest
    public void afterTestBase(String webBrowserType) throws IOException, DocumentException {
        extent.flush();
        try {
            driver.close();
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Parameters({"web_browser",})
    @AfterMethod
    public void getResult(ITestResult result, ITestContext context, String webBrowserType) throws Exception {
        String testcaseID = context.getCurrentXmlTest().getParameter(Setting.AddLogsParameter.TEST_CASE_ID);
        String description = context.getCurrentXmlTest().getParameter(Setting.AddLogsParameter.DESCRIPTION);
        String expectedResult = context.getCurrentXmlTest().getParameter(Setting.AddLogsParameter.EXPECTED_RESULT);

        if (result.getStatus() == ITestResult.FAILURE) {
            childReport.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));
            childReport.fail(result.getThrowable());
            childReport.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(new Common().encodeFileToBase64Binary(driver)).build());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            childReport.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
        } else {
            childReport.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
            childReport.skip(result.getThrowable());
        }
    }

}
