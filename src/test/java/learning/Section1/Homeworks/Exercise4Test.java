package learning.Section1.Homeworks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

/**
 * I. Настройка окружения и hello world
 * <p>
 * Ex4: GET-запрос
 * <p>
 * В проекте нужно создать тест, который отправляет GET-запрос по адресу:
 * <a href="https://playground.learnqa.ru/api/get_text">https://playground.learnqa.ru/api/get_text</a>
 * <p>
 * Затем вывести содержимое текста в ответе на запрос. Когда тест будет готов - давайте его закоммитим в наш репозиторий.
 * <p>
 * Результатом должна быть ссылка на коммит.
 */
public class Exercise4Test {

    @Test
    public void testGetTextRequest() {

        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/get_text")
                .andReturn();

//        response.prettyPrint();
        response.print();
//        System.out.println(response.asString());
    }
}
