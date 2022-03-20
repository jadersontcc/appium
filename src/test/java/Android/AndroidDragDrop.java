package Android;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AndroidDragDrop {
   
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
    public void drag_drop(){
        //Gesture: click on View, then Drag and Drop, then move the circles
        AndroidElement views = (AndroidElement) driver.findElementByAccessibilityId("Views");
        actions = new AndroidTouchAction(driver);
        actions.tap(ElementOption.element(views)).perform();
        AndroidElement drag_drop = (AndroidElement) driver.findElementByAccessibilityId("Drag and Drop");
        actions.tap(ElementOption.element(drag_drop)).perform();

        //Drag circles
        AndroidElement drag = (AndroidElement) driver.findElementById("drag_dot_1");
        AndroidElement drop = (AndroidElement) driver.findElementById("drag_dot_2");
        
        actions.longPress(ElementOption.element(drag))
                .waitAction()
                .moveTo(ElementOption.element(drop))
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
