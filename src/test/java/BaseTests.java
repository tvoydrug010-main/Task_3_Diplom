import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BaseTests {
    protected WebDriver driver;
    protected HomePage homePage;
    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    protected PersonalAccountPage personalAccountPage;
    protected ConstructorPage constructorPage;

    /**
     * Из за разных версий браузеров, реализовано через if/else
     * - для запуска в ChromeBrowser пишем mvn test
     * - для запуска в YandexBrowser пишем -Dbrowser=yandex
     */
    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        if (browser.equalsIgnoreCase("yandex")) {
            options.setBinary("C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe");
            WebDriverManager.chromedriver().browserVersion("146").setup();
        } else {
            options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
            WebDriverManager.chromedriver().browserVersion("148").setup();
        }

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://qa-stellarburgers.education-services.ru/");

        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
        constructorPage = new ConstructorPage(driver);
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

}
