package Android;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AndroidSwipe {
   
    public AppiumDriver driver;
    public AndroidTouchAction actions;

    @BeforeTest
    public void setup() throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("plataformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platformVersion", "10.0");
        caps.setCapability("deviceName", "Emulator10"); //for real devices change to device name from 'adb devices'
        caps.setCapability("app", System.getProperty("user.dir")+"/apps/ApiDemos.apk");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
    }

    @Test
    public void swipe_test(){
        //Gesture: click on View, then Galleryp, then swipe through the photos
        AndroidElement views = (AndroidElement) driver.findElementByAccessibilityId("Views");
        actions = new AndroidTouchAction(driver);
        actions.tap(ElementOption.element(views)).perform();
        AndroidElement gallery = (AndroidElement) driver.findElementByAccessibilityId("Gallery");
        actions.tap(ElementOption.element(gallery)).perform();
        AndroidElement photos = (AndroidElement) driver.findElementByAccessibilityId("1. Photos");
        actions.tap(ElementOption.element(photos)).perform();

        //Swipe
        AndroidElement pic1 = (AndroidElement) driver.findElementsByClassName("android.widget.ImageView").get(0);
        actions.press(ElementOption.element(pic1))
                .waitAction()
                .moveTo(PointOption.point(-20,500))
                .release()
                .perform();
    }

    @AfterTest
    public void tearDown(){
        if (null != driver){
            driver.quit();
        }
    }    
}
