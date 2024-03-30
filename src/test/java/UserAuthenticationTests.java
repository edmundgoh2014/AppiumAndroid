import lib.testBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAuthenticationTests extends testBase {

    @Test
    void loginWithCorrectUser() {
        final String userName = "bob@example.com";
        final String password = "10203040";
        String homePageTitleText = "Products";

        login(userName, password);
        WebElement homePageTitle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Products\"]")));
        assertEquals(homePageTitleText, homePageTitle.getText());
        logout();
    }

    @Test
    void loginWithLockedOutUser() {
        final String userName = "alice@example.com";
        final String password = "10203040";
        String userLockedOutPopUpText = "Sorry, this user has been locked out.";

        login(userName, password);
        WebElement userLockedOutPopUp = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"" + userLockedOutPopUpText + "\"]")));
        assertEquals(userLockedOutPopUpText, userLockedOutPopUp.getText());
        logout();
    }

    @Test
    void loginWithNonExistingUser() {
        final String userName = "unknown@example.com";
        final String password = "10203040";
        String noSuchUserPopUpText = "Provided credentials do not match any user in this service.";

        login(userName, password);
        WebElement noSuchUserPopUp = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"" + noSuchUserPopUpText + "\"]")));
        assertEquals(noSuchUserPopUpText, noSuchUserPopUp.getText());
        logout();
    }

    @Test
    void loginWithExistingUserButWrongPassword() {
        final String userName = "bob@example.com";
        final String password = "wrong";
        final String wrongCredentialsPopUpText = "Provided credentials do not match any user in this service.";

        login(userName, password);
        WebElement wrongCredentialsPopUp = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"" + wrongCredentialsPopUpText + "\"]")));
        assertEquals(wrongCredentialsPopUpText, wrongCredentialsPopUp.getText());
        logout();
    }
}
