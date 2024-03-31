package lib;

import io.appium.java_client.AppiumBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import config.testConfig;

public class testBase extends appiumDriver {
    public static final String circleColour = "red circle";
    public static final By burgerMenu = AppiumBy.accessibilityId("open menu");
    public static final By loginMenu = AppiumBy.accessibilityId("menu item log in");
    public static final By usernameInput = AppiumBy.accessibilityId("Username input field");
    public static final By passwordInput = AppiumBy.accessibilityId("Password input field");
    public static final By loginButton = AppiumBy.accessibilityId("Login button");
    public static final By logoutMenu = AppiumBy.accessibilityId("menu item log out");
    public static final By logoutButton = AppiumBy.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]");
    public static final By addToCartButton = AppiumBy.accessibilityId("Add To Cart button");
    public static final By productPrice = AppiumBy.accessibilityId("product price");
    public static final By minusItemCounter = AppiumBy.accessibilityId("counter minus button");
    public static final By plusItemCounter = AppiumBy.accessibilityId("counter plus button");
  
    public void login(String username, String password){
        driver.findElement(testBase.burgerMenu).click();
        driver.findElement(testBase.loginMenu).click();
        driver.findElement(testBase.usernameInput).sendKeys(username);
        driver.findElement(testBase.passwordInput).sendKeys(password);
        driver.findElement(testBase.loginButton).click();
    }

    // Overloaded method with no parameters, providing default values for both username and password
    public void login() {
        login(testConfig.username, testConfig.password);
    }

    public void logout(){
        driver.findElement(testBase.burgerMenu).click();
        driver.findElement(testBase.logoutMenu).click();
        driver.findElement(testBase.logoutButton).click();
        WebElement logoutPopUpBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@resource-id='android:id/button1']")));
        logoutPopUpBtn.click();
    }

    public class CartItemInfo {
        private String actualProductPrice;
        private String actualQuantity;
    
        public CartItemInfo(String actualProductPrice, String actualQuantity) {
            this.actualProductPrice = actualProductPrice;
            this.actualQuantity = actualQuantity;
        }
    
        public String getActualProductPrice() {
            return actualProductPrice;
        }
    
        public String getActualQuantity() {
            return actualQuantity;
        }
    }

    public CartItemInfo addItemToCart(String productName, int quantity) {
        // Validate quantity
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be greater than or equal to 0.");
        }

        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"store item text\" and @text=\""+productName+"\"]")).click();
        String actualProductPrice = driver.findElement(productPrice).getText();
        if (quantity > 1) {
            for(int i=1; i<quantity; i++){
                driver.findElement(plusItemCounter).click();
            }
        } else if (quantity == 0){
            driver.findElement(minusItemCounter).click();
        }
        String actualQuantity = driver.findElement(By.xpath("//android.widget.TextView[@text=\""+quantity+"\"]")).getText();
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\""+circleColour+"\"]/android.view.ViewGroup")).click();
        driver.findElement(addToCartButton).click();

        // Return product actual price and actual quantity
        return new CartItemInfo(actualProductPrice, actualQuantity);
    }
}
