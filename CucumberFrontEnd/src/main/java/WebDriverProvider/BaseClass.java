package WebDriverProvider;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static WebDriver driver;
    public static Properties prop;

    public BaseClass(){
        try{
            prop = new Properties();
            FileInputStream file = new FileInputStream("C:\\selenium-test\\Cucumber\\CucumberFrontEnd\\src\\main\\java\\Config\\config.properties");
            prop.load(file);
        } catch (IOException e){
            e.getMessage();
        }
    }

    public static void webDriverInitialization(){
        String BrowserName = prop.getProperty("Browser");

        if(BrowserName.equals("chrome")){
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
            desiredCapabilities.setPlatform(Platform.WINDOWS);
            desiredCapabilities.setBrowserName("Chrome");
            desiredCapabilities.setVersion("69.0.3497.100");
            desiredCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
            System.setProperty("webdriver.chrome.driver","C:\\selenium-test\\Cucumber\\CucumberFrontEnd\\src\\main\\java\\Drivers\\chromedriver.exe");
            driver = new ChromeDriver(desiredCapabilities);
        }else if (BrowserName.equals("Firefox")){
            System.setProperty("webdriver.gecko.driver","C:\\selenium-test\\Cucumber\\CucumberFrontEnd\\src\\main\\java\\Drivers");
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    public void pageLoadTimeout(){
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    public void implicitWait(){
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    }

    public void getURL(){
        driver.get(prop.getProperty("Url"));
    }

    public void explicitWaitClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver,40);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void explicitWaitelementPresent(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver,40);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void explicitWaitAllElementPresent(List<WebElement> elements){
        WebDriverWait wait = new WebDriverWait(driver,60);
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }


}
