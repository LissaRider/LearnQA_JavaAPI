package learning.Section3.Lessons;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * III. Написание тестов
 * <p>
 * 01. JUnit и простые тесты
 */
public class JUnitTest {

//    @Test
//    public void testPassedMapRequest() {
//
//        Response response = RestAssured
//                .get("https://playground.learnqa.ru/api/map")
//                .andReturn();
////        assertTrue(response.getStatusCode() == 200, "Unexpected status code");
//        assertEquals(200, response.getStatusCode(), "Unexpected status code");
//    }
//
//    @Test
//    public void testFailedMapRequest() {
//
//        Response response = RestAssured
//                .get("https://playground.learnqa.ru/api/map2")
//                .andReturn();
//        assertEquals(200, response.getStatusCode(), "Unexpected status code");
//    }

    @Test
    public void testFor200MapRequest() {

        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/map")
                .andReturn();
        assertEquals(200, response.getStatusCode(), "Unexpected status code");
    }

    @Test
    public void testFor404MapRequest() {

        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/map2")
                .andReturn();
        assertEquals(404, response.getStatusCode(), "Unexpected status code");
    }
}
