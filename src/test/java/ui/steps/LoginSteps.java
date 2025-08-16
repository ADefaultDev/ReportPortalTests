package ui.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ui.pages.LoginPage;

/**
 * Шаги авторизации в системе для Allure-отчётов.
 * <p>
 * Использует {@link LoginPage} для выполнения действий на странице логина.
 * Методы возвращают объект {@link LoginSteps} для возможности цепочки вызовов (fluent interface),
 * кроме метода clickLoginButton() и login(), которые завершают действие.
 *
 * @author ADefaultDev
 * @since 1.0
 */
public class LoginSteps {

    private final LoginPage loginPage;

    /**
     * Конструктор класса шагов авторизации.
     *
     * @param driver экземпляр WebDriver для работы с браузером
     */
    public LoginSteps(WebDriver driver) {
        this.loginPage = new LoginPage(driver);
    }

    /**
     * Открывает страницу логина по указанному URL.
     *
     * @param url адрес страницы логина
     * @return текущий объект {@link LoginSteps} для цепочки вызовов
     */
    @Step("Открыть страницу логина по URL: {url}")
    public LoginSteps openLoginPage(String url) {
        loginPage.open(url);
        return this;
    }

    /**
     * Вводит логин пользователя в поле ввода.
     *
     * @param username имя пользователя для авторизации
     * @return текущий объект {@link LoginSteps} для цепочки вызовов
     */
    @Step("Ввести логин: {username}")
    public LoginSteps enterLogin(String username) {
        loginPage.enterLogin(username);
        return this;
    }

    /**
     * Вводит пароль пользователя в поле ввода.
     *
     * @param password пароль пользователя
     * @return текущий объект {@link LoginSteps} для цепочки вызовов
     */
    @Step("Ввести пароль")
    public LoginSteps enterPassword(String password) {
        loginPage.enterPassword(password);
        return this;
    }

    /**
     * Нажимает кнопку "Login" для отправки формы авторизации
     * <p>
     * Этот метод завершает цепочку действий и не возвращает объект {@link LoginSteps}.
     */
    @Step("Нажать кнопку 'Login'")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    /**
     * Полный сценарий авторизации пользователя.
     * <p>
     * Выполняет шаги: открыть страницу логина → ввести логин → ввести пароль → нажать "Login".
     *
     * @param url      адрес страницы логина
     * @param username имя пользователя
     * @param password пароль пользователя
     */
    @Step("Авторизоваться как {username}")
    public void login(String url, String username, String password) {
        openLoginPage(url)
                .enterLogin(username)
                .enterPassword(password)
                .clickLoginButton();
    }
}
