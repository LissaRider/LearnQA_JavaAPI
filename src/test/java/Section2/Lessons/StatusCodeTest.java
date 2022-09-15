package Section2.Lessons;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class StatusCodeTest {

    /**
     * II. Создание простых запросов к API
     * <p>
     * 07. Коды ответа сервера
     */
    @Test
    public void testGet500StatusRequest() {

        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/get_500")
                .andReturn();

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
    }

    /**
     * II. Создание простых запросов к API
     * <p>
     * 07. Коды ответа сервера
     */
    @Test
    public void testGet404StatusRequest() {

        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/something")
                .andReturn();

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
    }

    /**
     * II. Создание простых запросов к API
     * <p>
     * 07. Коды ответа сервера
     */
    @Test
    public void testGet303StatusRedirectRequest() {

        Response response = RestAssured
                .given()
                .redirects()
//                .follow(false)
                .follow(true)
                .when()
                .get("https://playground.learnqa.ru/api/get_303")
                .andReturn();

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
    }
}
