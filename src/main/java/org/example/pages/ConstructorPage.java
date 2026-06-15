package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConstructorPage extends BasePage{

    public ConstructorPage(WebDriver driver){
        super(driver);
    }

    //Вкладка Булки
    @FindBy(xpath = "//span[text()='Булки']/..")
    private WebElement tabBuns;

    //Вкладка Соусы
    @FindBy(xpath = "//span[text()='Соусы']")
    private WebElement tabSauces;

    //Вкладка Начинки
    @FindBy(xpath = "//span[text()='Начинки']")
    private WebElement tabToppings;

    @Step("Кликаем по кнопке Булки")
    public void clickButtonRolls(){
        WebElement element = wait.until(ExpectedConditions.visibilityOf(tabBuns));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    @Step("Кликаем по кнопке Соусы")
    public void clickButtonSauces(){
        WebElement element = wait.until(ExpectedConditions.visibilityOf(tabSauces));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    @Step("Кликаем по кнопке Начинки")
    public void clickButtonToppings(){
        WebElement element = wait.until(ExpectedConditions.visibilityOf(tabToppings));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    @Step("Получаем цвет вкладки 'Булки'")
    public String getColorBuns() {
        wait.until(ExpectedConditions.visibilityOf(tabBuns));
        return tabBuns.getCssValue("color");
    }

    @Step("Получаем цвет вкладки 'Соусы'")
    public String getColorSauces() {
        wait.until(ExpectedConditions.visibilityOf(tabSauces));
        return tabSauces.getCssValue("color");
    }

    @Step("Получаем цвет вкладки 'Начинки'")
    public String getColorToppings() {
        wait.until(ExpectedConditions.visibilityOf(tabToppings));
        return tabToppings.getCssValue("color");
    }

}
