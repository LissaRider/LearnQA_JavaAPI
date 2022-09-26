package Section2.Lessons;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * II. Создание простых запросов к API
 * <p>
 * 06. Проверка типа запроса и параметров
 */
public class CheckTypeTest {

    @Test
    public void testCheckTypeGetRequest() {

        Response response = RestAssured
                .given()
                .queryParam("param1", "value1")
                .queryParam("param2", "value2")
                .get("https://playground.learnqa.ru/api/check_type")
                .andReturn();

        response.print();
    }

    @Test
    public void testCheckTypePostRequest() {

        Map<String, Object> body = new HashMap<>();
        body.put("param1", "value1");
        body.put("param2", "value2");

        Response response = RestAssured
                .given()
//                .body("param1=value1&param2=value2")
//                .body("{\"param1\":\"value1\",\"&param2\":\"value2\"}") // строка в json формате
                .body(body)
                .post("https://playground.learnqa.ru/api/check_type")
                .andReturn();

        response.print();
    }

    @Test
    public void testCheckTypeStatusRequest() {

        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/check_type")
                .andReturn();

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
    }
}
