import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

public class AutomateSignUpTests {

    private static AndroidDriver driver;

    @BeforeAll
    public static void setup() throws MalformedURLException {

        // Desired capabilities setup
        DesiredCapabilities capabilities = getDesiredCapabilities();

        URL url = URI.create("http://127.0.0.1:4723/wd/hub").toURL();
        driver = new AndroidDriver(url, capabilities);

        // Start by clicking the permission button
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

    }

    private static DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:deviceName", "AndroidEmulator");
        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:automationName", "uiautomator2");
        capabilities.setCapability("appium:platformVersion", "13.0");
        capabilities.setCapability("appium:appPackage", "com.dashpod.sportsandfitness");
        capabilities.setCapability("appium:appActivity", "com.dashpod.sportsandfitness.ui.activities.SplashScreenActivity");
        return capabilities;
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @TestFactory
    public List<DynamicTest> testSignupProcesses() throws IOException, CsvException {
        // Read the CSV file and create a list of tests
        CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Karthikeya Akhandam\\Documents\\BlazeTech\\userData.csv"));
        List<String[]> lines = reader.readAll();
        reader.close();

        return lines.stream()
                .skip(1) // Skip the header row
                .map(line -> DynamicTest.dynamicTest("Signup test for " + line[6] + " " + line[7],
                        () -> testSignupProcess(
                                line[0], line[1], line[2], line[3], line[4],
                                line[5], line[6], line[7], line[8], line[9],
                                line[10], line[11], line[12], line[13], line[14],
                                line[15], line[16]
                        )))
                .toList();
    }

    public void testSignupProcess(String username, String email, String countrycode, String phonenumber, String password, String profile, String firstname, String lastname, String genderex, String dateofbirth, String dimhex, String heightex, String dimwex, String weightex, String freqex, String aoiex, String interestex) {

        // Click on login button and go to Sign Up
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btn_login\"]")).click();
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/buttonSignUp\"]")).click();

        // Input user details for signup
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/userName\"]")).sendKeys(username);
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/userEmail\"]")).sendKeys(email);

        // Select country code
        driver.findElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/arrow_imv\"]")).click();
        WebElement countryCode = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + countrycode + "\"))"
        ));
        countryCode.click();

        // Input phone number, password, confirm password
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/phoneNumber\"]")).sendKeys(phonenumber);
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/newPassword\"]")).sendKeys(password);
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/confirmPassword\"]")).sendKeys(password);

        // Agree to terms and create account
        driver.findElement(By.xpath("//android.widget.CheckBox[@resource-id=\"com.dashpod.sportsandfitness:id/checkBoxAgree\"]")).click();
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/buttonCreateAccount\"]")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // Ensure login button appears (i.e., registration was successful)
        try {
            driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/loginuser\"]")).sendKeys(username);
            driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/loginpassword\"]")).sendKeys(password);
            driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btn_login3\"]")).click();
        } catch (RuntimeException e) {
            driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/button_backto_login\"]")).click();
            driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/loginuser\"]")).sendKeys(username);
            driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/loginpassword\"]")).sendKeys(password);
            driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btn_login3\"]")).click();
        }

        // Login after sign-up

        WebElement list2 = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + profile + "\"))"
        ));
        list2.click();

        driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/input_first_name\"]")).sendKeys(firstname);
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/input_last_name\"]")).sendKeys(lastname);
        driver.findElement(By.xpath("//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/arrow_imv\"]")).click();
        WebElement list3 = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + countrycode + "\"))"
        ));
        list3.click();
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/input_mobile_number\"]")).sendKeys(phonenumber);
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/input_email\"]")).sendKeys(email);

        //Gender
        if (genderex.equals("M")) {
            driver.findElement(By.xpath("//android.widget.RadioButton[@resource-id=\"com.dashpod.sportsandfitness:id/radio_male\"]")).click();
        }else if (genderex.equals("F")) {
            driver.findElement(By.xpath("//android.widget.RadioButton[@resource-id=\"com.dashpod.sportsandfitness:id/radio_female\"]")).click();
        }else {
            driver.findElement(By.xpath("//android.widget.RadioButton[@resource-id=\"com.dashpod.sportsandfitness:id/radio_other\"]")).click();
        }

        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"
        ));

        //DOB
        WebElement dob = driver.findElement(By.xpath("//android.widget.AutoCompleteTextView[@resource-id=\"com.dashpod.sportsandfitness:id/auto_input_date_of_month\"]"));
        dob.clear();
        dob.sendKeys(dateofbirth);

        //Height
        if (dimhex.equals("Ft")) {
            driver.findElement(By.xpath("//android.widget.Switch[@resource-id=\"com.dashpod.sportsandfitness:id/swHeight\"]")).click();
        }
        WebElement height = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/input_height\"]"));
        height.click();
        height.sendKeys(heightex);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        //Weight
        if (dimwex.equals("Lb")) {
            driver.findElement(By.xpath("//android.widget.Switch[@resource-id=\"com.dashpod.sportsandfitness:id/swWeight\"]")).click();
        }
        WebElement weight = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/input_weight\"]"));
        weight.click();
        weight.sendKeys(weightex);

        //Frequency
        driver.findElement(By.xpath("//android.widget.AutoCompleteTextView[@resource-id=\"com.dashpod.sportsandfitness:id/autoCompleteTextUpdate\"]")).click();
        WebElement freq = driver.findElement(By.xpath("//android.widget.AutoCompleteTextView[@resource-id=\"com.dashpod.sportsandfitness:id/autoCompleteTextUpdate\"]"));
        freq.clear();
        freq.sendKeys(freqex);
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btn_save\"]")).click();

        //Area of interest
        switch (aoiex) {
            case "Sports" ->
                    driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.dashpod.sportsandfitness:id/list_intrests\"]/android.widget.FrameLayout[1]/android.view.ViewGroup")).click();
            case "Fitness" ->
                    driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.dashpod.sportsandfitness:id/list_intrests\"]/android.widget.FrameLayout[2]/android.view.ViewGroup")).click();
            case "Physiotherapy" ->
                    driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.dashpod.sportsandfitness:id/list_intrests\"]/android.widget.FrameLayout[3]/android.view.ViewGroup")).click();
            default ->
                    driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btn_reset\"]")).click();
        }

        //Interest
        if (interestex.equals("Cricket")) {
            driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/interest_image\"])[1]")).click();
        }
        if (interestex.equals("Tennis")) {
            driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/interest_image\"])[2]")).click();
        }
        if (interestex.equals("Badmenton")) {
            driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/interest_image\"])[3]")).click();
        }
        if (interestex.equals("Soccer")) {
            driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/interest_image\"])[4]")).click();
        }
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btn_save\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));


        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Settings\"]")).click();
        driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id=\"com.dashpod.sportsandfitness:id/llSetting1\"]")).click();
        driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id=\"com.dashpod.sportsandfitness:id/llSettingOption13\"]")).click();
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]")).click();
    }

    @AfterAll
    public static void teardown() {
        // Close the driver after the test is completed
        if (driver != null) {
            driver.quit();
        }
    }
}
