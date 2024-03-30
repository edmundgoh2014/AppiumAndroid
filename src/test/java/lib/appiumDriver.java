package lib;

import config.testConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class appiumDriver extends appiumServer {

    protected io.appium.java_client.android.AndroidDriver driver;
    public WebDriverWait wait;
    private UiAutomator2Options options;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void startDriver() throws MalformedURLException {
        testConfig.explicitWaitTime = 3;

        options = new UiAutomator2Options()
                .setDeviceName(testConfig.deviceName)
                .setApp(testConfig.APK);
        driver = new AndroidDriver(new URL(testConfig.driverHost), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        wait = new WebDriverWait(driver, Duration.ofSeconds(testConfig.explicitWaitTime));
        System.out.println("BeforeEach");
    }

    @AfterEach
    public void stopDriver() {
        driver.quit();
        System.out.println("AfterEach");
    }
}