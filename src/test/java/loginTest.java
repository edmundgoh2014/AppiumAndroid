import io.appium.java_client.AppiumBy;
import lib.AndroidDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class loginTest extends AndroidDriver {
    public static final By burgerMenu = AppiumBy.accessibilityId("open menu");
    public static final By loginMenu = AppiumBy.accessibilityId("menu item log in");
    public static final By usernameInput = AppiumBy.accessibilityId("Username input field");
    public static final By passwordInput = AppiumBy.accessibilityId("Password input field");
    public static final By loginButton = AppiumBy.accessibilityId("Login button");
    public static final By logoutMenu = AppiumBy.accessibilityId("menu item log out");
    public static final By logoutButton = AppiumBy.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]");

    @Test
    void loginWithExistingUser() {
        final String userName = "bob@example.com";
        final String password = "10203040";
        final String expectedOutputText = "Products";
        final String expectedOutputText2 = "You are successfully logged out.";

        driver.findElement(burgerMenu).click();
        driver.findElement(loginMenu).click();
        driver.findElement(usernameInput).sendKeys(userName);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();

        String actualOutputText = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Products\"]")).getText();
        assertEquals(expectedOutputText, actualOutputText);
        driver.findElement(burgerMenu).click();
        driver.findElement(logoutMenu).click();
        driver.findElement(logoutButton).click();
        String actualOutputText2 = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/alertTitle\"]")).getText();
        assertEquals(expectedOutputText2, actualOutputText2);
    }

    @Test
    void loginWithLockedOutUser() {
        final String userName = "alice@example.com";
        final String password = "10203040";
        String expectedOutputText = "Sorry, this user has been locked out.";

        driver.findElement(burgerMenu).click();
        driver.findElement(loginMenu).click();
        driver.findElement(usernameInput).sendKeys(userName);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();

        String actualOutputText = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Sorry, this user has been locked out.\"]")).getText();
        assertEquals(expectedOutputText, actualOutputText);
    }

    @Test
    void loginWithNonExistingUser() {
        final String userName = "unknown@example.com";
        final String password = "10203040";
        String expectedOutputText = "Provided credentials do not match any user in this service.";

        driver.findElement(burgerMenu).click();
        driver.findElement(loginMenu).click();
        driver.findElement(usernameInput).sendKeys(userName);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();

        String actualOutputText = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Provided credentials do not match any user in this service.\"]")).getText();
        assertEquals(expectedOutputText, actualOutputText);
    }

    @Test
    void loginWithExistingUserButWrongPassword() {
        final String userName = "bob@example.com";
        final String password = "wrong";
        final String expectedOutputText = "Provided credentials do not match any user in this service.";

        driver.findElement(burgerMenu).click();
        driver.findElement(loginMenu).click();
        driver.findElement(usernameInput).sendKeys(userName);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();

        String actualOutputText = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Provided credentials do not match any user in this service.\"]")).getText();
        assertEquals(expectedOutputText, actualOutputText);
    }
}
