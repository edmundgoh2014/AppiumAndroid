import io.appium.java_client.AppiumBy;
import lib.appiumDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class reviewTests extends appiumDriver {
    public static final By item = AppiumBy.linkText("Sauce Labs Backpack");
    public static final By itemHeader = AppiumBy.accessibilityId("container header");
    public static final String circleColour = "red circle";
    public static final By itemPrice = AppiumBy.accessibilityId("product price");
    public static final By review5StarsButton = AppiumBy.accessibilityId("review star 5");
    public static final By closeReviewPopUpButton = AppiumBy.accessibilityId("Close Modal button");
    public static final By colourButton = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\""+circleColour+"\"]/android.view.ViewGroup");
    public static final By itemCounter = AppiumBy.accessibilityId("counter amount");
    public static final By minusItemCounter = AppiumBy.accessibilityId("counter minus button");
    public static final By plusItemCounter = AppiumBy.accessibilityId("counter plus button");
    public static final By addToCartButton = AppiumBy.accessibilityId("Add To Cart button");
    public static final By cartBadgeButton = AppiumBy.accessibilityId("cart badge");

    @Test
    void reviewInCartPage() {
        final String backpackPrice = "$29.99";
        final String reviewPopUp = "Thank you for submitting your review!";
        final String itemName = "Sauce Labs Backpack";

        driver.findElement(item).click();
        assertEquals(backpackPrice, driver.findElement(itemPrice).getText());
        driver.findElement(review5StarsButton).click();
        assertEquals(reviewPopUp, driver.findElement(By.xpath("//android.widget.TextView[@text=\""+reviewPopUp+"\"]")).getText());
        driver.findElement(closeReviewPopUpButton).click();
        driver.findElement(addToCartButton).click();
        driver.findElement(cartBadgeButton).click();
        assertEquals(itemName, driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"product label\" and @text=\"" + itemName + "\"]")).getText());
    }

    @Test
    void reviewInHomePage() {
        final String backpackPrice = "$29.99";
        final String reviewPopUp = "Thank you for submitting your review!";
        final String itemName = "Sauce Labs Backpack";

        driver.findElement(item).click();
        assertEquals(backpackPrice, driver.findElement(itemPrice).getText());
        driver.findElement(review5StarsButton).click();
        assertEquals(reviewPopUp, driver.findElement(By.xpath("//android.widget.TextView[@text=\""+reviewPopUp+"\"]")).getText());
        driver.findElement(closeReviewPopUpButton).click();
        driver.findElement(addToCartButton).click();
        driver.findElement(cartBadgeButton).click();
        assertEquals(itemName, driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"product label\" and @text=\"" + itemName + "\"]")).getText());
    }
}
