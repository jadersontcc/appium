package iOS;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class iOSPickerView {
   
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
    public void picker_view() throws IOException{
        driver.findElementByAccessibilityId("Picker View").click();
        driver.findElementByAccessibilityId("Red color component value").sendKeys("30");
        driver.findElementByAccessibilityId("Green color component value").sendKeys("200");
        //Or as Element
        IOSElement blue = (IOSElement) driver.findElementByAccessibilityId("Blue color component value");
        blue.sendKeys("100");
        System.out.println("The value for Blue is:");
        System.out.println(blue.getText());
    }

    @AfterTest
    public void tearDown(){
        if (null != driver){
            driver.quit();
        }
    }    
}
