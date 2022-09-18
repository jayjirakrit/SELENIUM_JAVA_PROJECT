package th.co.framework.qa.testcases.ui.framework;

import com.google.gson.Gson;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import th.co.framework.qa.ui.base.BaseWeb;
import th.co.framework.qa.ui.model.framework.LoginModels;
import th.co.framework.qa.ui.page.framework.LoginPages;
import th.co.framework.qa.ui.util.Common;
import th.co.framework.qa.ui.util.GetJsonDataUi;
import th.co.framework.qa.ui.util.MapData;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginFailedTestCase extends BaseWeb {

    ITestContext context;
    LoginPages loginPage;

    @BeforeClass
    public void constructor(ITestContext context) {
        this.context = context;
        this.loginPage = new LoginPages(driver, wait);
    }

    public void LoginSuccessFailed(String testCaseId) throws Exception {
        driver.get(baseUrlUi + "/login");
        LoginModels model = new Gson().fromJson(String.valueOf(GetJsonDataUi.userLoginFailedTestCase().get(testCaseId)), LoginModels.class);
        parentReport = extent.createTest(model.scenarioName, model.scenarioName);
        childReport = parentReport.createNode(model.description);
        new Common().addLogs(context, model.description, Arrays.toString(model.expectedResult.toArray()));
        new Common().addInputDataToReport(MapData.pojoToMap(model.testData.body), childReport);
        new Common().addExpectedResultToReport(model.expectedResult, childReport);
        new Common().screenCaptureFromBase64(childReport, driver);
        //Enter Username and Password
        loginPage.enterUsername(model.testData.body.username);
        loginPage.enterPassword(model.testData.body.password);
        loginPage.clickLogin();
        wait.until(ExpectedConditions.visibilityOfAllElements(loginPage.message));
        new Common().screenCaptureFromBase64(childReport, driver);
        //Validate Login Failed
        assertThat(loginPage.getMessage()).contains(model.expectedResultKey.get(0));
    }

    @Test
    public void loginFailedUsernameTest() throws Exception {
        String testCaseId = "login_failed_id_0001";
        LoginSuccessFailed(testCaseId);
    }

    @Test
    public void loginFailedPasswordTest() throws Exception {
        String testCaseId = "login_failed_id_0002";
        LoginSuccessFailed(testCaseId);
    }

}
