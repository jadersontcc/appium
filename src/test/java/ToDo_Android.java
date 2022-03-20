import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import utils.JsonReader;

import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CreateTaskPage;
import pageObjects.TasksListPage;

public class ToDo_Android extends TestBase {
    CreateTaskPage createTaskPage;
    TasksListPage tasksListPage;

    @DataProvider(name = "tasks data")
    public Object[][] passData() throws FileNotFoundException, IOException, ParseException{
        return JsonReader.getJSONData(System.getProperty("user.dir")+"/data/TasksData.json","Tasks Data", 2);
    }

    @Test(dataProvider = "tasks data")
    public void test1_add_task(String taskName, String taskDescription) throws MalformedURLException, InterruptedException{
        Android_setUp("7777", "Emulator","11.0","emulator-5556");
        Thread.sleep(3000);
        tasksListPage = new TasksListPage(driver);
        createTaskPage = new CreateTaskPage(driver);
        tasksListPage.clickAddTaskBtn();
        createTaskPage.enterTaskName(taskName);
        createTaskPage.enterTaskDescription(taskDescription);
        driver.hideKeyboard();
        createTaskPage.clickSaveBtn();
        tearDown();
    }

    @Test(dataProvider = "tasks data")
    public void test2_add_task(String taskName, String taskDescription) throws MalformedURLException, InterruptedException{
        Android_setUp("6666", "Emulator10","10.0","emulator-5554");
        Thread.sleep(3000);
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
