package pages;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
public class BasePage {
    public WebDriverWait wait;
    public AndroidDriver driver;
    private File f = new File("resources/261.apk");
    public String path = f.getAbsolutePath();


    @BeforeEach

    public void setUp() throws MalformedURLException {

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("appium:deviceName", "emulator-5554");
        dc.setCapability("platformName", "Android");
        dc.setCapability("appium:app", path);
        dc.setCapability("appium:appActivity", "ai.moises.ui.MainActivity");
        dc.setCapability("appium:appPackage", "ai.moises");
        dc.setCapability("appium:automationName", "uiautomator2");
        dc.setCapability("appium:ignoreHiddenApiPolicyError", true);
        dc.setCapability("appium:newCommandTimeout", 10000);

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, dc);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @AfterEach
    public void tearDown() {
           //driver.quit();
    }

}


