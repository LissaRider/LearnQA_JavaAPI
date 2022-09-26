package learning.Section3.Lessons;

import io.restassured.RestAssured;
//import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserAuthTest {

    String cookie;
    String header;
    int userIdOnAuth;

    /**
     * III. Написание тестов
     * <p>
     * 05. Функция BeforeEach() в JUnit
     */
    @BeforeEach
    public void loginUser() {
        Map<String, String> authData = new HashMap<>();
        authData.put("email", "vinkotov@example.com");
        authData.put("password", "1234");

        Response responseGetAuth = RestAssured
                .given()
                .body(authData)
                .post("https://playground.learnqa.ru/api/user/login")
                .andReturn();

        this.cookie = responseGetAuth.getCookie("auth_sid");
        this.header = responseGetAuth.getHeader("x-csrf-token");
        this.userIdOnAuth = responseGetAuth.jsonPath().getInt("user_id");
    }

    /**
     * III. Написание тестов
     * <p>
     * 03. Позитивный тест на авторизацию
     */
    @Test
    public void testAuthUser() {

//        Map<String, String> authData = new HashMap<>();
//        authData.put("email", "vinkotov@example.com");
//        authData.put("password", "1234");
//
//        Response responseGetAuth = RestAssured
//                .given()
//                .body(authData)
//                .post("https://playground.learnqa.ru/api/user/login")
//                .andReturn();
//
//        Map<String, String> cookies = responseGetAuth.getCookies();
//        Headers headers = responseGetAuth.getHeaders();
//        int userIdOnAuth = responseGetAuth.jsonPath().getInt("user_id");
//
//        assertEquals(200, responseGetAuth.getStatusCode(), "Unexpected status code");
//        assertTrue(cookies.containsKey("auth_sid"), "Response doesn't have 'auth_sid' cookie");
//        assertTrue(headers.hasHeaderWithName("x-csrf-token"), "Response doesn't have 'x-csrf-token' header");
//        assertTrue(responseGetAuth.jsonPath().getInt("user_id") > 0, "user_id should be greater than 0");

        JsonPath responseCheckAuth = RestAssured
                .given()
//                .header("x-csrf-token", responseGetAuth.getHeader("x-csrf-token"))
//                .cookie("auth_sid", responseGetAuth.getCookie("auth_sid"))
                .header("x-csrf-token", this.header)
                .cookie("auth_sid", this.cookie)
                .get("https://playground.learnqa.ru/api/user/auth")
                .jsonPath();

        int userIdOnCheck = responseCheckAuth.getInt("user_id");
        assertTrue(userIdOnCheck > 0, String.format("Unexpected user_id %d", userIdOnCheck));

        assertEquals(userIdOnAuth, userIdOnCheck, "user_id from auth request is not equal to user_id from check request");
    }

    /**
     * III. Написание тестов
     * <p>
     * 04. Негативные тесты на авторизацию
     */
    @ParameterizedTest
    @ValueSource(strings = {"cookie", "headers"})
    public void testNegativeAuthUser(String condition) {

//        Map<String, String> authData = new HashMap<>();
//        authData.put("email", "vinkotov@example.com");
//        authData.put("password", "1234");
//
//        Response responseGetAuth = RestAssured
//                .given()
//                .body(authData)
//                .post("https://playground.learnqa.ru/api/user/login")
//                .andReturn();
//
//        Map<String, String> cookies = responseGetAuth.getCookies();
//        Headers headers = responseGetAuth.getHeaders();

        RequestSpecification spec = RestAssured.given();
        spec.baseUri("https://playground.learnqa.ru/api/user/auth");

        switch (condition) {
            case "cookie":
//                spec.cookie("auth_sid", cookies.get("auth_sid"));
                spec.cookie("auth_sid", this.cookie);
                break;
            case "headers":
//                spec.header("x-csrf-token", headers.get("x-csrf-token"));
                spec.header("x-csrf-token", this.header);
                break;
            default:
                throw new IllegalArgumentException(String.format("Condition value is unknown: %s", condition));
        }

        JsonPath responseForCheck = spec.get().jsonPath();

        assertEquals(0, responseForCheck.getInt("user_id"), "user_id should be 0 for unauth request");
    }
}
