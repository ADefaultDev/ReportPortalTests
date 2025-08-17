package api.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import api.steps.DashboardApiSteps;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Набор API-тестов для управления Dashboard в Report Portal.
 * <p>
 * Данный класс проверяет возможность создания нового Dashboard через API
 * и подтверждает, что он успешно отображается в списке Dashboard.
 * Использует {@link BaseApiTest} для настройки {@code baseURI} и загрузки конфигурации.
 * <p>
 * Методы используют степы {@link DashboardApiSteps}, чтобы все действия отображались в Allure отчёте.
 *
 * @author ADefaultDev
 * @since 1.0
 */
@Epic("API Tests")
@Feature("Dashboard Management")
public class CreateDashboardTest extends BaseApiTest {

    /**
     * Проверяет успешное создание нового Dashboard через API и его наличие в списке.
     * <p>
     * Шаги теста:
     * <ol>
     *     <li>Создать уникальное имя для Dashboard</li>
     *     <li>Создать Dashboard</li>
     *     <li>Проверить существование Dashboard</li>
     * </ol>
     *
     * Ожидаемый результат:
     * <ul>
     *     <li>Dashboard успешно создаётся (HTTP 201)</li>
     *     <li>Dashboard отображается в списке существующих Dashboard (HTTP 200)</li>
     * </ul>
     */
    @Test
    @Story("Создание нового Dashboard через API")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка, что новый Dashboard успешно создаётся и отображается в списке через API")
    void createAndVerifyDashboard() {
        String token = config.getProperty("api.token");
        String projectName = config.getProperty("project.name");
        String dashboardName = "AutoDashboard_" + System.currentTimeMillis();

        DashboardApiSteps steps = new DashboardApiSteps(token, projectName);

        steps.createDashboard(dashboardName);

        List<Map<String, Object>> dashboards = steps.getDashboards();

        boolean found = dashboards.stream()
                .anyMatch(d -> dashboardName.equals(d.get("name")));

        assertTrue(found, "Созданный Dashboard должен присутствовать в списке");
    }
}
