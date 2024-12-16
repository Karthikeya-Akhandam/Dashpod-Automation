import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class AutomateloginTests {

    static AndroidDriver driver;
    static boolean isFirstTest = true; // Flag to track the first test

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:deviceName", "AndroidEmulator");
        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:automationName", "uiautomator2");
        capabilities.setCapability("appium:platformVersion", "13.0");
        capabilities.setCapability("appium:appPackage", "com.dashpod.sportsandfitness");
        capabilities.setCapability("appium:appActivity", "com.dashpod.sportsandfitness.ui.activities.SplashScreenActivity");

        URL url = URI.create("http://127.0.0.1:4723/wd/hub").toURL();
        driver = new AndroidDriver(url, capabilities);

        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @TestFactory
    public List<DynamicTest> loginTests() throws IOException, CsvException {
        // Read the CSV file and create a list of tests
        CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Karthikeya Akhandam\\Documents\\BlazeTech\\credentials.csv"));
        List<String[]> lines = reader.readAll();
        reader.close();

        return lines.stream()
                .skip(1) // Skip the header row
                .map(line -> DynamicTest.dynamicTest("Login test for " + line[0], () -> loginTest(line[0], line[1])))
                .toList();
    }

    private void loginTest(String username, String password) {

        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btn_login\"]")).click();

        WebElement uname = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/loginuser\"]"));
        uname.sendKeys(username);

        WebElement pass = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/loginpassword\"]"));
        pass.sendKeys(password);

        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btn_login3\"]")).click();

        // Only for the first test
        if (isFirstTest) {
            // These lines will be executed only for the first test
            driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            System.out.println("Executing special actions for the first test only");
            // Insert any special actions here for the first test

            // Reset flag to false after first test is executed
            isFirstTest = false;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Settings\"]")).click();
        driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id=\"com.dashpod.sportsandfitness:id/llSetting1\"]")).click();
        driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id=\"com.dashpod.sportsandfitness:id/llSettingOption13\"]")).click();
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]")).click();



        // Add some validation logic here to ensure the login is successful, such as checking for a specific element on the next screen
    }
}
