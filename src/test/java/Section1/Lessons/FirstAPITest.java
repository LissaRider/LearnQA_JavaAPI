package Section1.Lessons;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

/**
 * I. Настройка окружения и hello world
 * <p>
 * 10. Первый API тест
 */
public class FirstAPITest {

    @Test
    public void testHelloRequest() {

        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/hello")
                .andReturn();

        response.prettyPrint();
    }
}
