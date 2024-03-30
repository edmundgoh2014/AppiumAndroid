package lib;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class testBase extends appiumDriver {
    public static final By burgerMenu = AppiumBy.accessibilityId("open menu");
    public static final By loginMenu = AppiumBy.accessibilityId("menu item log in");
    public static final By usernameInput = AppiumBy.accessibilityId("Username input field");
    public static final By passwordInput = AppiumBy.accessibilityId("Password input field");
    public static final By loginButton = AppiumBy.accessibilityId("Login button");
    public static final By logoutMenu = AppiumBy.accessibilityId("menu item log out");
    public static final By logoutButton = AppiumBy.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]");

    public void login(String username, String password){
        driver.findElement(testBase.burgerMenu).click();
        driver.findElement(testBase.loginMenu).click();
        driver.findElement(testBase.usernameInput).sendKeys(username);
        driver.findElement(testBase.passwordInput).sendKeys(password);
        driver.findElement(testBase.loginButton).click();
    }

    public void logout(){
        driver.findElement(testBase.burgerMenu).click();
        driver.findElement(testBase.logoutMenu).click();
        driver.findElement(testBase.logoutButton).click();
        WebElement logoutPopUpBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@resource-id='android:id/button1']")));
        logoutPopUpBtn.click();
    }
}
