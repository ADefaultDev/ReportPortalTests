package api.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Класс шагов API для управления Dashboard.
 * <p>
 * Содержит методы для создания Dashboard, проверки их существования или отсутствия
 * в списке через API. Используется совместно с тестами для разделения шагов и логики проверок.
 * <p>
 * Все методы снабжены аннотацией {@link Step} для отображения в отчётах Allure.
 *
 * @author ADefaultDev
 * @since 1.0
 */
public class DashboardApiSteps {

    private final String token;
    private final String projectName;

    /**
     * Конструктор класса шагов.
     *
     * @param token       токен авторизации для API
     * @param projectName имя проекта, в рамках которого создаются Dashboard
     */
    public DashboardApiSteps(String token, String projectName) {
        this.token = token;
        this.projectName = projectName;
    }

    /**
     * Создание нового Dashboard через API.
     *
     * @param dashboardName имя создаваемого Dashboard
     */
    @Step("Создать новый Dashboard: {dashboardName}")
    public void createDashboard(String dashboardName) {
        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body("{\"name\":\"" + dashboardName + "\",\"description\":\"Created via API\",\"share\":false}")
                .when()
                .post("/v1/" + projectName + "/dashboard")
                .then()
                .statusCode(201);
    }

    /**
     * Проверка, что Dashboard с заданным именем существует в списке.
     *
     * @param dashboardName имя проверяемого Dashboard
     */
    @Step("Проверить, что Dashboard {dashboardName} существует в списке")
    public void verifyDashboardExists(String dashboardName) {
        List<Map<String, Object>> dashboards = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/v1/" + projectName + "/dashboard")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("content");

        boolean found = dashboards.stream().anyMatch(d -> dashboardName.equals(d.get("name")));
        assertTrue(found, "Созданный Dashboard должен присутствовать в списке");
    }

    /**
     * Попытка создать Dashboard без обязательных параметров (негативный сценарий).
     * <p>
     * Проверяется, что API возвращает код ошибки 400.
     */
    @Step("Попытка создать Dashboard без обязательных параметров")
    public void createDashboardWithMissingParams() {
        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body("{\"description\":\"Missing name\",\"share\":false}")
                .when()
                .post("/v1/" + projectName + "/dashboard")
                .then()
                .statusCode(400);
    }

    /**
     * Проверка, что Dashboard с заданным именем отсутствует в списке.
     *
     * @param dashboardName имя Dashboard, которого не должно быть в списке
     */
    @Step("Проверка, что Dashboard с именем '{dashboardName}' отсутствует в списке")
    public void verifyDashboardNotExists(String dashboardName) {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/v1/" + projectName + "/dashboard")
                .then()
                .statusCode(200)
                .extract().response();

        List<Map<String, Object>> dashboards = response.jsonPath().getList("content");
        boolean found = dashboards.stream()
                .anyMatch(d -> dashboardName.equals(d.get("name")));

        assertFalse(found, "Некорректный Dashboard не должен существовать в списке");
    }
}
