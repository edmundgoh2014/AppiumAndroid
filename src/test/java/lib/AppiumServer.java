package lib;

import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class appiumServer {
    protected static AppiumDriverLocalService service;

    @BeforeAll
    public static void startServer() throws IOException {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        @SuppressWarnings("deprecation")
URL url = new URL("http://127.0.0.1:4723/status");
HttpURLConnection connection = (HttpURLConnection) url.openConnection();
connection.setRequestMethod("GET");

// Get the response code
int responseCode = connection.getResponseCode();
System.out.println("Response code: " + responseCode);

// Handle the response based on the status code
if (responseCode == HttpURLConnection.HTTP_OK) {
    // Handle successful response
    // You can read the response body or perform other actions here
    System.out.println("GET request successful");
} else {
    // Handle error response
    System.out.println("GET request failed with response code: " + responseCode);

    // Print error response body if available
    InputStream errorStream = connection.getErrorStream();
    if (errorStream != null) {
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
        String errorResponse;
        while ((errorResponse = errorReader.readLine()) != null) {
            System.out.println("Error response: " + errorResponse);
        }
        errorReader.close();
    }
        }
        System.out.println("BeforeAll");
    }

    @AfterAll
    public static void stopServer() {
        service.stop();
        System.out.println("AfterAll");
    }
}
