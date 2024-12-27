import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.openqa.selenium.By;
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

public class AutomateSearchFilterTests {

    public static AndroidDriver driver;

    @BeforeAll
    public static void setup() throws MalformedURLException {
        // Desired Capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:deviceName", "HMD Global Nokia 6.1 Plus");
        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:automationName", "uiautomator2");
        capabilities.setCapability("appium:platformVersion", "10.0");
        capabilities.setCapability("appium:appPackage", "com.dashpod.sportsandfitness");
        capabilities.setCapability("appium:appActivity", "com.dashpod.sportsandfitness.ui.activities.SplashScreenActivity");

        URL url = URI.create("http://127.0.0.1:4723/wd/hub").toURL();
        driver = new AndroidDriver(url, capabilities);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btn_login\"]"))).click();

        //Enter your username
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/loginuser\"]"))).sendKeys("Your_Username");
        //Enter your password
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/loginpassword\"]"))).sendKeys("Your_Password");
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btn_login3\"]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.FrameLayout[@content-desc=\"Search\"]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/image_filter\"]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btnClear\"]"))).click();
    }

    @AfterEach
    public void afterEachTest() throws InterruptedException {
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/image_filter\"]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btnClear\"]"))).click();
    }

    @TestFactory
    public List<DynamicTest> testSignupProcesses() throws IOException, CsvException {
        // Read the CSV file and create a list of tests
        CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Karthikeya Akhandam\\Documents\\BlazeTech\\searchFilterData.csv"));
        List<String[]> lines = reader.readAll();
        reader.close();

        return lines.stream()
                .skip(1) // Skip the header row
                .map(line -> DynamicTest.dynamicTest("Activity test for " + line[0],
                        () -> testsearchfilterprocess(
                                line[1], line[2], line[3]
                        )))
                .toList();
    }
    private void testsearchfilterprocess(String s, String s1, String s2) throws InterruptedException {
        int si = Integer.parseInt(s);
        int no_of_pods = si;
        String category = s1;
        String act_type = s2;
        if (no_of_pods == 0 && category == null && act_type == null) {
            driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btnSelectAll\"]")).click();
        }else {
            selection(no_of_pods, category, act_type, driver);
        }

        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/show_results_btn\"]")).click();
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/image_filter\"]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btnClear\"]"))).click();
    }
    public static void selection (int sia, String sa1, String sa2, AndroidDriver driver) {
        switch (sia) {
            case 1 -> driver.findElement(By.xpath("//android.widget.ToggleButton[@resource-id=\"com.dashpod.sportsandfitness:id/tv_checkbox_cell\" and @text=\"1\"]")).click();
            case 2 -> driver.findElement(By.xpath("//android.widget.ToggleButton[@resource-id=\"com.dashpod.sportsandfitness:id/tv_checkbox_cell\" and @text=\"2\"]")).click();
            case 3 -> driver.findElement(By.xpath("//android.widget.ToggleButton[@resource-id=\"com.dashpod.sportsandfitness:id/tv_checkbox_cell\" and @text=\"3\"]")).click();
            case 4 -> driver.findElement(By.xpath("//android.widget.ToggleButton[@resource-id=\"com.dashpod.sportsandfitness:id/tv_checkbox_cell\" and @text=\"4\"]")).click();
            case 6 -> driver.findElement(By.xpath("//android.widget.ToggleButton[@resource-id=\"com.dashpod.sportsandfitness:id/tv_checkbox_cell\" and @text=\"6\"]")).click();
            default -> driver.findElement(By.xpath("//android.widget.CheckBox[@resource-id=\"com.dashpod.sportsandfitness:id/checkbox_all_nop\"]")).click();
        }
        switch (sa1) {
            case "CK" -> driver.findElement(By.xpath("//android.widget.ToggleButton[@resource-id=\"com.dashpod.sportsandfitness:id/tv_checkbox_cell\" and @text=\"Cricket\"]")).click();
            case "TN" -> driver.findElement(By.xpath("//android.widget.ToggleButton[@resource-id=\"com.dashpod.sportsandfitness:id/tv_checkbox_cell\" and @text=\"Tennis\"]")).click();
            case "BD" -> driver.findElement(By.xpath("//android.widget.ToggleButton[@resource-id=\"com.dashpod.sportsandfitness:id/tv_checkbox_cell\" and @text=\"Badminton\"]")).click();
            case "SF" -> driver.findElement(By.xpath("//android.widget.ToggleButton[@resource-id=\"com.dashpod.sportsandfitness:id/tv_checkbox_cell\" and @text=\"School Fitness\"]")).click();
            case "SO" -> driver.findElement(By.xpath("//android.widget.ToggleButton[@resource-id=\"com.dashpod.sportsandfitness:id/tv_checkbox_cell\" and @text=\"Soccer\"]")).click();
            case "PT" -> driver.findElement(By.xpath("//android.widget.ToggleButton[@resource-id=\"com.dashpod.sportsandfitness:id/tv_checkbox_cell\" and @text=\"Physiotherapy\"]")).click();
            case "MT" -> driver.findElement(By.xpath("//android.widget.ToggleButton[@resource-id=\"com.dashpod.sportsandfitness:id/tv_checkbox_cell\" and @text=\"Military Training\"]")).click();
            case "Boxing" -> driver.findElement(By.xpath("//android.widget.ToggleButton[@resource-id=\"com.dashpod.sportsandfitness:id/tv_checkbox_cell\" and @text=\"Boxing\"]")).click();
            case "ART" -> driver.findElement(By.xpath("//android.widget.ToggleButton[@resource-id=\"com.dashpod.sportsandfitness:id/tv_checkbox_cell\" and @text=\"Auditory Reflex Training\"]")).click();
            case "ABRT" -> driver.findElement(By.xpath("//android.widget.ToggleButton[@resource-id=\"com.dashpod.sportsandfitness:id/tv_checkbox_cell\" and @text=\"Audiblaze Reflex Training\"]")).click();
            case "NA" -> driver.findElement(By.xpath("//android.widget.ToggleButton[@resource-id=\"com.dashpod.sportsandfitness:id/tv_checkbox_cell\" and @text=\"New Activities\"]")).click();
            case "TT" -> driver.findElement(By.xpath("//android.widget.ToggleButton[@resource-id=\"com.dashpod.sportsandfitness:id/tv_checkbox_cell\" and @text=\"Table Tennis\"]")).click();
            default -> driver.findElement(By.xpath("//android.widget.CheckBox[@resource-id=\"com.dashpod.sportsandfitness:id/checkbox_all_categories\"]")).click();
        }
        switch (sa2) {
            case "H" -> driver.findElement(By.xpath("//android.widget.ToggleButton[@resource-id=\"com.dashpod.sportsandfitness:id/tv_checkbox_cell\" and @text=\"Hit\"]")).click();
            case "W" -> driver.findElement(By.xpath("//android.widget.ToggleButton[@resource-id=\"com.dashpod.sportsandfitness:id/tv_checkbox_cell\" and @text=\"Wave\"]")).click();
            default -> driver.findElement(By.xpath("//android.widget.CheckBox[@resource-id=\"com.dashpod.sportsandfitness:id/checkbox_all_activitytypes\"]")).click();
        }
    }
}
