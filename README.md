# ReportPortalTests

Автоматизированные тесты для проверки функционала **ReportPortal** (API
и UI).

##  Структура проекта

    test
     └── java
         ├── api
         │   ├── steps           # Шаги для API-тестов
         │   └── tests           # API-тесты (создание дашборда и др.)
         └── ui
             ├── pages           # Page Object классы (LoginPage, DashboardPage)
             ├── steps           # Шаги для UI-тестов
             ├── tests           # UI-тесты (логин, создание виджетов)
             └── utils           # Утилиты (Config)
    resources
     └── config.properties       # Конфигурация проекта

##  Технологии

-   **Java 17+**
-   **JUnit 5**
-   **Selenium** 
-   **RestAssured** 
-   **Gradle** 
-   **Allure**

## ⚙️ Конфигурация

Файл `resources/config.properties` содержит основные параметры
окружения, например:

``` properties
base.url=https://demo.reportportal.io/ui/#login
base.api.url=https://demo.reportportal.io/api
username=default
password=1q2w3e
project.name=default_personal
api.token требуется создать самостоятельно, поместить в config.properties

```

##  Запуск тестов

### Запуск всех тестов

``` bash
mvn clean test
```

### Запуск только API-тестов

``` bash
mvn -Dtest=api.tests.* test
```

### Запуск только UI-тестов

``` bash
mvn -Dtest=ui.tests.* test
```

## Отчеты

После выполнения тестов формируется отчет:

-   **Allure**:

    ``` bash
    allure serve target/allure-results
    ```

-   **ReportPortal**: результаты отправляются автоматически (если
    настроено подключение).

------------------------------------------------------------------------
