package th.co.framework.qa.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

public class Browser {
    boolean isHeadless = true;
    boolean isRemote;

    public Browser(boolean isHeadless) {
        this.isHeadless = isHeadless;
        this.isRemote = (Boolean.parseBoolean(System.getProperty("IS_REMOTE"))) ? Boolean.parseBoolean(System.getProperty("IS_REMOTE")) : false;
    }

    public WebDriver internetExplorer() {
        WebDriverManager.iedriver().setup();
        InternetExplorerOptions capabilityIE = new InternetExplorerOptions();
        return new InternetExplorerDriver(capabilityIE);
    }

    public WebDriver googleChrome() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (isHeadless) {
            options.addArguments("headless");
            options.setHeadless(true);
            options.addArguments("window-size=1280,800");
        }
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-popup-blocking");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        if (isRemote) {
            return getRemoteDriver(options);
        } else
            return new ChromeDriver(options);
    }

    public WebDriver edge() throws MalformedURLException {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        if (isHeadless) {
            options.addArguments("headless");
            options.setHeadless(true);
            options.addArguments("window-size=1280,800");
        }
        options.addArguments("--disable-notifications");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

        if (isRemote) {
            return getRemoteDriver(options);
        } else
            return new EdgeDriver(options);
    }

    public WebDriver firefox() throws MalformedURLException {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (isHeadless) {
            options.addArguments("headless");
            options.setHeadless(true);
            options.addArguments("window-size=1280,800");
        }
        options.addArguments("--disable-notifications");
        options.setAcceptInsecureCerts(true);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        if (isRemote) {
            return getRemoteDriver(options);
        } else
            return new FirefoxDriver(options);
    }

    public WebDriver safari() {
        WebDriverManager.safaridriver().setup();
        SafariOptions capability = new SafariOptions();
        capability.setCapability("safari.cleanSession", true);

        return new SafariDriver(capability);
    }

    private WebDriver getRemoteDriver(Capabilities options) throws MalformedURLException {
        String host = (System.getProperty("HUB_HOST") != null) ? System.getProperty("HUB_HOST") : "localhost";
        String url = "http://" + host + ":4444/wd/hub";
        return new RemoteWebDriver(new URL(url), options);
    }
}
