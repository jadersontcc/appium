package Android;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.functions.ExpectedCondition;

public class AndroidSendPhotos {
   
    public AndroidDriver driver;
    
    private static By backupBtn = By.id("auto_backup_switch");
    private static By touchOutsideBtn = By.id("touch_outside");
    private static By keepOffBtn = By.xpath("//*[@text='KEEP OFF']");
    private static By photo = By.xpath("//android.view.ViewGroup[contains(@content-desc,'Photo taken')]");

    File classPath, imageDir, img;

    @BeforeTest
    public void setup() throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("plataformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platformVersion", "10.0");
        caps.setCapability("deviceName", "Emulator10");
        caps.setCapability("appPackage", "com.google.android.apps.photos");
        caps.setCapability("appActivity", ".home.HomeActivity");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
    }

    @Test
    public void send_photos() throws IOException{
        classPath = new File(System.getProperty("user.dir"));
        imageDir = new File(classPath, "/resources/images");
        img = new File(imageDir.getCanonicalPath(), "IMG_0296.PNG");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(backupBtn)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(touchOutsideBtn)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(keepOffBtn)).click();

        String Android_Photo_Path = "mnt/sdcard/Pictures";
        driver.pushFile(Android_Photo_Path + "/" + img.getName(),img);
        wait.until(ExpectedConditions.numberOfElementsToBe(photo, 1));
    }

    @AfterTest
    public void tearDown(){
        if (null != driver){
            driver.quit();
        }
    }    
}
