package learning.Section2.Homeworks;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * II. Создание простых запросов к API
 * <p>
 * Ex8*: Токены
 * <p>
 * Иногда API-метод выполняет такую долгую задачу, что за один HTTP-запрос от него нельзя сразу получить
 * готовый ответ. Это может быть подсчет каких-то сложных вычислений или необходимость собрать информацию по разным источникам.
 * <p>
 * В этом случае на первый запрос API начинает выполнения задачи, а на последующие ЛИБО говорит, что задача еще
 * не готова, ЛИБО выдает результат. Сегодня я предлагаю протестировать такой метод.
 * <p>
 * Сам API-метод находится по следующему URL:
 * <a href="https://playground.learnqa.ru/ajax/api/longtime_job">https://playground.learnqa.ru/ajax/api/longtime_job</a>
 * <p>
 * Если мы вызываем его БЕЗ GET-параметра token, метод заводит новую задачу, а в ответ выдает нам JSON со следующими полями:
 * <p>
 * * seconds - количество секунд, через сколько задача будет выполнена
 * <p>
 * * token - тот самый токен, по которому можно получить результат выполнения нашей задачи
 * <p>
 * Если же вызвать API-метод, УКАЗАВ GET-параметром token, то мы получим следующий JSON:
 * <p>
 * * error - будет только в случае, если передать token, для которого не создавалась задача. В этом случае в ответе
 * будет следующая надпись - No job linked to this token
 * <p>
 * * status - если задача еще не готова, будет надпись Job is NOT ready, если же готова - будет надпись Job is ready
 * <p>
 * * result - будет только в случае, если задача готова, это поле будет содержать результат
 * <p>
 * Наша задача - написать тест, который сделал бы следующее:
 * <p>
 * 1) создавал задачу
 * <p>
 * 2) делал один запрос с token ДО того, как задача готова, убеждался в правильности поля status
 * <p>
 * 3) ждал нужное количество секунд с помощью функции time.sleep() - для этого надо сделать import time
 * <p>
 * 4) делал бы один запрос c token ПОСЛЕ того, как задача готова, убеждался в правильности поля status и наличии поля result
 * <p>
 * Как всегда, код нашей программы выкладываем ссылкой на коммит.
 */
public class Exercise8Test {

    public final String NOT_READY = "Job is NOT ready";
    public final String READY = "Job is ready";
    private final String URL = "https://playground.learnqa.ru/ajax/api/longtime_job";

    @Test
    public void testLongtimeJobRequest() throws InterruptedException {

        JsonPath createJobResponse = RestAssured.get(URL).jsonPath();

        createJobResponse.prettyPrint();

        String token = createJobResponse.get("token");
//        System.out.printf("\ttoken: %s%n", token);
        int seconds = createJobResponse.get("seconds");
//        System.out.printf("\tseconds: %s%n", seconds);

        JsonPath createJobWithTokenResponse = response(token);

        createJobWithTokenResponse.prettyPrint();

        String status = getStatus(createJobWithTokenResponse);
//        System.out.printf("\tstatus: %s%n", status);
        String error = createJobWithTokenResponse.get("error");
//        System.out.printf("\terror: %s%n", error);

        switch (status) {
            case NOT_READY:
                System.out.printf("\nWarning! %s, turn on waiting for %d seconds%n", status, seconds);
                Thread.sleep(seconds * 1000L);
                createJobWithTokenResponse = response(token);
                System.out.printf("\nThanks for waiting. %s%n", getStatus(createJobWithTokenResponse));

                String result = createJobWithTokenResponse.get("result");
//                System.out.printf("\tresult: %s%n", result);
                assertNotNull(result, "Error! Result is null");
                break;
            case READY:
                System.out.println(status);
                break;
            default:
                System.err.printf("\nError! %s%n", createJobWithTokenResponse.prettify());
                break;
        }
    }

    private String getStatus(JsonPath response) {
        return response.get("status");
    }

    private JsonPath response(String token) {
        return RestAssured
                .given()
                .queryParam("token", token)
                .get(URL)
                .jsonPath();
    }
}
