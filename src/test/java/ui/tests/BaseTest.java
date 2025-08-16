package ui.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.utils.Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

/**
 * Базовый абстрактный класс для UI-тестов.
 * <p>
 * Отвечает за:
 * <ul>
 *     <li>Загрузку конфигурации из файла {@code config.properties}.</li>
 *     <li>Инициализацию и настройку экземпляра {@link WebDriver}.</li>
 *     <li>Завершение работы драйвера после выполнения теста.</li>
 * </ul>
 *
 * @author ADefaultDev
 * @since 1.0
 */
public abstract class BaseTest {

    protected WebDriver driver;

    protected Config config;

    /**
     * Выполняется перед каждым тестом.
     * <ul>
     *     <li>Загружает файл конфигурации {@code config.properties}.</li>
     *     <li>Создаёт новый экземпляр {@link ChromeDriver}.</li>
     *     <li>Устанавливает неявное ожидание поиска элементов (5 секунд).</li>
     *     <li>Разворачивает окно браузера на весь экран.</li>
     * </ul>
     *
     * @throws IOException если не удалось загрузить файл конфигурации.
     */
    @BeforeEach
    public void setUp() throws IOException {
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    /**
     * Выполняется после каждого теста.
     * Закрывает браузер и завершает сессию {@link WebDriver}.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Возвращает текущий экземпляр {@link WebDriver}.
     *
     * @return активный {@link WebDriver}.
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Возвращает загруженные конфигурационные свойства.
     *
     * @return объект {@link Properties}, содержащий настройки теста.
     */
    public Config getConfig() {
        return config;
    }
}
