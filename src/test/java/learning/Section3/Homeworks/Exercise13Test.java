package learning.Section3.Homeworks;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * III. Написание тестов
 * <p>
 * Ex13: User Agent
 * <p>
 * User Agent - это один из заголовков, позволяющий серверу узнавать, с какого девайса и браузера пришел запрос.
 * Он формируется автоматически клиентом, например браузером. Определив, с какого девайса или браузера пришел к
 * нам пользователь мы сможем отдать ему только тот контент, который ему нужен.
 * <p>
 * Наш разработчик написал метод:
 * <a href="https://playground.learnqa.ru/ajax/api/user_agent_check">https://playground.learnqa.ru/ajax/api/user_agent_check</a>
 * <p>
 * Метод определяет по строке заголовка User Agent следующие параметры:
 * <p>
 * - device - iOS или Android
 * <p>
 * - browser - Chrome, Firefox или другой браузер
 * <p>
 * - platform - мобильное приложение или веб
 * <p>
 * Если метод не может определить какой-то из параметров, он выставляет значение Unknown.
 * <p>
 * Наша задача написать параметризованный тест. Этот тест должен брать из дата-провайдера User Agent и ожидаемые
 * значения, GET-делать запрос с этим User Agent и убеждаться, что результат работы нашего метода правильный -
 * т.е. в ответе ожидаемое значение всех трех полей.
 * <p>
 * Список User Agent и ожидаемых значений можно найти по этой ссылке:
 * <a href="https://gist.github.com/KotovVitaliy/138894aa5b6fa442163561b5db6e2e26">https://gist.github.com/KotovVitaliy/138894aa5b6fa442163561b5db6e2e26</a>
 * <p>
 * На самом деле метод не всегда работает правильно. Ответом к задаче должен быть список из тех User Agent,
 * которые вернули неправильным хотя бы один параметр, с указанием того, какой именно параметр неправильный.
 * <p>
 * И, конечно, ссылка на коммит с вашим тестом.
 */
public class Exercise13Test {

    @ParameterizedTest
    @CsvSource(value = {
            "'Mozilla/5.0 (Linux; U; Android 4.0.2; en-us; Galaxy Nexus Build/ICL53F) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30', Mobile, No, Android",
            "'Mozilla/5.0 (iPad; CPU OS 13_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/91.0.4472.77 Mobile/15E148 Safari/604.1', Mobile, Chrome, iOS",
            "'Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)', Googlebot, Unknown, Unknown",
            "'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36 Edg/91.0.100.0', Web, Chrome, No",
            "'Mozilla/5.0 (iPad; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1', Mobile, No, iPhone"
    })

    public void testUserAgentCheckRequest(String userAgent, String expectedPlatform, String expectedBrowser, String expectedDevice) {

        JsonPath response = RestAssured
                .given()
                .header("user-agent", userAgent)
                .get("https://playground.learnqa.ru/ajax/api/user_agent_check")
                .jsonPath();

//        response.prettyPrint();

        String actualPlatform = response.get("platform");
        String actualBrowser = response.get("browser");
        String actualDevice = response.get("device");

//        assertEquals(expectedPlatform, actualPlatform, String.format("Unexpected 'platform' value\nUser Agent:\n\t%s%n", userAgent));
//        assertEquals(expectedBrowser, actualBrowser, String.format("Unexpected 'browser' value\nUser Agent:\n\t%s%n", userAgent));
//        assertEquals(expectedDevice, actualDevice, String.format("Unexpected 'device' value\nUser Agent:\n\t%s%n", userAgent));

        assertTrue(expectedPlatform.equals(actualPlatform) && expectedBrowser.equals(actualBrowser) && expectedDevice.equals(actualDevice),
                String.format("Unexpected response parameters\nUser Agent: \n\t%s" +
                                "\nExpected values:\n\t'platform': '%s', 'browser': '%s', 'device': '%s'" +
                                "\nActual values:\n\t'platform': '%s', 'browser': '%s', 'device': '%s'",
                        userAgent, expectedPlatform, expectedBrowser, expectedDevice, actualPlatform, actualBrowser, actualDevice));
    }
}
