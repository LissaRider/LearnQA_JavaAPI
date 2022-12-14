package learning.Section2.Lessons;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * II. Создание простых запросов к API
 * <p>
 * 04. Параметры запроса
 */
public class QueryParamsTest {

    @Test
    public void testRestAssured() {

        Map<String, String> params = new HashMap<>();
        params.put("name", "John");

        Response response = RestAssured
                .given()
                .queryParams(params)
//                .queryParam("name", "John")
                .get("https://playground.learnqa.ru/api/hello")
                .andReturn();

        response.prettyPrint();
    }
}
