import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class HelloWorldTest {

    /**
     * I.Настройка окружения и hello world. 09.
     * <p>
     * Создание проекта и Hello worldГиперссылка
     */
    @Test
    public void testHelloWorld() {
        System.out.println("Hello world");
    }

    /**
     * I. Настройка окружения и hello world.
     * <p>
     * 10. Первый API тестГиперссылка
     */
    @Test
    public void testHelloRequest() {
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/hello")
                .andReturn();
        response.prettyPrint();
    }

    /**
     * I. Настройка окружения и hello world.
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
}
