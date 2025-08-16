package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object для страницы Login в веб-приложении.
 * <p>
 * Содержит методы для ввода логина и пароля на странице авторизации,
 * Использует Selenium WebDriver для поиска и взаимодействия с элементами страницы.
 *
 * @author ADefaultDev
 * @since 1.0
 */
public class LoginPage {

    private final WebDriver driver;

    private final By loginButton = By.cssSelector("button[type='submit']");

    /**
     * Конструктор страницы Login.
     *
     * @param driver экземпляр {@link WebDriver}, используемый для управления браузером.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Открывает страницу логина.
     *
     * @param baseUrl - url сайта авторизации.
     */
    public void open(String baseUrl) {
        driver.get(baseUrl);
    }

    /**
     * Вводит логин пользователя.
     *
     * @param login логин пользователя.
     */
    public void enterLogin(String login) {
        WebElement loginInput = driver.findElement(By.name("login"));
        loginInput.clear();
        loginInput.sendKeys(login);
    }

    /**
     * Вводит пароль пользователя.
     *
     * @param password пароль пользователя.
     */
    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(By.cssSelector("input[type='password']"));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    /**
     * Нажимает на кнопку "login".
     */
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
        new DashboardPage(driver);
    }
}
