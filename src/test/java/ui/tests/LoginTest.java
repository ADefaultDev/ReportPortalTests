package ui.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.steps.LoginSteps;
import ui.utils.Config;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Набор UI-тестов для проверки процесса аутентификации в Report Portal.
 * <p>
 * Использует JUnit 5 и Allure для описания и отчетности.
 * Наследует {@link BaseTest}
 *
 * @author ADefaultDev
 * @since 1.0
 */
@Epic("UI Tests")
@Feature("Authentication")
public class LoginTest extends BaseTest {

    /**
     * Проверяет успешный вход в Report Portal с корректными учетными данными.
     * <p>
     * Шаги теста:
     * <ol>
     *     <li>Авторизоваться через {@link LoginSteps} с использованием данных из конфигурации.</li>
     *     <li>Дождаться редиректа на страницу запусков.</li>
     * </ol>
     *
     * Ожидаемый результат:
     * <ul>
     *     <li>URL должен содержать <b>#default_personal/launches/all</b>,
     *     что означает успешную аутентификацию.</li>
     * </ul>
     */
    @Test
    @Story("Успешный вход в Report Portal")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Проверка, что пользователь может войти в систему с корректными учетными данными")
    void successfulLoginTest() {
        LoginSteps loginSteps = new LoginSteps(driver);

        loginSteps.login(
                Config.get("base.url"),
                Config.get("username"),
                Config.get("password")
        );

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean urlChanged = wait.until(ExpectedConditions.urlContains("#default_personal/launches/all"));

        assertTrue(urlChanged, "URL должен содержать '#default_personal/launches/all' после успешного входа");
    }
}
