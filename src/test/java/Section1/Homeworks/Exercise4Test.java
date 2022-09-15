package Section1.Homeworks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Exercise4Test {

    /**
     * I. Настройка окружения и hello world
     * <p>
     * Ex4: GET-запрос
     * <p>
     * В проекте нужно создать тест, который отправляет GET-запрос по адресу: https://playground.learnqa.ru/api/get_text
     * <p>
     * Затем вывести содержимое текста в ответе на запрос. Когда тест будет готов - давайте его закоммитим в наш репозиторий.
     * <p>
     * Результатом должна быть ссылка на коммит.
     */
    @Test
    public void testGetTextRequest() {

        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/get_text")
                .andReturn();

//        response.prettyPrint();
        System.out.println(response.asString());
    }
}
