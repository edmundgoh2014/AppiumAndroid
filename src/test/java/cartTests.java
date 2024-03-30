import io.appium.java_client.AppiumBy;
import lib.appiumDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class cartTests extends appiumDriver {
    public static final By itemHeader = AppiumBy.accessibilityId("container header");
    public static final String circleColour = "red circle";
    public static final By itemPrice = AppiumBy.accessibilityId("product price");
    public static final By colourButton = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\""+circleColour+"\"]/android.view.ViewGroup");
    public static final By zeroItemCounter = AppiumBy.xpath("//android.widget.TextView[@text=\"0\"]");
    public static final By minusItemCounter = AppiumBy.accessibilityId("counter minus button");
    public static final By plusItemCounter = AppiumBy.accessibilityId("counter plus button");
    public static final By addToCartButton = AppiumBy.accessibilityId("Add To Cart button");
    public static final By cartBadgeButton = AppiumBy.accessibilityId("cart badge");

    @Test
    void add1ItemToCart() {
        String backpackPrice = "$29.99";
        String itemName = "Sauce Labs Backpack";

        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"store item text\" and @text=\""+itemName+"\"]")).click();
        assertEquals(backpackPrice, driver.findElement(itemPrice).getText());
        driver.findElement(addToCartButton).click();
        driver.findElement(cartBadgeButton).click();
        assertEquals(itemName, driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"product label\" and @text=\"" + itemName + "\"]")).getText());
    }

    @Test
    void add0ItemToCart() {
        String backpackPrice = "$29.99";
        String counter = "0";
        String itemName = "Sauce Labs Backpack";

        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"store item text\" and @text=\""+itemName+"\"]")).click();
        assertEquals(backpackPrice, driver.findElement(itemPrice).getText());
        driver.findElement(minusItemCounter).click();
        assertEquals(counter, driver.findElement(zeroItemCounter).getText());
        assertFalse(driver.findElement(addToCartButton).isEnabled());
        driver.findElement(cartBadgeButton).click();
        List<WebElement> itemInCart = driver.findElements(By.xpath("//android.widget.TextView[@content-desc=\"product label\" and @text=\"" + itemName + "\"]"));
        assertTrue(itemInCart.isEmpty());
    }
}
