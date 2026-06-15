import org.example.pages.apiClient.CreateUser;
import org.example.pages.factory.UserFactory;
import org.example.pages.models.User;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTests extends BaseTests {
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
    //Переход по кнопке "Личный кабинет" без авторизации
    @Test
    @Tag("noRegistration")
    public void checkRedirectFromPersAccNotAuth(){
        homePage.clickButtonPersonalAccount();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/login"));
        assertTrue(driver.getCurrentUrl().contains("/login"),
                "Редирект завершился не успешно! Текущий URL: " + driver.getCurrentUrl());
    }

    //Переход по кнопке "Личный кабинет" c авторизацией
    @Test
    public void checkRedirectFromPersAccAuthSuccess(){
        homePage.clickButtonPersonalAccount();
        loginPage.setEmail(validUser.getEmail());
        loginPage.setPassword(validUser.getPassword());
        loginPage.clickButtonEnterInFormRegister();
        homePage.clickButtonPersonalAccount();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/profile"));
        assertTrue(driver.getCurrentUrl().contains("/profile"),
                "Редирект завершился не успешно! Текущий URL: " + driver.getCurrentUrl());
    }

    @Test
    public void checkRedirectToTheConstructorFromPersAcc(){
        homePage.clickButtonPersonalAccount();
        loginPage.setEmail(validUser.getEmail());
        loginPage.setPassword(validUser.getPassword());
        loginPage.clickButtonEnterInFormRegister();
        homePage.clickButtonPersonalAccount();
        homePage.clickButtonConstructor();
        String expectedColor = "rgba(255, 255, 255, 1)";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> expectedColor.equals(homePage.getColorButtonConstructor()));
        assertEquals(expectedColor, homePage.getColorButtonConstructor(),
                "Текст кнопки 'Конструктор' не стал белым! Значит редирект не осуществился");
        wait.until(ExpectedConditions.urlContains("https://qa-stellarburgers.education-services.ru/"));
        assertTrue(driver.getCurrentUrl().contains("https://qa-stellarburgers.education-services.ru"),
                "Редирект завершился не успешно! Текущий URL: " + driver.getCurrentUrl());
    }

    @Test
    public void checkCLickLogoStellarAndRedirect(){
        homePage.clickButtonPersonalAccount();
        loginPage.setEmail(validUser.getEmail());
        loginPage.setPassword(validUser.getPassword());
        loginPage.clickButtonEnterInFormRegister();
        homePage.clickButtonPersonalAccount();
        homePage.clickLogoStellar();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("https://qa-stellarburgers.education-services.ru/"));
        assertTrue(driver.getCurrentUrl().contains("https://qa-stellarburgers.education-services.ru"),
                "Редирект завершился не успешно! Текущий URL: " + driver.getCurrentUrl());
    }

    @AfterEach
    public void tearDown(TestInfo testInfo){
        boolean skipRegistration = testInfo.getTags().contains("noRegistration");
        if (!skipRegistration) {
            user.deleteUser();;
        }
    }

}
