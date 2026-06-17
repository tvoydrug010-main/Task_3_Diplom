import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstructorPageTest extends BaseTests{

    @Test
    public void checkClickTabBuns(){
        constructorPage.clickButtonRolls();
        String expectedColor = "rgba(255, 255, 255, 1)";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> expectedColor.equals(constructorPage.getColorBuns()));
        assertEquals(expectedColor, constructorPage.getColorBuns(),
                "Текст вкладки 'Булки>' не стал белым! Значит переход не осуществился");

    }
    @Test
    public void checkClickTabSauces(){
        constructorPage.clickButtonSauces();
        String expectedColor = "rgba(255, 255, 255, 1)";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> expectedColor.equals(constructorPage.getColorSauces()));
        assertEquals(expectedColor, constructorPage.getColorSauces(),
                "Текст вкладки 'Соусы>' не стал белым! Значит переход не осуществился");
    }

    @Test
    public void checkClickTabToppings(){
        constructorPage.clickButtonToppings();
        String expectedColor = "rgba(255, 255, 255, 1)";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> expectedColor.equals(constructorPage.getColorToppings()));
        assertEquals(expectedColor, constructorPage.getColorToppings(),
                "Текст вкладки 'Начинки>' не стал белым! Значит переход не осуществился");
    }

}
