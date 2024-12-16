import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Scanner;

public class automatePlayerProfile {
    public static void main (String[] args) throws MalformedURLException {

        Scanner sc = new Scanner(System.in);

        // Desired capabilities
        DesiredCapabilities capabilities = getDesiredCapabilities();

        URL url = URI.create("http://127.0.0.1:4723/wd/hub").toURL();
        AndroidDriver driver = new AndroidDriver(url, capabilities);

        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Settings\"]")).click();
        driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id=\"com.dashpod.sportsandfitness:id/llSetting1\"]")).click();
        driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id=\"com.dashpod.sportsandfitness:id/llSettingOption12\"]")).click();
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btn_player_profiles\"]")).click();

        System.out.println("Enter First Name: ");
        String fname = sc.nextLine();
        WebElement first_name = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/input_first_name\"]"));
        first_name.sendKeys(fname);

        System.out.println("Enter Last Name: ");
        String lname = sc.nextLine();
        WebElement last_name = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/input_last_name\"]"));
        last_name.sendKeys(lname);

        System.out.println("Enter Phone Num: ");
        String pnum = sc.nextLine();
        WebElement mob_num = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/input_mobile_number\"]"));
        mob_num.sendKeys(pnum);

        System.out.println("Enter Email address: ");
        String emailadd = sc.nextLine();
        WebElement email = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.dashpod.sportsandfitness:id/input_email\"]"));
        email.sendKeys(emailadd);

        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"
        ));


        System.out.println("Enter Gender(M/F)");
        String gender = sc.nextLine();
        if (gender.equals("M")) {
            driver.findElement(By.xpath("//android.widget.RadioButton[@resource-id=\"com.dashpod.sportsandfitness:id/radio_male\"]")).click();
        }else if (gender.equals("F")) {
            driver.findElement(By.xpath("//android.widget.RadioButton[@resource-id=\"com.dashpod.sportsandfitness:id/radio_female\"]")).click();
        }else {
            driver.findElement(By.xpath("//android.widget.RadioButton[@resource-id=\"com.dashpod.sportsandfitness:id/radio_other\"]")).click();
        }

        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.dashpod.sportsandfitness:id/btn_save\"]")).click();


    }

    private static DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:deviceName", "AndroidEmulator");
        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:automationName", "uiautomator2");
        capabilities.setCapability("appium:platformVersion", "13.0");
        capabilities.setCapability("appium:appPackage", "com.dashpod.sportsandfitness");
        capabilities.setCapability("appium:appActivity", "com.dashpod.sportsandfitness.MainActivity");
        return capabilities;
    }
}
