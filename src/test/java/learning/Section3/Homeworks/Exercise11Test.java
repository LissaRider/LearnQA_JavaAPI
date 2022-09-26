package learning.Section3.Homeworks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * III. Написание тестов
 * <p>
 * Ex11: Тест запроса на метод cookie
 * <p>
 * Необходимо написать тест, который делает запрос на метод:
 * <a href="https://playground.learnqa.ru/api/homework_cookie">https://playground.learnqa.ru/api/homework_cookie</a>
 * <p>
 * Этот метод возвращает какую-то cookie с каким-то значением. Необходимо понять что за cookie и с каким значением,
 * и зафиксировать это поведение с помощью assert.
 * <p>
 * Результатом должна быть ссылка на коммит с тестом.
 */
public class Exercise11Test {

    @Test
    public void testCookieRequest() {

        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/homework_cookie")
                .andReturn();

        Map<String, String> responseCookies = response.getCookies();
        System.out.println(responseCookies);
        String responseCookie = response.getCookie("HomeWork");

        assertEquals("hw_value", responseCookie, "Unexpected cookie value");
    }
}
