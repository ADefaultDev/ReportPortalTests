package ui.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ui.pages.DashboardPage;

/**
 * Шаги для работы с дашбордом и виджетами.
 * <p>
 * Используются для формирования Allure-отчётов.
 * Методы возвращают текущий объект {@link WidgetSteps} для цепочки вызовов (fluent interface),
 * кроме методов, возвращающих результат проверки (boolean).
 * <p>
 * Использует {@link DashboardPage} для взаимодействия с элементами страницы.
 *
 * @author ADefaultDev
 * @since 1.0
 */
public class WidgetSteps {

    private final DashboardPage dashboardPage;

    /**
     * Конструктор класса шагов работы с виджетами.
     *
     * @param driver экземпляр {@link WebDriver} для работы с браузером
     */
    public WidgetSteps(WebDriver driver) {
        this.dashboardPage = new DashboardPage(driver);
    }

    /**
     * Открывает список всех дашбордов.
     *
     * @return текущий объект {@link WidgetSteps} для цепочки вызовов
     */
    @Step("Открыть список дашбордов")
    public WidgetSteps openDashboardsList() {
        dashboardPage.openDashboardsList();
        return this;
    }

    /**
     * Открывает первый дашборд из списка.
     *
     * @return текущий объект {@link WidgetSteps} для цепочки вызовов
     */
    @Step("Открыть первый дашборд")
    public WidgetSteps openFirstDashboard() {
        dashboardPage.openFirstDashboard();
        return this;
    }

    /**
     * Нажимает кнопку "Add new widget" для добавления нового виджета.
     *
     * @return текущий объект {@link WidgetSteps} для цепочки вызовов
     */
    @Step("Нажать 'Add new widget'")
    public WidgetSteps clickAddNewWidget() {
        dashboardPage.clickAddNewWidget();
        return this;
    }

    /**
     * Выбирает первый доступный тип виджета для добавления.
     *
     * @return текущий объект {@link WidgetSteps} для цепочки вызовов
     */
    @Step("Выбрать первый тип виджета")
    public WidgetSteps selectFirstWidgetType() {
        dashboardPage.selectFirstWidgetType();
        return this;
    }

    /**
     * Переходит к следующему шагу добавления виджета.
     *
     * @return текущий объект {@link WidgetSteps} для цепочки вызовов
     */
    @Step("Перейти к следующему шагу")
    public WidgetSteps clickNextStep() {
        dashboardPage.clickNextStep();
        return this;
    }

    /**
     * Обрабатывает фильтр виджета.
     * <p>
     * Если фильтр существует — выбирает его, иначе создаёт новый.
     *
     * @return текущий объект {@link WidgetSteps} для цепочки вызовов
     */
    @Step("Обработать фильтр (если есть — выбрать, если нет — создать новый)")
    public WidgetSteps handleFilterIfExistsOrCreate() {
        dashboardPage.handleFilterIfExistsOrCreate();
        return this;
    }

    /**
     * Нажимает кнопку "Add" для подтверждения создания виджета.
     *
     * @return текущий объект {@link WidgetSteps} для цепочки вызовов
     */
    @Step("Нажать кнопку 'Add' для подтверждения создания виджета")
    public WidgetSteps clickAddButton() {
        dashboardPage.clickAddButton();
        return this;
    }

    /**
     * Проверяет, что виджет успешно добавлен на дашборд.
     *
     * @return true, если виджет добавлен успешно, иначе false
     */
    @Step("Проверить, что виджет создан успешно")
    public boolean isWidgetAddedSuccessfully() {
        return dashboardPage.isWidgetAddedSuccessfully();
    }

    /**
     * Полный сценарий создания виджета с фильтром.
     * <p>
     * Выполняет последовательность действий:
     * открыть список дашбордов → открыть первый дашборд → добавить новый виджет →
     * выбрать тип виджета → перейти через шаги → обработать фильтр → добавить виджет →
     * проверить успешность добавления.
     *
     * @return true, если виджет добавлен успешно, иначе false
     */
    @Step("Создать новый виджет с фильтром")
    public boolean createWidgetWithFilter() {
        return openDashboardsList()
                .openFirstDashboard()
                .clickAddNewWidget()
                .selectFirstWidgetType()
                .clickNextStep()
                .clickNextStep()
                .handleFilterIfExistsOrCreate()
                .clickNextStep()
                .clickAddButton()
                .isWidgetAddedSuccessfully();
    }
}
