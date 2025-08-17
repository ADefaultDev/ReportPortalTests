package api.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

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
     * Возвращает список dashboard на странице.
     * <p>
     * @return List - список dashboard.
     */
    @Step("Получить список Dashboard")
    public List<Map<String, Object>> getDashboards() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/v1/" + projectName + "/dashboard")
                .then()
                .statusCode(200)
                .extract().response();

        return response.jsonPath().getList("content");
    }
}
