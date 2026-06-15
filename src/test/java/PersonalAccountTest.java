import org.example.pages.apiClient.CreateUser;
import org.example.pages.factory.UserFactory;
import org.example.pages.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonalAccountTest extends BaseTests {
    private User validUser;
    private CreateUser user;


    @BeforeEach
    public void createUser(TestInfo testInfo){
        validUser =  UserFactory.createValidUser();
        user = new CreateUser(validUser.getEmail(), validUser.getPassword(), validUser.getName());
        boolean skipRegistration = testInfo.getTags().contains("noRegistration");
        if (!skipRegistration) {
            user.sendCreateUserSuccess();
        }
    }

    @Test
    public void checkLoggingOutOfYourAccount(){
        homePage.clickButtonPersonalAccount();
        loginPage.setEmail(validUser.getEmail());
        loginPage.setPassword(validUser.getPassword());
        loginPage.clickButtonEnterInFormRegister();
        homePage.clickButtonPersonalAccount();
        personalAccountPage.clickButtonExit();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("/login"));
        assertTrue(driver.getCurrentUrl().contains("/login"),
                "Редирект завершился не успешно! Текущий URL: " + driver.getCurrentUrl());

        assertTrue(personalAccountPage.getAccessToken() == null ||
                personalAccountPage.getAccessToken().isEmpty(),
                "accessToken всё ещё в localStorage! Выход не произошёл. Значение: " + personalAccountPage.getAccessToken());
    }

    @AfterEach
    public void tearDown(){
        user.deleteUser();
    }
}
