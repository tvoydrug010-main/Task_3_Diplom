package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    //Кнопка "Войти" в форме регистрации (на самом деле общая кнопка для всех страниц)
    @FindBy(xpath = "//button[contains(@class, 'button_type_primary') and text()='Войти']")
    private WebElement buttonEnterInFormRegister;

    //Ссылка "Восстановить пароль"
    @FindBy(xpath = "//a[@href='/forgot-password']")
    private WebElement linkRecoverPassword;

    //Поле ввода Email
    @FindBy(xpath = "//input[@name='name']")
    private WebElement inputEmail;
    //Поле ввода Пароль
    @FindBy(xpath = "//input[@name='Пароль']")
    private WebElement inputPassword;

    public  LoginPage(WebDriver driver){
        super(driver);
    }

    @Step("Кликаем по кнопке Войти")
    public void clickButtonEnterInFormRegister(){
        wait.until(ExpectedConditions.elementToBeClickable(buttonEnterInFormRegister)).click();
    }
    @Step("Заполняем поле Email")
    public void setEmail(String email){
        inputEmail.sendKeys(email);
    }
    @Step("Заполняем поле Пароль")
    public void setPassword(String password){
        inputPassword.sendKeys(password);
    }

    @Step("Кликаем по ссылке Восстановить пароль")
    public void clickLinkRecoverPassword(){
        wait.until(ExpectedConditions.elementToBeClickable(linkRecoverPassword)).click();
    }
}
