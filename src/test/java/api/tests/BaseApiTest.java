package api.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.util.Properties;

/**
 * Базовый абстрактный класс для API-тестов.
 * <p>
 * Отвечает за инициализацию конфигурации тестов:
 * <ul>
 *     <li>Загружает настройки из файла {@code config.properties}</li>
 *     <li>Устанавливает базовый URI для RestAssured</li>
 * </ul>
 * Наследники этого класса могут использовать загруженные параметры
 * через переменную {@link #config}.
 *
 * @author ADefaultDev
 * @since 1.0
 */
public abstract class BaseApiTest {

    /**
     * Конфигурационные параметры тестов,
     * загруженные из файла {@code config.properties}.
     */
    protected static Properties config;

    /**
     * Метод выполняется один раз перед запуском всех тестов в классе-наследнике.
     * <p>
     * Загружает конфигурацию из файла {@code config.properties} и
     * устанавливает базовый URI для RestAssured на основе параметра
     * {@code base.api.url}.
     *
     * @throws RuntimeException если файл конфигурации не найден
     *                          или произошла ошибка при загрузке.
     */
    @BeforeAll
    static void setUp() {
        try {
            config = new Properties();
            config.load(BaseApiTest.class.getClassLoader().getResourceAsStream("config.properties"));

            RestAssured.baseURI = config.getProperty("base.api.url");

        } catch (Exception e) {
            throw new RuntimeException("Не удалось загрузить config.properties", e);
        }
    }
}
