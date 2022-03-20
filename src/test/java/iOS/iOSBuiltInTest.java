package iOS;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class iOSBuiltInTest {
    
    AppiumDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("plataformName", "Android");
        caps.setCapability("automationName", "XCUITest");
        caps.setCapability("platformVersion", "14.0");
        caps.setCapability("deviceName", "iPhone 12");
        //for real devices:
        // caps.setCapability("udid", "00001234-00123456789ABC");
        // caps.setCapability("xcodeOrgId", "email or team id");
        // caps.setCapability("xcodeSigningId", "iPhone Developer");
        // necessary to build WebDriverAgent on device
        // caps.setCapability("useNewWDA", true);
        // caps.setCapability("derivedDataPath", "WebDriverAgentRunner path from XCode");
        caps.setCapability("bundleID", "com.apple.MobileAddressBook"); //can be searched by Console on Mac e.g. Contacts
        driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
    }

    @Test
    public void click_App_Button(){
        //code of testing
        driver.findElementByAccessibilityId("Add").click();
        driver.findElementByAccessibilityId("First name").sendKeys("Jon");
        driver.findElementByAccessibilityId("Last name").sendKeys("Doe");
        driver.findElementByAccessibilityId("Done").click();
    }

    @AfterTest
    public void tearDown(){
        if (null != driver){
            driver.quit();
        }
    }
}
