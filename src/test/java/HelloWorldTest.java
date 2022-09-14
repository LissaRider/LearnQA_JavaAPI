import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class HelloWorldTest {

    @Test
    public void testHelloWorld() {
//        I.Настройка окружения и hello world. 09. Создание проекта и Hello worldГиперссылка
//        System.out.println("Hello world");

//        I. Настройка окружения и hello world. 10. Первый API тестГиперссылка
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/hello")
                .andReturn();
        response.prettyPrint();
    }
}
