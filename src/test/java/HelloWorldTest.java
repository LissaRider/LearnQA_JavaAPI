import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HelloWorldTest {

    /**
     * I.Настройка окружения и hello world
     * <p>
     * 09. Создание проекта и Hello world
     */
    @Test
    public void testHelloWorld() {
        System.out.println("Hello world");
    }

    /**
     * I. Настройка окружения и hello world
     * <p>
     * 10. Первый API тест
     */
    @Test
    public void testHelloRequest() {
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/hello")
                .andReturn();
        response.prettyPrint();
    }

    /**
     * I. Настройка окружения и hello world
     * <p>
     * Ex4: GET-запрос
     */
    @Test
    public void testGetTextRequest() {
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/get_text")
                .andReturn();
//        response.prettyPrint();
        System.out.println(response.asString());
    }

    /**
     * II. Создание простых запросов к API
     * <p>
     * 04. Параметры запроса
     */
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

    /**
     * II. Создание простых запросов к API
     * <p>
     * 05. Парсинг JSON
     */
    @Test
    public void testRestAssuredJsonParsing() {
        Map<String, String> params = new HashMap<>();
        params.put("name", "John");
        JsonPath response = RestAssured
                .given()
                .queryParams(params)
                .get("https://playground.learnqa.ru/api/hello")
                .jsonPath();

        String name = response.get("answer2");
        if (name == null) {
            System.out.println("The key 'answer2' is absent");
        } else {
            System.out.println(name);
        }
    }

    /**
     * II. Создание простых запросов к API
     * <p>
     * 06. Проверка типа запроса и параметров
     */
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

    /**
     * II. Создание простых запросов к API
     * <p>
     * 06. Проверка типа запроса и параметров
     */
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
}
