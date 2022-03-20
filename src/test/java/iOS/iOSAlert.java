package iOS;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;

public class iOSAlert {
   
    public IOSDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("plataformName", "iOS");
        caps.setCapability("automationName", "XCUITest");
        caps.setCapability("platformVersion", "15.2");
        caps.setCapability("deviceName", "iPhone 12 Pro");
        caps.setCapability("app", System.getProperty("user.dir")+"/apps/UIKitCatalog.app");
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
    public void alert_test(){
        driver.findElementByAccessibilityId("Alert Views").click();
        driver.findElementByAccessibilityId("Simple").click();
        //manipulating Alert with switchTo()
        driver.switchTo().alert().accept(); //Similar to Selenium action - See workoaround on https://appiumpro.com/editions/31-automating-custom-alert-buttons-on-ios
    }

    @Test
    public void Okay_Cancel_Alert(){
        driver.findElementByAccessibilityId("Alert Views").click();
        driver.findElementByAccessibilityId("Text entry").click();
        driver.switchTo().alert().sendKeys("Hello Test");
        driver.switchTo().alert().accept();
    }

    @Test
    public void Send_Text_Alert(){
        driver.findElementByAccessibilityId("Alert Views").click();
        driver.findElementByAccessibilityId("Okay / Cancel").click();
        driver.switchTo().alert().dismiss(); 
    }

    @AfterTest
    public void tearDown(){
        if (null != driver){
            driver.quit();
        }
    }    
}
