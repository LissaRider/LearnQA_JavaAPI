package learning.Section2.Homeworks;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

/**
 * II. Создание простых запросов к API
 * <p>
 * Ex5: Парсинг JSON
 * <p>
 * В рамках этой задачи нужно создать тест, который будет делать GET-запрос на адрес
 * <a href="https://playground.learnqa.ru/api/get_json_homework">https://playground.learnqa.ru/api/get_json_homework</a>
 * <p>
 * Полученный JSON необходимо распечатать и изучить. Мы увидим, что это данные с сообщениями и временем,
 * когда они были написаны. Наша задача вывести текст второго сообщения.
 * <p>
 * Ответом должна быть ссылка на тест в вашем репозитории.
 */
public class Exercise5Test {

    @Test
    public void testGetJsonHomeworkRequest() {

        JsonPath response = RestAssured
                .get("https://playground.learnqa.ru/api/get_json_homework")
                .jsonPath();

        System.out.println("\nПолученный в ответе JSON:");
        response.prettyPrint();

        System.out.println("\nТекст второго сообщения:");
        String message2 = response.get("messages.message[1]");
        System.out.printf("\t%s%n", message2);
    }
}
