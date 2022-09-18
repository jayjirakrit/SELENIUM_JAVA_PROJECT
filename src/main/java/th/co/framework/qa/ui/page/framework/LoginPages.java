package th.co.framework.qa.ui.page.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import th.co.framework.qa.ui.util.Common;

public class LoginPages {

    WebDriver driver;
    Wait<WebDriver> wait;

    public LoginPages(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "username")
    private WebElement userNameTxt;

    @FindBy(id = "password")
    private WebElement passwordTxt;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginBtn;

    @FindBy(id = "flash")
    public WebElement message;

    public void enterUsername(String username) throws Exception {
        userNameTxt.clear();
        new Common().fnSetTextInTextArea(driver, userNameTxt, username, "Username");
    }

    public void enterPassword(String password) throws Exception {
        passwordTxt.clear();
        new Common().fnSetTextInTextArea(driver, passwordTxt, password, "Password");
    }

    public void clickLogin() {
        new Common().fnClickButton(driver, loginBtn, "Login");
    }

    public String getMessage() {
        wait.until(ExpectedConditions.visibilityOf(message));
        return message.getText();
    }

}



