package learning.Section3.Homeworks;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * III. Написание тестов
 * <p>
 * Ex12: Тест запроса на метод header
 * <p>
 * Необходимо написать тест, который делает запрос на метод:
 * <a href="https://playground.learnqa.ru/api/homework_header">https://playground.learnqa.ru/api/homework_header</a>
 * <p>
 * Этот метод возвращает headers с каким-то значением. Необходимо понять что за headers и с каким значением,
 * и зафиксировать это поведение с помощью assert
 * <p>
 * Результатом должна быть ссылка на коммит с тестом.
 */
public class Exercise12Test {

    @Test
    public void testHeaderRequest() {

        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/homework_header")
                .andReturn();

        Headers responseHeaders = response.getHeaders();
        System.out.println(responseHeaders);
        String responseHeader = response.getHeader("x-secret-homework-header");

        assertEquals("Some secret value", responseHeader, "Unexpected header value");
    }
}
