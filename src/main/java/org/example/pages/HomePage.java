package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }
    //Кнопка "Войти в аккаунт" на главной странице
    @FindBy(xpath = "//button[contains(text(), 'Войти в аккаунт')]")
    private WebElement buttonLogInAcc;

    //Кнопка "Личный кабинет" на главной странице
    @FindBy(xpath = "//p[contains(., 'Личный Кабинет')]")
    private WebElement buttonPersonalAccount;
    //Кнопка "Конструктор"
    @FindBy(xpath = "//p[contains(.,'Конструктор')]")
    private WebElement buttonConstructor;
    //Лого Stellar Burgers
    @FindBy(css = "svg[width='290'][height='50']")
    private WebElement svgLogo;

    @Step("Кликаем по кнопке Войти в аккаунт")
    public void clickButtonLogInAcc(){
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(buttonLogInAcc));
        button.click();
    }

    @Step("Кликаем по кнопке Личный кабинет")
    public void clickButtonPersonalAccount(){
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(buttonPersonalAccount));
        button.click();
    }

    @Step("Кликаем по кнопке Конструктор")
    public void clickButtonConstructor(){
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(buttonConstructor));
        button.click();
    }

    @Step("Получем цвет кнопки при переходе на кнопку Конструктор")
    public String getColorButtonConstructor() {
        String actualColor = buttonConstructor.getCssValue("color");
        return actualColor;
    }

    @Step("Кликаем по логотипу Stellar Burgers")
    public void clickLogoStellar(){
        wait.until(ExpectedConditions.elementToBeClickable(svgLogo)).click();
    }

    @Step("Проверка успешного входа: переход на главную страницу + accessToken")
    public void verifySuccessfulLogin() {
        String expectedBaseUrl = "https://qa-stellarburgers.education-services.ru/";
        wait.until(ExpectedConditions.urlToBe(expectedBaseUrl));
        assertEquals(expectedBaseUrl, driver.getCurrentUrl(),
                "Ожидался переход на главную страницу, но URL: " + driver.getCurrentUrl());
        wait.until(ExpectedConditions.visibilityOf(buttonPersonalAccount));

        String accessToken = (String) ((JavascriptExecutor) driver)
                .executeScript("return localStorage.getItem('accessToken');");

        assertNotNull(accessToken, "accessToken не найден в localStorage! Авторизация не прошла.");
        assertFalse(accessToken.isEmpty(), "accessToken пустой!");
    }

}
