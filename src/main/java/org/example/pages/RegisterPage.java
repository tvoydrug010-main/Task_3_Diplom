package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends BasePage{
    //Сслыка на функционал "Зарегистрироваться"
    @FindBy(xpath = "//a[@href='/register' and text()='Зарегистрироваться']")
    private WebElement linkRegister;
    //Поле ввода Имя
    @FindBy(xpath = "//label[normalize-space()='Имя']/following-sibling::input")
    private WebElement inputName;
    //Поле ввода Email
    @FindBy(xpath = "//label[normalize-space()='Email']/following-sibling::input")
    private WebElement inputEmail;
    //Поле ввода Пароль
    @FindBy(xpath = "//label[normalize-space()='Пароль']/following-sibling::input")
    private WebElement inputPassword;
    //Кпопка Зарегистрироваться
    @FindBy(xpath = "//button[contains(., 'Зарегистрироваться')]")
    private WebElement buttonRegister;
    //Ссылка "Войти"
    @FindBy(xpath = "//a[@href='/login']")
    private WebElement linkEnter;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Кликаем по ссылке Зарегистрироваться")
    public void clickLinkRegister(){
        wait.until(ExpectedConditions.elementToBeClickable(linkRegister)).click();
    }
    @Step("Заполняем поле Имя")
    public void setName(String name){
        inputName.sendKeys(name);
    }
    @Step("Заполняем поле Email")
    public void setEmail(String email){
        inputEmail.sendKeys(email);
    }
    @Step("Заполняем поле Пароль")
    public void setPassword(String password){
        inputPassword.sendKeys(password);
    }
    @Step("Кликаем по кнопке Зарегистрироваться")
    public void clickButtonRegister(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(buttonRegister));
        button.click();
    }

    @Step("Кликаем по сслыке Войти")
    public void clickLinkEnter(){
        wait.until(ExpectedConditions.elementToBeClickable(linkEnter)).click();
    }

    @Step("Полный процесс регистрации по шагам")
    public void allStepForRegister(String name, String email, String password){
        clickLinkRegister();
        setName(name);
        setEmail(email);
        setPassword(password);
        clickButtonRegister();
    }
}
