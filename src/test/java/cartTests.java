import io.appium.java_client.AppiumBy;
import lib.testBase;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class cartTests extends testBase {
    public static final By actualTotalNoOfItem = AppiumBy.accessibilityId("total number");
    public static final By actualTotalPrice = AppiumBy.accessibilityId("total price");
    public static final By cartBadgeButton = AppiumBy.accessibilityId("cart badge");
    public static final String expectedProductPrice = "$29.99";
    public static final String itemName = "Sauce Labs Backpack";

    @Test
    void add1ItemToCart() {
        int quantity = 1;

        CartItemInfo cartItemInfo = addItemToCart(itemName, quantity);
        String actualProductPrice = cartItemInfo.getActualProductPrice();
        String actualQuantity = cartItemInfo.getActualQuantity();

        assertEquals(expectedProductPrice, actualProductPrice);
        assertEquals(Integer.toString(quantity), actualQuantity);
        driver.findElement(cartBadgeButton).click();
        assertEquals(itemName, driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"product label\" and @text=\"" + itemName + "\"]")).getText());
        assertEquals(expectedProductPrice, driver.findElement(actualTotalPrice).getText());
        assertEquals(quantity + " item", driver.findElement(actualTotalNoOfItem).getText());
    }

    @Test
    void add0ItemToCart() {
        int quantity = 0;

        CartItemInfo cartItemInfo = addItemToCart(itemName, quantity);
        String actualProductPrice = cartItemInfo.getActualProductPrice();
        String actualQuantity = cartItemInfo.getActualQuantity();

        assertEquals(expectedProductPrice, actualProductPrice);
        assertEquals(Integer.toString(quantity), actualQuantity);
        assertFalse(driver.findElement(addToCartButton).isEnabled());
        driver.findElement(cartBadgeButton).click();
        List<WebElement> itemInCart = driver.findElements(By.xpath("//android.widget.TextView[@content-desc=\"product label\" and @text=\"" + itemName + "\"]"));
        assertTrue(itemInCart.isEmpty());
    }
}
