package Steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import utils.BaseClass;

public class hooks extends BaseClass {

    public Scenario scenario = null;

    @Before()
    public void before(Scenario scenario) throws IOException {
        this.scenario = scenario;
        driver = BaseClass.getDriver();
    }

    @After(order = 1)
    public void AfterSelenium() {
        BaseClass.TearDown();
        System.out.println("Completed execution for the scenario :" + scenario.getName());
    }

    @After(order = 2)
    public void AftersaveScreenshot(Scenario scenario) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy_hh.mm.ss");
        Date curDate = new Date();
        String strDate = sdf.format(curDate);
        File screenshot_with_scenario_name = (((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
        File destPath = new File("./target/Extent_Reports/Screenshots/" + scenario.getName() + strDate + ".png");

        try {
            FileUtils.copyFile(screenshot_with_scenario_name, destPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}