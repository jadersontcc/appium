import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CreateTaskPage;
import pageObjects.TasksListPage;
import utils.JsonReader;

public class ToDo_iOS extends TestBase {
    CreateTaskPage createTaskPage;
    TasksListPage tasksListPage;
    
    @DataProvider(name = "tasks data")
    public Object[][] passData() throws FileNotFoundException, IOException, ParseException{
        return JsonReader.getJSONData(System.getProperty("user.dir")+"/data/TasksData.json","Tasks Data", 2);
    }
    
    @Test(dataProvider = "tasks data")
    public void test_add_task(String taskName, String taskDescription) throws MalformedURLException{
        iOS_setUp();
        tasksListPage = new TasksListPage(driver);
        createTaskPage = new CreateTaskPage(driver);
        tasksListPage.clickAddTaskBtn();
        createTaskPage.enterTaskName(taskName);
        createTaskPage.enterTaskDescription(taskDescription);
        driver.hideKeyboard();
        createTaskPage.clickSaveBtn();
        tearDown();
    }
}
