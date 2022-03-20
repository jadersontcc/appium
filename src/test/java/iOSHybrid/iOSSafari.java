package iOSHybrid;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;

public class iOSSafari {
    
    IOSDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("plataformName", "iOS");
        caps.setCapability("platformVersion", "15.2");
        caps.setCapability("deviceName", "iPhone 12 Pro");
        caps.setCapability("browserName", "Safari");
        caps.setCapability("safari:useSimulator", true);
        //for real devices:
        // caps.setCapability("udid", "00001234-00123456789ABC");
        // caps.setCapability("xcodeOrgId", "email or team id");
        // caps.setCapability("xcodeSigningId", "iPhone Developer");
        // necessary to build WebDriverAgent on device
        // caps.setCapability("useNewWDA", true);
        // caps.setCapability("derivedDataPath", "WebDriverAgentRunner path from XCode");
        driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
    }

    @Test
    public void user_login(){
        driver.get("https://the-internet.herokuapp.com/login");
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        WebElement username = driver.findElementByCssSelector("input#username"); //<input> + id="username"
        username.sendKeys("tomsmith");
        WebElement password = driver.findElementByCssSelector("input#password"); //<input> + id="password"
        password.sendKeys("SuperSecretPassword!");
        WebElement loginBtn = driver.findElementByCssSelector("button.radius"); //<button> + class="radius"
        loginBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("secure"));
        System.out.println(driver.getCurrentUrl());
    }

    @AfterTest
    public void tearDown(){
        if (null != driver){
            driver.quit();
        }
    }
}
