package learning.Section2.Lessons;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * II. Создание простых запросов к API
 * <p>
 * 08. Заголовки
 */
public class HeadersTest {

    @Test
    public void testGet303RedirectWithHeadersRequest() {
        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .when()
                .get("https://playground.learnqa.ru/api/get_303")
                .andReturn();

        response.prettyPrint();

        String locationHeader = response.getHeader("Location");
        System.out.println(locationHeader);
    }

    @Test
    public void testShowAllHeadersRequest() {

        Map<String, String> headers = new HashMap<>();
        headers.put("MyHeader1", "myValue1");
        headers.put("MyHeader2", "myValue2");

        Response response = RestAssured
                .given()
                .headers(headers)
                .when()
                .get("https://playground.learnqa.ru/api/show_all_headers")
                .andReturn();

        response.prettyPrint();

        Headers responseHeaders = response.getHeaders();
        System.out.println(responseHeaders);
    }
}
