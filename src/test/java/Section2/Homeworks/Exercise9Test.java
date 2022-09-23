package Section2.Homeworks;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Exercise9Test {

    final String NOT_AUTHORIZED = "You are NOT authorized";
    List<String> passwords = Arrays.asList("1234567", "123456", "12345", "1234", "12345678", "123456789",
            "1234567890", "password", "qwerty", "abc123", "football", "monkey", "111111", "letmein", "dragon",
            "baseball", "sunshine", "iloveyou", "trustno1", "princess", "adobe123", "123123", "welcome", "login",
            "admin", "qwerty123", "solo", "1q2w3e4r", "666666", "master", "photoshop", "1qaz2wsx", "qwertyuiop",
            "ashley", "mustang", "121212", "starwars", "654321", "bailey", "access", "flower", "555555", "shadow",
            "passw0rd", "lovely", "7777777", "michael", "!@#$%^&*\t", "jesus", "password1", "superman", "hello",
            "charlie", "888888", "696969", "qwertyuiop", "hottie", "freedom", "aa123456", "qazwsx", "ninja",
            "azerty", "loveme", "whatever", "donald", "batman", "zaq1zaq1", "Football", "000000", "123qwe");


    /**
     * II. Создание простых запросов к API
     * <p>
     * Ex9*: Подбор пароля
     * Это задание повышенной сложности. Оно необязательное. Но зато интересное. :)
     * <p>
     * Сегодня к нам пришел наш коллега и сказал, что забыл свой пароль от важного сервиса.
     * Он просит нас помочь ему написать программу, которая подберет его пароль.
     * <p>
     * Условие следующее. Есть метод:
     * <a href="https://playground.learnqa.ru/ajax/api/get_secret_password_homework">https://playground.learnqa.ru/ajax/api/get_secret_password_homework</a>
     * <p>
     * Его необходимо вызывать POST-запросом с двумя параметрами: login и password
     * <p>
     * Если вызвать метод без поля login или указать несуществующий login, метод вернет 500
     * <p>
     * Если login указан и существует, метод вернет нам авторизационную cookie с названием auth_cookie и каким-то значением.
     * <p>
     * У метода существует защита от перебора. Если верно указано поле login, но передан неправильный password,
     * то авторизационная cookie все равно вернется. НО с "неправильным" значением, которое на самом деле не позволит
     * создавать авторизованные запросы. Только если и login, и password указаны верно, вернется cookie с "правильным"
     * значением. Таким образом используя только метод get_secret_password_homework невозможно узнать,
     * передали ли мы верный пароль или нет.
     * <p>
     * По этой причине нам потребуется второй метод, который проверяет правильность нашей авторизованной cookie:
     * <a href="https://playground.learnqa.ru/ajax/api/check_auth_cookie">https://playground.learnqa.ru/ajax/api/check_auth_cookie</a>
     * <p>
     * Если вызвать его без cookie с именем auth_cookie или с cookie, у которой выставлено "неправильное" значение,
     * метод вернет фразу "You are NOT authorized".
     * <p>
     * Если значение cookie “правильное”, метод вернет: “You are authorized”
     * <p>
     * Коллега говорит, что точно помнит свой login - это значение super_admin
     * <p>
     * А вот пароль забыл, но точно помнит, что выбрал его из списка самых популярных паролей на Википедии (вот тебе и супер админ...).
     * <p>
     * Ссылка: <a href="https://en.wikipedia.org/wiki/List_of_the_most_common_passwords">https://en.wikipedia.org/wiki/List_of_the_most_common_passwords</a>
     * <p>
     * Искать его нужно среди списка Top 25 most common passwords by year according to SplashData -
     * список паролей можно скопировать в ваш тест вручную или придумать более хитрый способ, если сможете.
     * <p>
     * Итак, наша задача - написать тест и указать в нем login нашего коллеги и все пароли из Википедии в виде списка.
     * Программа должна делать следующее:
     * <p>
     * 1. Брать очередной пароль и вместе с логином коллеги вызывать первый метод get_secret_password_homework.
     * В ответ метод будет возвращать авторизационную cookie с именем auth_cookie и каким-то значением.
     * <p>
     * 2. Далее эту cookie мы должна передать во второй метод check_auth_cookie. Если в ответ вернулась фраза
     * "You are NOT authorized", значит пароль неправильный. В этом случае берем следующий пароль и все заново.
     * Если же вернулась другая фраза - нужно, чтобы программа вывела верный пароль и эту фразу.
     * <p>
     * Ответом к задаче должен быть верный пароль и ссылка на коммит со скриптом.
     */
    @Test
    public void testGetSecretPasswordRequest() {

        for (String password : passwords) {
            Map<String, String> params = new HashMap<>();
            params.put("login", "super_admin");
            params.put("password", password);
            Response getSecretPasswordResponse = RestAssured
                    .given()
                    .body(params)
                    .post("https://playground.learnqa.ru/ajax/api/get_secret_password_homework")
                    .andReturn();
            String responseCookie = getSecretPasswordResponse.getCookie("auth_cookie");

            Map<String, String> cookies = new HashMap<>();
            if (responseCookie != null) {
                cookies.put("auth_cookie", responseCookie);
            }

            Response checkAuthCookieResponse = RestAssured
                    .given()
                    .body(params)
                    .cookies(cookies)
                    .post("https://playground.learnqa.ru/ajax/api/check_auth_cookie")
                    .andReturn();
            String answer = checkAuthCookieResponse.asString();

//            System.out.printf("Password: %s%n", password);
//            checkAuthCookieResponse.print();
//            System.out.println();

            if (!answer.equals(NOT_AUTHORIZED)) {
                System.out.printf("Password: %s%n", password);
                checkAuthCookieResponse.print();
                break;
            }
        }
    }
}
