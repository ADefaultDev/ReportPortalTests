package ui.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import ui.steps.LoginSteps;
import ui.steps.WidgetSteps;
import ui.utils.Config;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Набор UI-тестов для проверки функционала создания виджетов на дашборде.
 * <p>
 * Использует JUnit 5 и Allure для описания шагов и формирования отчётов.
 * Наследуется от {@link BaseTest}, который отвечает за инициализацию WebDriver
 * и загрузку конфигурации.
 *
 * @author ADefaultDev
 * @since 1.0
 */
@Epic("UI Tests")
@Feature("Dashboard")
public class CreateWidgetTest extends BaseTest {

    /**
     * Проверяет возможность создать виджет на дашборде с использованием фильтра.
     * <p>
     * Шаги теста:
     * <ol>
     *     <li>Авторизоваться в системе с валидными данными (через {@link LoginSteps}).</li>
     *     <li>Создать новый виджет на дашборде с фильтром (через {@link WidgetSteps}).</li>
     * </ol>
     *
     * Ожидаемый результат:
     * <ul>
     *     <li>Виджет успешно добавлен, о чём свидетельствует всплывающее уведомление
     *     об успешном добавлении.</li>
     * </ul>
     */
    @Test
    @Story("Создание виджета с фильтром")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет возможность создать виджет на дашборде с фильтром, если фильтр отсутствует - создает его")
    void createWidgetWithFilterTest() {
        LoginSteps loginSteps = new LoginSteps(driver);
        WidgetSteps widgetSteps = new WidgetSteps(driver);

        loginSteps.login(
                Config.get("base.url"),
                Config.get("username"),
                Config.get("password")
        );

        assertTrue(widgetSteps.createWidgetWithFilter(), "Виджет должен быть успешно добавлен");
    }
}
