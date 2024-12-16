import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

public class AutomatePodSettings {
    public static void main(String[] args) throws MalformedURLException {

        // Desired capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:deviceName", "HMD Global Nokia 6.1 Plus");
        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:automationName", "uiautomator2");
        capabilities.setCapability("appium:platformVersion", "10.0");
        capabilities.setCapability("appium:appPackage", "com.dashpod.sportsandfitness");
        capabilities.setCapability("appium:appActivity", "com.dashpod.sportsandfitness.MainActivity");

        URL url = URI.create("http://127.0.0.1:4723/wd/hub").toURL();
        AndroidDriver driver = new AndroidDriver(url, capabilities);

        //driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Settings\"]")).click();

        driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id=\"com.dashpod.sportsandfitness:id/llSetting2\"]")).click();
        driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id=\"com.dashpod.sportsandfitness:id/llSettingOption22\"]")).click();

        //Hit Sensitivity (60,260,460,660;315)
        TouchAction action1 = new TouchAction(driver);
        action1.longPress(PointOption.point(460,315)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).perform().release();

        //Wave Sensitivity (60,260,460,660;490)
        TouchAction action2 = new TouchAction(driver);
        action2.longPress(PointOption.point(460,490)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).perform().release();

        //Brightness (60,60+x,...,60+9x,x=66.666;665)
        TouchAction action3 = new TouchAction(driver);
        action3.longPress(PointOption.point(260,665)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).perform().release();

    }
}