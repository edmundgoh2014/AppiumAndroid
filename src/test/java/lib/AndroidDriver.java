package lib;

import config.testConfig;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AndroidDriver extends AppiumServer {

    protected io.appium.java_client.android.AndroidDriver driver;
    private UiAutomator2Options options;

    @BeforeEach
    public void startDriver() throws MalformedURLException {
        options = new UiAutomator2Options()
                .setDeviceName(testConfig.deviceName)
                .setApp(testConfig.APK);
        driver = new io.appium.java_client.android.AndroidDriver(new URL(testConfig.driverHost), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        System.out.println("BeforeEach");
    }

    @AfterEach
    public void stopDriver() {
        driver.quit();
        System.out.println("AfterEach");
    }
}
