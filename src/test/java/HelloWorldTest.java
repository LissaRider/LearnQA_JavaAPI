import io.restassured.RestAssured;
import io.restassured.http.Headers;
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

    /**
     * II. Создание простых запросов к API
     * <p>
     * 06. Проверка типа запроса и параметров
     */
    @Test
    public void testCheckTypeStatusRequest() {
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/check_type")
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

    /**
     * II. Создание простых запросов к API
     * <p>
     * 08. Заголовки
     */
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

    /**
     * II. Создание простых запросов к API
     * <p>
     * 08. Заголовки
     */
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


    /**
     * II. Создание простых запросов к API
     * <p>
     * 09. Cookie
     */
    @Test
    public void testGetAuthCookiesRequest() {
        Map<String, String> data = new HashMap<>();
        data.put("login", "secret_login");
        data.put("password", "secret_pass");

        Response response = RestAssured
                .given()
                .body(data)
                .when()
                .post("https://playground.learnqa.ru/api/get_auth_cookie")
                .andReturn();
        System.out.println("\nPretty text:");
        response.prettyPrint();
        System.out.println("\nHeaders:");
        Headers responseHeaders = response.getHeaders();
        System.out.println(responseHeaders);
        System.out.println("\nCookies:");
        Map<String, String> responseCookies = response.getCookies();
        System.out.println(responseCookies);
        String responseCookie = response.getCookie("auth_cookie");
        System.out.println(responseCookie);
    }

    /**
     * II. Создание простых запросов к API
     * <p>
     * 09. Cookie
     */
    @Test
    public void testCheckAuthCookiesRequest() {
        Map<String, String> data = new HashMap<>();
        data.put("login", "secret_login2");
        data.put("password", "secret_pass2");

        Response responseForGet = RestAssured
                .given()
                .body(data)
                .when()
                .post("https://playground.learnqa.ru/api/get_auth_cookie")
                .andReturn();
        String responseCookie = responseForGet.getCookie("auth_cookie");

        Map<String, String> cookies = new HashMap<>();
        if (responseCookie != null) {
            cookies.put("auth_cookie", responseCookie);
        }
        Response responseForCheck = RestAssured
                .given()
                .body(data)
                .cookies(cookies)
                .when()
                .post("https://playground.learnqa.ru/api/check_auth_cookie")
                .andReturn();
        responseForCheck.print();
    }
}
