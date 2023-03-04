package ru.job4j.set;

import org.junit.jupiter.api.Test;
import ru.job4j.collection.SimpleArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleSetTest {

    @Test
    void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void checkDuplicatesAndSize() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(5)).isTrue();
        assertThat(set.contains(5)).isTrue();
        assertThat(set.add(5)).isFalse();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
        assertThat(set.add(3)).isTrue();
        assertThat(set.contains(3)).isTrue();
        assertThat(set).containsSequence(5, null, 3);
        assertThat(set).hasSize(3);
    }
}
