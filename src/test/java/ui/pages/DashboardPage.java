package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object для страницы Dashboard в веб-приложении.
 * <p>
 * Содержит методы для навигации по списку Dashboard, открытия первого Dashboard,
 * добавления нового виджета и управления фильтрами.
 * Использует Selenium WebDriver для поиска и взаимодействия с элементами страницы.
 *
 * <p>Каждый метод возвращает {@link DashboardPage} </p>
 *
 * @author ADefaultDev
 * @since 1.0
 */
public class DashboardPage {

    private final WebDriverWait wait;
    private final WebDriver driver;

    private final By dashboardsButton = By.cssSelector("a[href='#default_personal/dashboard']");
    private final By firstDashboardName = By.cssSelector(".dashboardTable__name--t2a89");
    private final By addNewWidgetButton = By.xpath("//button[.//span[text()='Add new widget']]");
    private final By firstRadioOption = By.cssSelector(".inputRadio__toggler--ygpdQ");
    private final By nextStepButton = By.xpath("//span[text()='Next step']");

    private final By addFilterButton = By.xpath("//span[text()='Add filter']");
    private final By filterNameInput = By.cssSelector("input[placeholder='Input filter name']");
    private final By enterNameInput = By.cssSelector("input[placeholder='Enter name']");
    private final By submitFilterButton = By.cssSelector("button:has(span:contains('Submit')), button[title='']");

    private final By addButton = By.xpath("//button[contains(., 'Add')]");

    private final By successPopup = By.cssSelector("div._system-alert_14lm6_1._success_14lm6_14.notification-item");

    /**
     * Конструктор страницы Dashboard.
     *
     * @param driver экземпляр {@link WebDriver}, используемый для управления браузером.
     */
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Открывает список доступных Dashboard.
     */
    public void openDashboardsList() {
        wait.until(ExpectedConditions.elementToBeClickable(dashboardsButton)).click();
    }

    /**
     * Открывает первый Dashboard в списке.
     */
    public void openFirstDashboard() {
        wait.until(ExpectedConditions.elementToBeClickable(firstDashboardName)).click();
    }

    /**
     * Нажимает кнопку "Add new widget".
     */
    public void clickAddNewWidget() {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(addNewWidgetButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

    /**
     * Выбирает первый доступный тип виджета.
     */
    public void selectFirstWidgetType() {
        wait.until(ExpectedConditions.elementToBeClickable(firstRadioOption)).click();
    }

    /**
     * Переходит к следующему шагу добавления виджета.
     */
    public void clickNextStep() {
        wait.until(ExpectedConditions.elementToBeClickable(nextStepButton)).click();
    }

    /**
     * Проверяет, доступен ли выбор фильтра.
     * Если да — выбирает первый фильтр.
     * Если нет — создаёт новый фильтр.
     */
    public void handleFilterIfExistsOrCreate() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(firstRadioOption));
            driver.findElement(firstRadioOption).click();
        } catch (TimeoutException e) {
            addFilter("New", "New");
        }
    }

    /**
     * Добавляет новый фильтр с указанными параметрами.
     *
     * @param filterName название фильтра.
     * @param nameValue  значение для поля "Enter name".
     */
    public void addFilter(String filterName, String nameValue) {
        wait.until(ExpectedConditions.elementToBeClickable(addFilterButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterNameInput)).sendKeys(filterName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterNameInput)).sendKeys(nameValue);
        wait.until(ExpectedConditions.elementToBeClickable(submitFilterButton)).click();
    }

    /**
     * Нажимает кнопку "Add" для подтверждения добавления виджета.
     * Использует JavaScript для клика
     */
    public void clickAddButton() {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(addButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

    /**
     * Проверяет, появился ли всплывающий попап об успешном добавлении виджета.
     *
     * @return {@code true}, если попап появился, иначе {@code false}.
     */
    public boolean isWidgetAddedSuccessfully() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successPopup));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
