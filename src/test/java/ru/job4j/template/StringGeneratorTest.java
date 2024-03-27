package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
public class StringGeneratorTest {

    @Test
    public void whenGeneratorWorks() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of(
                "name", "John",
                "subject", "you");
        StringGenerator stringGenerator = new StringGenerator();
        String result = stringGenerator.produce(template, args);
        assertThat(result).isEqualTo("I am John, Who are you?");
    }

    @Test
    public void whenMoreKeysInMapThenThrowsException() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of(
                "name", "John",
                "subject", "you",
                "surname", "Smith"
        );
        StringGenerator stringGenerator = new StringGenerator();
        assertThatThrownBy(() -> stringGenerator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenWrongKeysInTemplateThenThrowsException() {
        String template = "My surname is ${surname}, Who are ${subject}?";
        Map<String, String> args = Map.of(
                "name", "John",
                "subject", "you"
        );
        StringGenerator stringGenerator = new StringGenerator();
        assertThatThrownBy(() -> stringGenerator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
