import lib.testBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import config.testConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAuthenticationTests extends testBase {

    @Test
    void loginWithCorrectUser() {
        String homePageTitleText = "Products";

        login();
        WebElement homePageTitle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Products\"]")));
        assertEquals(homePageTitleText, homePageTitle.getText());
        logout();
    }

    @Test
    void loginWithLockedOutUser() {
        String userName = "alice@example.com";
        String password = "10203040";
        String userLockedOutPopUpText = "Sorry, this user has been locked out.";

        login(userName, password);
        WebElement userLockedOutPopUp = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"" + userLockedOutPopUpText + "\"]")));
        assertEquals(userLockedOutPopUpText, userLockedOutPopUp.getText());
    }

    @Test
    void loginWithNonExistingUser() {
        String userName = "unknown@example.com";
        String password = "unknown";
        String noSuchUserPopUpText = "Provided credentials do not match any user in this service.";

        login(userName, password);
        WebElement noSuchUserPopUp = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"" + noSuchUserPopUpText + "\"]")));
        assertEquals(noSuchUserPopUpText, noSuchUserPopUp.getText());
    }

    @Test
    void loginWithExistingUserButWrongPassword() {
        String password = "wrong";
        String wrongCredentialsPopUpText = "Provided credentials do not match any user in this service.";

        login(testConfig.username, password);
        WebElement wrongCredentialsPopUp = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"" + wrongCredentialsPopUpText + "\"]")));
        assertEquals(wrongCredentialsPopUpText, wrongCredentialsPopUp.getText());
    }
}
