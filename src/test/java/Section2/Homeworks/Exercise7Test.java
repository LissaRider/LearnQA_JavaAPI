package Section2.Homeworks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * II. Создание простых запросов к API
 * <p>
 * Ex7:* Долгий редирект
 * <p>
 * Необходимо написать тест, который создает GET-запрос на адрес из предыдущего задания:
 * <a href="https://playground.learnqa.ru/api/long_redirect">https://playground.learnqa.ru/api/long_redirect</a>
 * <p>
 * На самом деле этот URL ведет на другой, который мы должны были узнать на предыдущем занятии.
 * Но этот другой URL тоже куда-то редиректит. И так далее. Мы не знаем заранее количество всех редиректов и итоговый адрес.
 * <p>
 * Наша задача — написать цикл, которая будет создавать запросы в цикле, каждый раз читая URL для редиректа из
 * нужного заголовка. И так, пока мы не дойдем до ответа с кодом 200.
 * <p>
 * Ответом должна быть ссылка на тест в вашем репозитории и количество редиректов.
 */
public class Exercise7Test {

    @Test
    public void testLongRedirectLoopRequest() {

        List<String> locationList = new ArrayList<>();
        String queryUrl = "https://playground.learnqa.ru/api/long_redirect";
        int statusCode = 301;
        while (statusCode != 200) {
            Response response = RestAssured
                    .given()
                    .redirects()
                    .follow(false)
                    .when()
                    .get(queryUrl)
                    .andReturn();

            queryUrl = response.getHeader("Location");
            statusCode = response.getStatusCode();
            if (queryUrl != null) {
                locationList.add(queryUrl);
            }
        }

        int size = locationList.size();
        System.out.printf("The last response received with code 200, number of redirects: %d%n", size);
        System.out.println(locationList);
    }
}
