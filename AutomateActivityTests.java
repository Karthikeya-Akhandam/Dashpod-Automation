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
        //capabilities.setCapability("appium:appPackage", "com.dashpod.sportsandfitness");
        //capabilities.setCapability("appium:appActivity", "com.dashpod.sportsandfitness.ui.activities.SplashScreenActivity");

        URL url = URI.create("http://127.0.0.1:4723/wd/hub").toURL();
        driver = new AndroidDriver(url, capabilities);
        //Thread.sleep(1000);
        //driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]")).click();
        //Thread.sleep(1000);
        //driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btn_login\"]")).click();
        //driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/loginuser\"]")).sendKeys("Karthik");
        //driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/loginpassword\"]")).sendKeys("Karthikeya@123");
        //driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btn_login3\"]")).click();
        //Thread.sleep(1000);
        //Thread.sleep(90000);
    }

    @AfterEach
    public void afterEachTest() {
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/navigation_bar_item_large_label_view\"]")).click();
    }

    @TestFactory
    public List<DynamicTest> testSignupProcesses() throws IOException, CsvException {
        // Read the CSV file and create a list of tests
        CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Karthikeya Akhandam\\Documents\\BlazeTech\\activityData.csv"));
        List<String[]> lines = reader.readAll();
        reader.close();

        return lines.stream()
                .skip(1) // Skip the header row
                .map(line -> DynamicTest.dynamicTest("Activity test for " + line[0] + " " + line[1],
                        () -> testActivityProcess(
                                line[0], line[1], line[2], line[3]
                        )))
                .toList();
    }

    private void testActivityProcess(String s, String s1, String s2, String s3) throws InterruptedException {
        int t1 = Integer.parseInt(s2);
        int laps = Integer.parseInt(s3);
        Thread.sleep(1000);

        switch (s) {
            case "Cricket" -> {
                driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/imageView11\"])[1]")).click();
                switch (s1) {
                    case "Retrieval" -> activity2(driver, laps, 1);
                    case "RH 4" -> activity(t1, driver, laps, 4);
                    case "RH 6" -> activity(t1, driver, laps, 2);
                    case "RW 4" -> activity(t1, driver, laps, 5);
                    case "RW 6" -> activity(t1, driver, laps, 3);
                    case "SH 4" -> activity(t1, driver, laps, 6);
                    case "SH 6" -> {
                        scroll(driver);
                        activity(t1, driver, laps, 2);
                    }
                    case "SW 4" -> {
                        scroll(driver);
                        activity(t1, driver, laps, 5);
                    }
                    case "SW 6" -> {
                        scroll(driver);
                        activity(t1, driver, laps, 3);
                    }
                }
            }
            case "Tennis" -> {
                driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/imageView11\"])[2]")).click();
                switch (s1) {
                    case "RH 4" -> activity(t1, driver, laps, 1);
                    case "RH 6" -> activity(t1, driver, laps, 4);
                    case "RW 4" -> activity(t1, driver, laps, 2);
                    case "RW 6" -> activity(t1, driver, laps, 5);
                    case "SH 4" -> activity(t1, driver, laps, 3);
                    case "SH 6" -> activity(t1, driver, laps, 6);
                    case "SW 4" -> {
                        scroll(driver);
                        activity(t1, driver, laps, 3);
                    }
                    case "SW 6" -> {
                        scroll(driver);
                        activity(t1, driver, laps, 6);
                    }
                }
            }
            case "Badminton" -> {
                driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/imageView11\"])[3]")).click();
                switch (s1) {
                    case "Six-Point Footwork Sequential" -> activity(t1, driver, laps, 1);
                    case "Six-Point Footwork Random" -> activity(t1, driver, laps, 4);
                    case "RH 4" -> activity(t1, driver, laps, 2);
                    case "RH 6" -> activity(t1, driver, laps, 5);
                    case "RW 4" -> activity(t1, driver, laps, 3);
                    case "RW 6" -> activity(t1, driver, laps, 6);
                    case "SH 4" -> {
                        scroll(driver);
                        scroll(driver);
                        activity(t1, driver, laps, 1);
                    }
                    case "SH 6" -> {
                        scroll(driver);
                        scroll(driver);
                        activity(t1, driver, laps, 4);
                    }
                    case "SW 4" -> {
                        scroll(driver);
                        scroll(driver);
                        activity(t1, driver, laps, 2);
                    }
                    case "SW 6" -> {
                        scroll(driver);
                        scroll(driver);
                        activity(t1, driver, laps, 5);
                    }
                    case "BD Hit 1" -> {
                        scroll(driver);
                        scroll(driver);
                        activity(t1, driver, laps, 3);
                    }
                    case "BD Wave 1" -> {
                        scroll(driver);
                        scroll(driver);
                        activity(t1, driver, laps, 6);
                    }
                }
            }
            case "School Fitness" -> {
                driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/imageView11\"])[4]")).click();
                switch (s1) {
                    case "RH 4" -> activity(t1, driver, laps, 1);
                    case "RH 6" -> activity(t1, driver, laps, 4);
                    case "RW 4" -> activity(t1, driver, laps, 2);
                    case "RW 6" -> activity(t1, driver, laps, 5);
                    case "SH 4" -> activity(t1, driver, laps, 3);
                    case "SH 6" -> activity(t1, driver, laps, 6);
                    case "SW 4" -> activity(t1, driver, laps, 3);
                    case "SW 6" -> {
                        scroll(driver);
                        activity(t1, driver, laps, 6);
                    }
                }
            }
            case "Soccer" -> {
                driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/imageView11\"])[5]")).click();
                switch (s1) {
                    case "RH 4" -> activity(t1, driver, laps, 1);
                    case "RH 6" -> activity(t1, driver, laps, 4);
                    case "RW 4" -> activity(t1, driver, laps, 2);
                    case "RW 6" -> activity(t1, driver, laps, 5);
                    case "SH 4" -> activity(t1, driver, laps, 3);
                    case "SH 6" -> activity(t1, driver, laps, 6);
                    case "SW 4" -> {
                        scroll(driver);
                        activity(t1, driver, laps, 3);
                    }
                    case "SW 6" -> {
                        scroll(driver);
                        activity(t1, driver, laps, 6);
                    }
                }
            }
            case "Physiotherapy" -> {
                driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/imageView11\"])[6]")).click();
                switch (s1) {
                    case "PT Sequential Laps" -> activity2(driver, laps, 1);
                    case "RH 6" -> activity(t1, driver, laps, 4);
                    case "RW 6" -> activity(t1, driver, laps, 2);
                    case "SH 6" -> activity(t1, driver, laps, 5);
                    case "SW 6" -> activity(t1, driver, laps, 3);
                    case "Sequential Circle Hit 4" -> activity(t1, driver, laps, 6);
                    case "Sequential Circle Wave 4" -> {
                        String Element1 = "PT Sequential Circle Wave 4";
                        WebElement list3 = driver.findElement(AppiumBy.androidUIAutomator(
                                "new UiScrollable(new UiSelector().scrollable(true).orientation(0)).scrollIntoView(new UiSelector().text(\"" + Element1 + "\"))"
                        ));
                        list3.click();
                        activity1(t1, driver, laps);
                    }

                    case "T Test" -> {
                        String Element7 = "PT T Test";
                        WebElement list3 = driver.findElement(AppiumBy.androidUIAutomator(
                                "new UiScrollable(new UiSelector().scrollable(true).orientation(0)).scrollIntoView(new UiSelector().text(\"" + Element7 + "\"))"
                        ));
                        list3.click();
                        activity3(driver, laps);
                    }
                    case "3 Cone Wave" -> {
                        String Element8 = "3 Cone Shuttle Drill Wave (L Drill)";
                        WebElement list3 = driver.findElement(AppiumBy.androidUIAutomator(
                                "new UiScrollable(new UiSelector().scrollable(true).orientation(0)).scrollIntoView(new UiSelector().text(\"" + Element8 + "\"))"
                        ));
                        list3.click();
                        activity3(driver, laps);
                    }
                    case "10 Meter" -> {
                        String Element8 = "Ten Meters Agility Shuttle";
                        WebElement list3 = driver.findElement(AppiumBy.androidUIAutomator(
                                "new UiScrollable(new UiSelector().scrollable(true).orientation(0)).scrollIntoView(new UiSelector().text(\"" + Element8 + "\"))"
                        ));
                        list3.click();
                        activity3(driver, laps);
                    }
                    case "SEMO Wave" -> {
                        String Element9 = "SEMO Agility Drill Wave";
                        WebElement list3 = driver.findElement(AppiumBy.androidUIAutomator(
                                "new UiScrollable(new UiSelector().scrollable(true).orientation(0)).scrollIntoView(new UiSelector().text(\"" + Element9 + "\"))"
                        ));
                        list3.click();
                        activity3(driver, laps);
                    }
                    case "Pod Colour 4" -> {
                        String Element10 = "Four Pod color Memory Test Tap";
                        driver.findElement(AppiumBy.androidUIAutomator(
                                "new UiScrollable(new UiSelector().scrollable(true).orientation(0)).scrollIntoView(new UiSelector().text(\"" + Element10 + "\"))"
                        ));
                        activity3(driver, laps);
                    }
                    case "Pod Colour 6" -> {
                        String Element11 = "Six Pod color Memory Test Tap";
                        driver.findElement(AppiumBy.androidUIAutomator(
                                "new UiScrollable(new UiSelector().scrollable(true).orientation(0)).scrollIntoView(new UiSelector().text(\"" + Element11 + "\"))"
                        ));
                        activity3(driver, laps);
                    }
                    default -> {
                        // Handle any case where no match occurs
                    }
                }

            }
            case "Military Training" -> {
                driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"
                ));
                driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/imageView11\"])[1]")).click();
                switch (s1) {
                    case "RH 4" -> activity(t1, driver, laps, 1);
                    case "RH 6" -> activity(t1, driver, laps, 4);
                    case "RW 4" -> activity(t1, driver, laps, 2);
                    case "RW 6" -> activity(t1, driver, laps, 5);
                    case "SH 4" -> activity(t1, driver, laps, 3);
                    case "SH 6" -> activity(t1, driver, laps, 6);
                    case "SW 4" -> {
                        scroll(driver);
                        activity(t1, driver, laps, 3);
                    }
                    case "SW 6" -> {
                        scroll(driver);
                        activity(t1, driver, laps, 6);
                    }
                }
            }
            case "Boxing" -> {
                driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"
                ));
                driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/imageView11\"])[2]")).click();
                switch (s1) {
                    case "SH 6" -> activity(t1, driver, laps, 1);
                    case "SH 4" -> activity(t1, driver, laps, 3);
                    case "RH 6" -> activity(t1, driver, laps, 2);
                    case "RH 4" -> activity(t1, driver, laps, 4);
                }
            }
            case "Auditory Reflex Training" -> {
                driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"
                ));
                driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/imageView11\"])[3]")).click();
                switch (s1) {
                    case "SW 4" -> activity(t1, driver, laps, 1);
                    case "SW 6" -> activity(t1, driver, laps, 3);
                    case "RW 4" -> activity(t1, driver, laps, 2);
                    case "RW 6" -> activity(t1, driver, laps, 4);
                }
            }
            case "Audiblaze Reflex Training" -> {
                driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"
                ));
                driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/imageView11\"])[4]")).click();
                switch (s1) {
                    case "SW 4" -> activity(t1, driver, laps, 1);
                    case "SW 6" -> activity(t1, driver, laps, 3);
                    case "RW 4" -> activity(t1, driver, laps, 2);
                    case "RW 6" -> activity(t1, driver, laps, 4);
                }
            }
            case "New Activities" -> {
                driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"
                ));
                driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/imageView11\"])[5]")).click();
                switch (s1) {
                    case "FH 6" -> activity(t1, driver, laps, 1);
                    case "FW 6" -> activity(t1, driver, laps, 4);
                    case "OH 6" -> activity(t1, driver, laps, 2);
                    case "OW 6" -> activity(t1, driver, laps, 5);
                    case "Back to home Hit 6" -> activity(t1, driver, laps, 3);
                }
            }
            case "Table Tennis" -> {
                driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"
                ));
                driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/imageView11\"])[6]")).click();
                if (s1.equals("X Drill")) {
                    driver.findElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/activityImage\"]")).click();
                    activity1(t1, driver, laps);
                }
            }
        }
    }

    public static void activity(int time, AndroidDriver driver, int laps, int index1) throws InterruptedException {

        driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='com.dashpod.sportsandfitness:id/rv']/android.view.ViewGroup[" + index1 + "]")).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"
        ));
        int timeexist = Integer.parseInt(driver.findElement(By.id("com.dashpod.sportsandfitness:id/text_count")).getText());
        if (time > timeexist) {
            for (int k = time/5; k<timeexist/5; k++) {
                driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_plus\"])[1]")).click();
            }
        } else if (time < timeexist) {
            for (int k = time/5; k<timeexist/5; k++){
                driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_minus\"])[1]")).click();
            }
        } else {

        }
        int lapexist = Integer.parseInt(driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_count\" and @text=\"1\"]")).getText());
        if (laps < lapexist) {
            for (int j = laps; j < lapexist; j++) {
                driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_plus\"])[2]")).click();
            }
        } else if (laps > lapexist) {
            for (int j = laps; j < lapexist; j++) {
                driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_minus\"])[2]")).click();
            }
        }
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/player_name\"]")).click();
        driver.findElement(By.xpath("(//android.view.ViewGroup[@resource-id=\"com.dashpod.sportsandfitness:id/lyt_profile\"])[2]")).click();
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btn_start\"]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(130));
        WebElement close = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btndialog\"]")));
        close.click();
        driver.findElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/toolbarIcon\"]")).click();
        Thread.sleep(2000);

    }

    public static void activity1(int time,AndroidDriver driver, int laps) throws InterruptedException {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"
        ));
        int timeexist = Integer.parseInt(driver.findElement(By.id("com.dashpod.sportsandfitness:id/text_count")).getText());
        if (time > timeexist) {
            for (int k = time/5; k<timeexist/5; k++) {
                driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_plus\"])[1]")).click();
            }
        } else if (time < timeexist) {
            for (int k = time/5; k<timeexist/5; k++){
                driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_minus\"])[1]")).click();
            }
        } else {

        }
        int lapexist = Integer.parseInt(driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_count\" and @text=\"1\"]")).getText());
        if (laps < lapexist) {
            for (int j = laps; j < lapexist; j++) {
                driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_plus\"])[2]")).click();
            }
        } else if (laps > lapexist) {
            for (int j = laps; j < lapexist; j++) {
                driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_minus\"])[2]")).click();
            }
        }
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/player_name\"]")).click();
        driver.findElement(By.xpath("(//android.view.ViewGroup[@resource-id=\"com.dashpod.sportsandfitness:id/lyt_profile\"])[2]")).click();
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btn_start\"]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(130));
        WebElement close = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btndialog\"]")));
        close.click();
        driver.findElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/toolbarIcon\"]")).click();
        Thread.sleep(2000);
    }

    public static void activity2(AndroidDriver driver, int laps, int index1) throws InterruptedException {

        driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='com.dashpod.sportsandfitness:id/rv']/android.view.ViewGroup[" + index1 + "]")).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"
        ));
        int lapexist = Integer.parseInt(driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_count\" and @text=\"1\"]")).getText());
        if (laps < lapexist) {
            for (int j = laps; j < lapexist; j++) {
                driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_plus\"])[2]")).click();
            }
        } else if (laps > lapexist) {
            for (int j = laps; j < lapexist; j++) {
                driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_minus\"])[2]")).click();
            }
        }
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/player_name\"]")).click();
        driver.findElement(By.xpath("(//android.view.ViewGroup[@resource-id=\"com.dashpod.sportsandfitness:id/lyt_profile\"])[2]")).click();
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btn_start\"]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(130));
        WebElement close = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btndialog\"]")));
        close.click();
        driver.findElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/toolbarIcon\"]")).click();
        Thread.sleep(1000);

    }

    public static void activity3(AndroidDriver driver, int laps) throws InterruptedException {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"
        ));
        int lapexist = Integer.parseInt(driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_count\" and @text=\"1\"]")).getText());
        if (laps < lapexist) {
            for (int j = laps; j < lapexist; j++) {
                driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_plus\"])[2]")).click();
            }
        } else if (laps > lapexist) {
            for (int j = laps; j < lapexist; j++) {
                driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/text_minus\"])[2]")).click();
            }
        }
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.dashpod.sportsandfitness:id/player_name\"]")).click();
        driver.findElement(By.xpath("(//android.view.ViewGroup[@resource-id=\"com.dashpod.sportsandfitness:id/lyt_profile\"])[2]")).click();
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btn_start\"]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(130));
        WebElement close = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btndialog\"]")));
        close.click();
        driver.findElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/toolbarIcon\"]")).click();
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
