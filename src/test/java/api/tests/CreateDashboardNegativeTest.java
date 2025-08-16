package api.tests;


import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import api.steps.DashboardApiSteps;

/**
 * Негативный тест для проверки функционала создания Dashboard через API с недостаточными параметрами.
 * <p>
 * Цель теста — убедиться, что API корректно обрабатывает попытки создания Dashboard без обязательных параметров,
 * возвращает код ошибки и не создаёт объект в системе.
 * <p>
 * Использует {@link BaseApiTest} для инициализации конфигурации и baseURI.
 * Шаги теста:
 * <ol>
 *     <li>Попытка создать Dashboard без обязательного имени через POST-запрос.</li>
 *     <li>Проверка, что API возвращает код ошибки 400.</li>
 *     <li>Получение списка Dashboard через GET-запрос.</li>
 *     <li>Проверка, что Dashboard с пустым именем отсутствует в списке.</li>
 * </ol>
 *
 * @author ADefaultDev
 * @since 1.0
 */
@Epic("API Tests")
@Feature("Dashboard Management")
public class CreateDashboardNegativeTest extends BaseApiTest {

    /**
     * Проверка, что API возвращает ошибку при создании Dashboard без обязательных параметров
     * и некорректный объект не появляется в списке Dashboard.
     */
    @Test
    @Story("Создание Dashboard с недостаточными параметрами")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка, что API возвращает ошибку при создании Dashboard без обязательных параметров")
    void createDashboardWithMissingParams() {
        String token = config.getProperty("api.token");
        String projectName = config.getProperty("project.name");
        String dashboardName = "";

        DashboardApiSteps steps = new DashboardApiSteps(token, projectName);

        steps.createDashboardWithMissingParams();
        steps.verifyDashboardNotExists(dashboardName);
    }
}
