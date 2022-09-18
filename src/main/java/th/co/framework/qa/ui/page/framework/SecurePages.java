package th.co.framework.qa.ui.page.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import th.co.framework.qa.ui.util.Common;

public class SecurePages {

    WebDriver driver;
    Wait<WebDriver> wait;

    public SecurePages(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);

    }

    @FindBy(className = "icon-signout")
    private WebElement logoutBtn;

    @FindBy(id = "flash")
    public WebElement message;

    public void clickLogout() {
        wait.until(ExpectedConditions.visibilityOf(logoutBtn));
        new Common().fnClickButton(driver, logoutBtn, "Logout");
    }

    public String getMessage() {
        wait.until(ExpectedConditions.visibilityOf(message));
        return message.getText();
    }
}



