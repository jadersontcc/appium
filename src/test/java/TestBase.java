import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class TestBase {
    public static AppiumDriver driver;

    public static void Android_setUp(String port, String deviceName, String platformVersion, String udid) throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("plataformName", "Android");
        caps.setCapability("platformVersion", platformVersion);
        caps.setCapability("deviceName", deviceName);
        caps.setCapability("udid", udid);
        caps.setCapability("app", System.getProperty("user.dir")+"/apps/ToDoApp.apk");
        //in case is necessary to keep previous data and no need to re-install the app:
        //remove "app" above;
        //use appPackage and appActivity;
        //or use "setCapability with "noReset"
        driver = new AndroidDriver<>(new URL("http://localhost:"+port+"/wd/hub"), caps);
    }

    public void iOS_setUp() throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("plataformName", "iOS");
        caps.setCapability("platformVersion", "15.2");
        caps.setCapability("deviceName", "iPhone 12 Pro");
        caps.setCapability("app", System.getProperty("user.dir")+"/apps/UIKitCatalog.app");
        driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
    }

    public static void tearDown(){
        if (null != driver){
            driver.quit();
        }
    }
}
