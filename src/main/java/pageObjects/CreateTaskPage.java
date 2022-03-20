package pageObjects;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class CreateTaskPage extends PageBase {

    public CreateTaskPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
    
    @AndroidFindBy(id = "editTextTitre")
    @iOSXCUITFindBy(iOSNsPredicate = "value == 'Title'")
    MobileElement taskNameText;

    @AndroidFindBy(id = "editTextNote")
    @iOSXCUITFindBy(iOSNsPredicate = "value == 'Description'")
    MobileElement taskDescriptionText;

    @AndroidFindBy(id = "action_save")
    @iOSXCUITFindBy(iOSNsPredicate = "value == 'Save'")
    MobileElement saveBtn;

    public void enterTaskName(String taskName){
        clear(taskNameText);
        sendText(taskNameText, taskName);
    }

    public void enterTaskDescription(String taskDescription){
        clear(taskDescriptionText);
        sendText(taskDescriptionText, taskDescription);
    }

    public void clickSaveBtn(){
        click(saveBtn);
    }

}
