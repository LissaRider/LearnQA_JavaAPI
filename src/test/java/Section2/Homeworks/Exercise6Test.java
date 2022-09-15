package Section2.Homeworks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Exercise6Test {

    /**
     * II. Создание простых запросов к API
     * <p>
     * Ex6:* Редирект
     * <p>
     * Необходимо написать тест, который создает GET-запрос на адрес:
     * <a href="https://playground.learnqa.ru/api/long_redirect">https://playground.learnqa.ru/api/long_redirect</a>
     * <p>
     * С этого адреса должен происходит редирект на другой адрес. Наша задача — распечатать адрес, на который редиректит указанные URL.
     * <p>
     * Ответом должна быть ссылка на тест в вашем репозитории.
     */
    @Test
    public void testLongRedirectRequest() {

        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .get("https://playground.learnqa.ru/api/long_redirect")
                .andReturn();

        String responseHeader = response.getHeader("location");
        System.out.println(responseHeader);
    }
}
