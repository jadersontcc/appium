package Android;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AndroidBuiltInTest {
    AppiumDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("plataformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platformVersion", "10.0");
        caps.setCapability("deviceName", "Emulator10");
        caps.setCapability("appPackage", "com.android.calculator2");
        caps.setCapability("appActivity", ".Calculator");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
    } 

    @Test
    public void click_test(){
        driver.findElementById("digit_1").click();
        driver.findElementById("op_add").click();
        driver.findElementById("digit_3").click();
        driver.findElementById("eq").click();
        Assert.assertEquals(driver.findElementById("result"), 4);
    }

    @AfterTest
    public void tearDown(){
        if (null != driver){
            driver.quit();
        }
    }
}
