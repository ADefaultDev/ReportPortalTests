package ui.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Утилитный класс для загрузки и доступа к настройкам приложения
 * из файла {@code config.properties}, расположенного в classpath.
 * <p>
 * Файл конфигурации загружается один раз при инициализации класса.
 * Доступ к свойствам осуществляется через статический метод {@link #get(String)}.
 *
 * <p>Пример использования:</p>
 * <pre>{@code
 * String baseUrl = Config.get("base.url");
 * }</pre>
 *
 * @author ADefaultDev
 * @since 1.0
 */
public class Config {
    private static final Properties props = new Properties();

    /**
     * Статический инициализатор, который загружает конфигурацию
     * из файла {@code config.properties} при первом обращении к классу.
     *
     * @throws RuntimeException при неудачной попытке загрузить файл.
     */
    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Cannot find config.properties");
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    /**
     * Возвращает значение свойства по указанному ключу.
     *
     * @param key ключ свойства.
     * @return значение свойства, или {@code null}, если ключ отсутствует.
     */
    public static String get(String key) {
        return props.getProperty(key);
    }
}
