package config;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Objects;

public class testConfig {
    public static final String APK;
    public static final String deviceName = "Pixel_3a_API_34_extension_level_7_x86_64";
    public static final String driverHost = "http://localhost:4723";

    static {
        try {
            APK = Path.of(Objects.requireNonNull(testConfig.class.getClassLoader().getResource("Android-MyDemoAppRN.1.3.0.build-244.apk")).toURI()).toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
