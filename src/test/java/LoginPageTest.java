import net.datafaker.Faker;
import org.example.pages.apiClient.CreateUser;
import org.example.pages.factory.UserFactory;
import org.example.pages.models.User;
import org.junit.jupiter.api.*;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPageTest extends BaseTests{
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
    public void checkButtonLogInAcc() {
        homePage.clickButtonLogInAcc();
        loginPage.setEmail(validUser.getEmail());
        loginPage.setPassword(validUser.getPassword());
        loginPage.clickButtonEnterInFormRegister();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/login"));
        homePage.verifySuccessfulLogin();
    }

    @Test
    public void checkButtonPersonalAccount(){
        homePage.clickButtonPersonalAccount();
        loginPage.setEmail(validUser.getEmail());
        loginPage.setPassword(validUser.getPassword());
        loginPage.clickButtonEnterInFormRegister();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/login"));
        homePage.verifySuccessfulLogin();
    }

    @Test
    public void checkButtonEnterFormRegister(){
        homePage.clickButtonPersonalAccount();
        registerPage.clickLinkRegister();
        registerPage.clickLinkEnter();
        loginPage.setEmail(validUser.getEmail());
        loginPage.setPassword(validUser.getPassword());
        loginPage.clickButtonEnterInFormRegister();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/login"));
        homePage.verifySuccessfulLogin();
    }
    @Test
    public void checkButtonEnterFormRecoverPassword(){
        homePage.clickButtonPersonalAccount();
        loginPage.clickLinkRecoverPassword();
        registerPage.clickLinkEnter();
        loginPage.setEmail(validUser.getEmail());
        loginPage.setPassword(validUser.getPassword());
        loginPage.clickButtonEnterInFormRegister();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/login"));
        homePage.verifySuccessfulLogin();
    }

    @AfterEach
    public void tearDown(){
        user.deleteUser();
    }
}
