package Section3.Lessons;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * III. Написание тестов
 * <p>
 * 02. Параметризованный тест
 */
public class ParametrizedTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "John", "Peter"})
    public void testHelloMethodWithoutName(String name) {

        Map<String, String> queryParams = new HashMap<>();
        if (name.length() > 0) queryParams.put("name", name);

        JsonPath response = RestAssured
                .given()
                .queryParams(queryParams)
                .get("https://playground.learnqa.ru/api/hello")
                .jsonPath();

        String answer = response.getString("answer");
        String expectedName = (name.length() > 0) ? name : "someone";
        assertEquals(String.format("Hello, %s", expectedName), answer, "The answer is not expected");
    }

//    @Test
//    public void testHelloMethodWithName() {
//
//        String name = "Username";
//        JsonPath response = RestAssured
//                .given()
//                .queryParam("name", name)
//                .get("https://playground.learnqa.ru/api/hello")
//                .jsonPath();
//
//        String answer = response.getString("answer");
//        assertEquals(String.format("Hello, %s", name), answer, "The answer is not expected");
//    }

}
