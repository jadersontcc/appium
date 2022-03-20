package Android;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;

public class AndroidClipboard {
   
    public AndroidDriver<WebElement> driver; //works fine to send SMS via Android

    @BeforeTest
    public void setup() throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("plataformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platformVersion", "10.0");
        caps.setCapability("deviceName", "Emulator10");
        caps.setCapability("appPackage", "com.google.android.apps.messaging");
        caps.setCapability("appActivity", ".ui.ConversationListActivity");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
    }

    @Test
    public void clipboard_test(){
        String text = "Hello text from Clipboard";
        driver.setClipboardText(text);
        //MobileElement nameTxt = (MobileElement) driver.findElementByAccessibilityId("text field")
        //nameTxt.clear();
        //nameTxt.sendKeys(driver.getClipboardText());
        driver.sendSMS("555-123-4567", driver.getClipboardText());
        //Assert.assertEquals(text, nameTxt.getText()); 
    }

    @AfterTest
    public void tearDown(){
        if (null != driver){
            driver.quit();
        }
    }    
}
