package iOS;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class iOSSlider {
   
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
    public void slider_test(){
        driver.findElementByAccessibilityId("Sliders").click();
        IOSElement slider = (IOSElement) driver.findElementByXPath("//XCUIElementTypeSlider");
        slider.setValue("0%");
        slider.setValue("1%");
        slider.setValue("0.5%");
        Assert.assertEquals(slider.getAttribute("value"), "48%");
    }

    @AfterTest
    public void tearDown(){
        if (null != driver){
            driver.quit();
        }
    }    
}
