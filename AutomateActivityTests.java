import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class AutomateActivityTests {

    private static AndroidDriver driver;

    @BeforeAll
    public static void setup() throws MalformedURLException {
        // Desired capabilities setup
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:deviceName", "HMD Global Nokia 6.1 Plus");
        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:automationName", "uiautomator2");
        capabilities.setCapability("appium:platformVersion", "10.0");

        URL url = URI.create("http://127.0.0.1:4723/wd/hub").toURL();
        driver = new AndroidDriver (url, capabilities);
    }

    @TestFactory
    public List<DynamicTest> testActivityProcesses() throws IOException, CsvException {
        // Read the CSV file and create a list of tests
        CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Karthikeya Akhandam\\Documents\\BlazeTech\\activityData2.csv"));
        List<String[]> lines = reader.readAll();
        reader.close();

        return lines.stream()
                .skip(1) // Skip the header row
                .map(line -> DynamicTest.dynamicTest("Activity test for " + line[0] + " " + line[1],
                        () -> {
                            /*// Skip the third test (index 0 to 19 based on csv file index)
                            if (lines.indexOf(line) <= 1 && lines.indexOf(line) <= 21) {
                                System.out.println("Skipping test for " + line[0] + " " + line[1]);
                                return; // Skip this test
                            }
                            //End of skip*/
                            testActivityProcess(
                                    line[0], line[1], line[2], line[3], line[4]
                            );
                        }))
                .toList();
    }

    private void testActivityProcess(String s, String s1, String s2, String s3, String s4) throws InterruptedException {
        int t1 = Integer.parseInt(s2);
        int laps = Integer.parseInt(s3);
        int miss = Integer.parseInt(s4);
        Thread.sleep(1000);

        switch (s) {
            case "Cricket" -> {
                switch (s1) {
                    case "Retrieval" -> activity2(driver, laps, 1, 1, 0);
                    case "RH 4" -> activity(t1, driver, laps, 4, miss, 1, 0);
                    case "RH 6" -> activity(t1, driver, laps, 2, miss, 1, 0);
                    case "RW 4" -> activity(t1, driver, laps, 5, miss, 1, 0);
                    case "RW 6" -> activity(t1, driver, laps, 3, miss, 1, 0);
                    case "SH 4" -> activity(t1, driver, laps, 6, miss, 1, 0);
                    case "SH 6" -> activity(t1, driver, laps, 2, miss, 1, 1);
                    case "SW 4" -> activity(t1, driver, laps, 5, miss, 1, 1);
                    case "SW 6" -> activity(t1, driver, laps, 3, miss, 1, 1);
                }
            }
            case "Tennis" -> {
                switch (s1) {
                    case "RH 4" -> activity(t1, driver, laps, 1, miss, 2, 0);
                    case "RH 6" -> activity(t1, driver, laps, 4, miss, 2, 0);
                    case "RW 4" -> activity(t1, driver, laps, 2, miss, 2, 0);
                    case "RW 6" -> activity(t1, driver, laps, 5, miss, 2, 0);
                    case "SH 4" -> activity(t1, driver, laps, 3, miss, 2, 0);
                    case "SH 6" -> activity(t1, driver, laps, 6, miss, 2, 0);
                    case "SW 4" -> activity(t1, driver, laps, 3, miss, 2, 1);
                    case "SW 6" -> activity(t1, driver, laps, 6, miss, 2, 1);
                }
            }
            case "Badminton" -> {
                switch (s1) {
                    case "Six-Point Footwork Sequential" -> activity(t1, driver, laps, 1, miss, 3, 0);
                    case "Six-Point Footwork Random" -> activity(t1, driver, laps, 4, miss, 3, 0);
                    case "RH 4" -> activity(t1, driver, laps, 2, miss, 3, 0);
                    case "RH 6" -> activity(t1, driver, laps, 5, miss, 3, 0);
                    case "RW 4" -> activity(t1, driver, laps, 3, miss, 3, 0);
                    case "RW 6" -> activity(t1, driver, laps, 6, miss, 3, 0);
                    case "SH 4" -> activity(t1, driver, laps, 1, miss, 3, 2);
                    case "SH 6" -> activity(t1, driver, laps, 4, miss, 3, 2);
                    case "SW 4" -> activity(t1, driver, laps, 2, miss, 3, 2);
                    case "SW 6" -> activity(t1, driver, laps, 5, miss, 3, 2);
                    case "BD Hit 1" -> activity(t1, driver, laps, 3, miss, 3, 2);
                    case "BD Wave 1" -> activity(t1, driver, laps, 6, miss, 3, 2);
                }
            }
            case "School Fitness" -> {
                switch (s1) {
                    case "RH 4" -> activity(t1, driver, laps, 1, miss, 4, 0);
                    case "RH 6" -> activity(t1, driver, laps, 4, miss, 4, 0);
                    case "RW 4" -> activity(t1, driver, laps, 2, miss, 4, 0);
                    case "RW 6" -> activity(t1, driver, laps, 5, miss, 4, 0);
                    case "SH 4" -> activity(t1, driver, laps, 3, miss, 4, 0);
                    case "SH 6" -> activity(t1, driver, laps, 6, miss, 4, 0);
                    case "SW 4" -> activity(t1, driver, laps, 3, miss, 4, 1);
                    case "SW 6" -> activity(t1, driver, laps, 6, miss, 4, 1);
                }
            }
            case "Soccer" -> {
                switch (s1) {
                    case "RH 4" -> activity(t1, driver, laps, 1, miss, 5, 0);
                    case "RH 6" -> activity(t1, driver, laps, 4, miss, 5, 0);
                    case "RW 4" -> activity(t1, driver, laps, 2, miss, 5, 0);
                    case "RW 6" -> activity(t1, driver, laps, 5, miss, 5, 0);
                    case "SH 4" -> activity(t1, driver, laps, 3, miss, 5, 0);
                    case "SH 6" -> activity(t1, driver, laps, 6, miss, 5, 0);
                    case "SW 4" -> activity(t1, driver, laps, 3, miss, 5, 1);
                    case "SW 6" -> activity(t1, driver, laps, 6, miss, 5, 1);
                }
            }
            case "Physiotherapy" -> {
                switch (s1) {
                    case "PT Sequential Laps" -> activity2(driver, laps, 1, 6, 0);
                    case "RH 6" -> activity(t1, driver, laps, 4, miss, 6, 0);
                    case "RW 6" -> activity(t1, driver, laps, 2, miss, 6, 0);
                    case "SH 6" -> activity(t1, driver, laps, 5, miss, 6, 0);
                    case "SW 6" -> activity(t1, driver, laps, 3, miss, 6, 0);
                    case "Sequential Circle Hit 4" -> activity(t1, driver, laps, 6, miss, 6, 0);
                    case "Sequential Circle Wave 4" -> activity2(driver, laps, 2, 6, 1);
                    case "Sequential Circle Hit 6" -> activity2(driver, laps, 6, 6, 1);
                    case "Sequential Circle Wave 6" -> activity2(driver,laps,3, 6, 1);
                    case "T Test" -> activity2(driver, laps, 7, 6, 1);
                    case "3 Cone Wave" -> activity2(driver, laps, 4, 6, 1);
                    case "10 Meter" -> activity2(driver, laps, 8, 6, 1);
                    case "SEMO Wave" -> activity2(driver, laps, 2, 6, 2);
                    case "Pod Colour 4" -> activity2(driver, laps, 5, 6, 2);
                    case "Pod Colour 6" -> activity2(driver, laps, 3, 6, 2);
                }
            }
            case "Military Training" -> {
                driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"
                ));
                switch (s1) {
                    case "RH 4" -> activity(t1, driver, laps, 1, miss, 1, 0);
                    case "RH 6" -> activity(t1, driver, laps, 4, miss, 1, 0);
                    case "RW 4" -> activity(t1, driver, laps, 2, miss, 1, 0);
                    case "RW 6" -> activity(t1, driver, laps, 5, miss, 1, 0);
                    case "SH 4" -> activity(t1, driver, laps, 3, miss, 1, 0);
                    case "SH 6" -> activity(t1, driver, laps, 6, miss, 1, 0);
                    case "SW 4" -> activity(t1, driver, laps, 3, miss, 1, 1);
                    case "SW 6" -> activity(t1, driver, laps, 6, miss, 1, 1);
                }
            }
            case "Boxing" -> {
                driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"
                ));
                switch (s1) {
                    case "SH 6" -> activity(t1, driver, laps, 1, miss, 2, 0);
                    case "SH 4" -> activity(t1, driver, laps, 3, miss, 2, 0);
                    case "RH 6" -> activity(t1, driver, laps, 2, miss, 2, 0);
                    case "RH 4" -> activity(t1, driver, laps, 4, miss, 2, 0);
                }
            }
            case "Auditory Reflex Training" -> {
                driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"
                ));
                switch (s1) {
                    case "SW 4" -> activity(t1, driver, laps, 1, miss, 3, 0);
                    case "SW 6" -> activity(t1, driver, laps, 3, miss, 3, 0);
                    case "RW 4" -> activity(t1, driver, laps, 2, miss, 3, 0);
                    case "RW 6" -> activity(t1, driver, laps, 4, miss, 3, 0);
                }
            }
            case "Audiblaze Reflex Training" -> {
                driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"
                ));
                switch (s1) {
                    case "SW 4" -> activity(t1, driver, laps, 1, miss, 4, 0);
                    case "SW 6" -> activity(t1, driver, laps, 3, miss, 4, 0);
                    case "RW 4" -> activity(t1, driver, laps, 2, miss, 4, 0);
                    case "RW 6" -> activity(t1, driver, laps, 4, miss, 4, 0);
                }
            }
            case "New Activities" -> {
                driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"
                ));
                switch (s1) {
                    case "FH 6" -> activity(t1, driver, laps, 1, miss, 5, 0);
                    case "FW 6" -> activity(t1, driver, laps, 4, miss, 5, 0);
                    case "OH 6" -> activity(t1, driver, laps, 2, miss, 5, 0);
                    case "OW 6" -> activity(t1, driver, laps, 5, miss, 5, 0);
                    case "Back to home Hit 6" -> activity(t1, driver, laps, 3, miss, 5, 0);
                }
            }
            case "Table Tennis" -> {
                driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"
                ));
                if (s1.equals("X Drill")) {
                    activity2(driver, laps, 1, 6, 0);
                }
            }
        }
    }

    public static void activity(int time, AndroidDriver driver, int laps, int index2, int miss, int index, int index1) {

        //Want the test case
        String opt = JOptionPane.showInputDialog("Do you want this test case : ");
        if (opt.equalsIgnoreCase("N")) {
            return;
        }
        driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/imageView11\"])[" + index + "]")).click();

        System.out.println("Scroll : " + index1);
        if (index1 == 1) scroll(driver);
        if (index1 == 2) {
            scroll(driver);
            scroll(driver);
        }

        driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='com.dashpod.sportsandfitness:id/rv']/android.view.ViewGroup[" + index2 + "]")).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"
        ));
        WebElement time1 = driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.dashpod.sportsandfitness:id/list_settings\"]/android.view.ViewGroup[1]/android.widget.LinearLayout/android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_count\"][1]"));
        int timeexist = Integer.parseInt(time1.getText());
        if (time > timeexist) {
            for (int k = time / 5; k < timeexist / 5; k++) {
                driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_plus\"])[1]")).click();
            }
        } else if (time < timeexist) {
            for (int k = time / 5; k < timeexist / 5; k++) {
                driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_minus\"])[1]")).click();
            }
        } else {

        }
        WebElement lap1 = driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.dashpod.sportsandfitness:id/list_settings\"]/android.view.ViewGroup[2]/android.widget.LinearLayout/android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_count\"][1]"));
        int lapexist = Integer.parseInt(lap1.getText());
        if (laps > lapexist) {
            for (int j = lapexist; j < laps; j++) {
                driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_plus\"])[2]")).click();
            }
        } else if (laps < lapexist) {
            for (int j = laps; j < lapexist; j++) {
                driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_minus\"])[2]")).click();
            }
        } else {

        }
        WebElement miss1 = driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.dashpod.sportsandfitness:id/list_settings\"]/android.view.ViewGroup[3]/android.widget.LinearLayout/android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_count\"][1]"));
        int missexist = Integer.parseInt(miss1.getText());
        if (miss > missexist) {
            for (int k = missexist; k < miss; k++) {
                driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_plus\"])[3]")).click();
            }
        } else if (miss < missexist) {
            for (int k = miss; k < missexist; k++) {
                driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_minus\"])[3]")).click();
            }
        } else {

        }
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/player_name\"]")).click();
        driver.findElement(By.xpath("(//android.view.ViewGroup[@resource-id=\"com.dashpod.sportsandfitness:id/lyt_profile\"])[2]")).click();
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btn_start\"]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
        WebElement analysis = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btnViewAnalysis\"]")));
        analysis.click();

        WebElement tableLayout = driver.findElement(By.id("com.dashpod.sportsandfitness:id/tbllayout"));

        // Locate all the rows inside the TableLayout (assuming TableRow is used)
        List<WebElement> rows = tableLayout.findElements(By.xpath("//android.widget.TableLayout[@resource-id=\"com.dashpod.sportsandfitness:id/tbllayout\"]/android.widget.TableRow[2]"));

        // Loop through each row and extract the cell values
        for (WebElement row : rows) {
            // Find all cells within the row (assuming cells are TextView)
            List<WebElement> cells = row.findElements(By.className("android.widget.TextView"));
            WebElement lastCell = cells.getLast();
            int poor = (int) Double.parseDouble(lastCell.getText());

            driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Search\"]")).click();

            String prob = JOptionPane.showInputDialog("Errors : ");
            if (prob.equalsIgnoreCase("2L")) {
                Assert.assertEquals(1,2);
            } else if (prob.equalsIgnoreCase("D")) {
                Assert.assertEquals(1,2);
            }

            Assert.assertEquals(miss, poor);
        }
    }

    public static void activity2(AndroidDriver driver, int laps, int index2, int index, int index1) throws InterruptedException {

        //Want the test case
        String opt = JOptionPane.showInputDialog("Do you want this test case : ");
        if (opt.equalsIgnoreCase("N")) {
            return;
        }
        driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/imageView11\"])[" + index + "]")).click();
        if (index1 == 0) {

        } else if (index1 == 1) {
            scroll(driver);
        } else if (index1 == 2) {
            scroll(driver);
            scroll(driver);
        }

        driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='com.dashpod.sportsandfitness:id/rv']/android.view.ViewGroup[" + index2 + "]")).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"
        ));
        WebElement lap1 = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_count\"]"));
        int lapexist = Integer.parseInt(lap1.getText());
        if (laps < lapexist) {
            for (int j = laps; j < lapexist; j++) {
                driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_minus\"]")).click();
            }
        } else if (laps > lapexist) {
            for (int j = lapexist; j < laps; j++) {
                driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_plus\"]")).click();
            }
        }
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/player_name\"]")).click();
        driver.findElement(By.xpath("(//android.view.ViewGroup[@resource-id=\"com.dashpod.sportsandfitness:id/lyt_profile\"])[2]")).click();
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btn_start\"]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
        WebElement close = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btndialog\"]")));
        close.click();
        driver.findElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/toolbarIcon\"]")).click();

        String prob = JOptionPane.showInputDialog("Errors : ");
        if (prob.equalsIgnoreCase("2L")) {
            Assert.assertEquals(1,2);
        } else if (prob.equalsIgnoreCase("D")) {
            Assert.assertEquals(1,2);
        }

        Thread.sleep(1000);
    }

    public static void scroll(AndroidDriver driver) {
        // Assuming 'driver' is your Appium driver object
        WebElement recycleView = driver.findElement(By.id("com.dashpod.sportsandfitness:id/rv"));

        // Get the dimensions and bounds of the RecyclerView element
        int startX = recycleView.getLocation().getX() + recycleView.getSize().width - 1;  // Start X (right side of the element)
        int startY = recycleView.getLocation().getY() + recycleView.getSize().height / 2; // Start Y (middle of the element)
        int endX = recycleView.getLocation().getX() + 1;  // End X (left side of the element)
        // Same Y-coordinate for horizontal scroll

        // Perform horizontal swipe gesture to scroll to the right
        new TouchAction(driver)
                .press(PointOption.point(startX, startY))  // Start swipe on the right side
                .moveTo(PointOption.point(endX, startY))    // Move to the left side of the element
                .release()
                .perform();  // Perform the swipe action
    }

    @AfterAll
    public static void teardown() {
        // Close the driver after the test is completed
        if (driver != null) {
            driver.quit();
        }
    }
}
