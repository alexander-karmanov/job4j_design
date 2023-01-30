package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("Audi", "Bentley", "Cadillac", "Jaguar", "Volvo");
        assertThat(list).hasSize(5)
                .contains("Jaguar")
                .contains("Bentley", Index.atIndex(1))
                .containsSequence("Cadillac", "Jaguar")
                .startsWith("Audi");
        assertThat(list).first().isEqualTo("Audi");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set =  simpleConvert.toSet("cube", "cylinder", "pyramid", "sphere");
        assertThat(set).hasSize(4)
                .containsOnly("pyramid", "cylinder", "sphere", "cube")
                .doesNotContain("cone")
                .containsAnyOf("cylinder", "pyramid");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("chair", "sofa", "table", "wardrobe");
        assertThat(map).hasSize(4)
                .containsKeys("chair", "sofa", "table")
                .containsValues(3, 0, 2, 1)
                .doesNotContainKey("lamp")
                .doesNotContainValue(8)
                .containsEntry("wardrobe", 3);
    }
}
