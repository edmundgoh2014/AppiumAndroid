package config;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Objects;

public class testConfig {
    public static final String APK;
    public static final String deviceName = "testAndroid";
    public static final String driverHost = "http://localhost:4723";
    public static int explicitWaitTime = 3;
    public static final String username = "bob@example.com";
    public static final String password = "10203040";

    static {
        try {
            APK = Path.of(Objects.requireNonNull(testConfig.class.getClassLoader().getResource("Android-MyDemoAppRN.1.3.0.build-244.apk")).toURI()).toString();
        } catch (URISyntaxException | NullPointerException e) {
            System.err.println("Error while retrieving APK path: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
