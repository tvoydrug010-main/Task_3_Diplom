package org.example.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PersonalAccountPage extends BasePage{

    public PersonalAccountPage(WebDriver driver) {
        super(driver);
    }
    //Кнопка Выход в Личном кабинете
    @FindBy(xpath = "//button[contains(., 'Выход')]")
    private WebElement buttonExit;

    public void clickButtonExit(){
        wait.until(ExpectedConditions.elementToBeClickable(buttonExit)).click();
    }

    public String getAccessToken(){
        String accessToken = (String) ((JavascriptExecutor) driver)
                .executeScript("return localStorage.getItem('accessToken');");
        return accessToken;
    }
}
