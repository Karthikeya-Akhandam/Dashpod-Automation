import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class AutomateDevicesTests {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        // Desired Capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:deviceName", "HMD Global Nokia 6.1 Plus");
        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:automationName", "uiautomator2");
        capabilities.setCapability("appium:platformVersion", "10.0");
        capabilities.setCapability("appium:appPackage", "com.dashpod.sportsandfitness");
        capabilities.setCapability("appium:appActivity", "com.dashpod.sportsandfitness.MainActivity");

        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(url, capabilities);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Grant permissions
        WebElement permissionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]")));
        permissionButton.click();

        // Navigate through the app
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/dashPodMenuIcon\"]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.LinearLayout[@resource-id=\"com.dashpod.sportsandfitness:id/bottom_sheet_header\"]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageView[@resource-id=\"com.dashpod.sportsandfitness:id/selected_image_scan\"]"))).click();

        // Define mac address to search for
        String mac = "dashpod-v0.1-EB:EE";
        loop(driver, mac);
    }

    public static void loop(AndroidDriver driver, String mac) throws InterruptedException {
        List<WebElement> pods = driver.findElements(By.xpath("//android.view.ViewGroup[@resource-id=\"com.dashpod.sportsandfitness:id/lyt_pod\"]"));
        int retries = 0;
        while (retries < 25) { // Prevent infinite recursion
            for (int i = 0; i < pods.size(); i++) {
                WebElement podNameElement = pods.get(i).findElement(By.xpath(".//android.widget.TextView[@resource-id='com.dashpod.sportsandfitness:id/pod_name']"));
                String podName = podNameElement.getText();
                if (podName.equals(mac)) {
                    System.out.println(podName);
                    WebElement checkBox = pods.get(i).findElement(By.xpath(".//android.widget.CheckBox[@resource-id='com.dashpod.sportsandfitness:id/checkBox_bluetooth']"));
                    checkBox.click();
                    return; // Stop once the matching pod is found and clicked
                }
            }

            // If not found, scroll and try again
            scroll(driver);
            retries++;
        }
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollBackward(10)"
        ));
        loop(driver, mac);
    }

    public static void scroll(AndroidDriver driver) {
        WebElement recycleView = driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.dashpod.sportsandfitness:id/list_unprovisioned\"]"));

        // Get the dimensions and bounds of the RecyclerView element
        int startX = recycleView.getLocation().getX() + recycleView.getSize().width / 2;  // Start X (right side of the element)
        int startY = recycleView.getLocation().getY() + recycleView.getSize().height - 1; // Start Y (middle of the element)
        int endY = recycleView.getLocation().getY() + 1;  // End Y (top of the element)

        new TouchAction(driver).press(PointOption.point(startX, startY)).moveTo(PointOption.point(startX, endY)).release().perform();
    }
}
