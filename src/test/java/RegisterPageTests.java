import net.datafaker.Faker;
import org.example.pages.factory.UserFactory;
import org.example.pages.models.User;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterPageTests extends BaseTests{

    @Test
    public void registerSuccessUser(){
        User validUser = UserFactory.createValidUser();
        homePage.clickButtonLogInAcc();
        registerPage.clickLinkRegister();
        registerPage.setName(validUser.getName());
        registerPage.setEmail(validUser.getEmail());
        registerPage.setPassword(validUser.getPassword());
        registerPage.clickButtonRegister();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/login"));
        assertTrue(driver.getCurrentUrl().contains("/login"),
                "Регистрация завершилась не успешно! Текущий URL: " + driver.getCurrentUrl());
    }

    @Test
    public void registerInvalidPassword(){
        User invalidUser = UserFactory.createUserWithShortPassword();
        homePage.clickButtonLogInAcc();
        registerPage.clickLinkRegister();
        registerPage.setName(invalidUser.getName());
        registerPage.setEmail(invalidUser.getEmail());
        registerPage.setPassword(invalidUser.getPassword());
        registerPage.clickButtonRegister();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        By errorMessageLocator = By.xpath("//p[contains(.,'Некорректный пароль')]");
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        assertTrue(errorElement.isDisplayed(), "Сообщение об ошибке не отобразилось на экране!");
    }
}
