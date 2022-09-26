package learning.Section3.Homeworks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * III. Написание тестов
 * <p>
 * Ex10: Тест на короткую фразу
 * <p>
 * В рамках этой задачи с помощью JUnit необходимо написать тест, который проверяет длину какое-то переменной типа
 * String с помощью любого выбранного Вами метода assert.
 * <p>
 * Если текст длиннее 15 символов, то тест должен проходить успешно. Иначе падать с ошибкой.
 * <p>
 * Результатом должна стать ссылка на такой тест.
 */
public class Exercise10Test {

    @ParameterizedTest
    @ValueSource(strings = {"", "value", "valuevaluevalue", "valuevaluevaluevalue"})
    public void testValueLength(String value) {

        int length = value.length();

        assertTrue(length > 15,
                String.format("\n\tLength of value '%s' is not greater than 15\n\tActual length: %d", value, length));
    }
}
