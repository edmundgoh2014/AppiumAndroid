package lib;

import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class appiumServer {
    protected static AppiumDriverLocalService service;

    @BeforeAll
    public static void startServer() throws IOException {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        System.out.println("BeforeAll");
    }

    @AfterAll
    public static void stopServer() {
        service.stop();
        System.out.println("AfterAll");
    }
}
