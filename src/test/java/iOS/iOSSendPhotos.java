package iOS;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;

public class iOSSendPhotos {
   
    public IOSDriver driver;
    File classPath, imageDir, img;
    String imgName = "IMG_0296.PNG";

    @BeforeTest
    public void setup() throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("plataformName", "iOS");
        caps.setCapability("automationName", "XCUITest");
        caps.setCapability("platformVersion", "15.2");
        caps.setCapability("deviceName", "iPhone 12 Pro");
        caps.setCapability("bundleID", "com.apple.mobileslideshow");
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
    public void send_photos() throws IOException{
        classPath = new File(System.getProperty("user.dir"));
        imageDir = new File(classPath, "/resources/images");
        img = new File(imageDir.getCanonicalPath(), "IMG_0296.PNG");
        driver.pushFile(imgName,img);
    }

    @AfterTest
    public void tearDown(){
        if (null != driver){
            driver.quit();
        }
    }    
}
