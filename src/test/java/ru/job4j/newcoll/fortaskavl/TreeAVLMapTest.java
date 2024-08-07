package ru.job4j.newcoll.fortaskavl;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class TreeAVLMapTest {

    @Test
    void whenEmptyTree() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertThat(tree.values()).isEmpty();
        assertThat(tree.keySet()).isEmpty();
    }

    @Test
    void whenAddOneElemThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertThat(tree.insert(1, "11")).isTrue();
        assertThat(tree.values()).hasSize(1)
                .containsExactly("11");
        assertThat(tree.keySet()).hasSize(1)
                .containsExactly(1);
    }

    @Test
    void whenAddSevenElemThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertThat(tree.insert(1, "11")).isTrue();
        assertThat(tree.insert(2, "22")).isTrue();
        assertThat(tree.insert(3, "33")).isTrue();
        assertThat(tree.insert(4, "44")).isTrue();
        assertThat(tree.insert(5, "55")).isTrue();
        assertThat(tree.insert(6, "66")).isTrue();
        assertThat(tree.insert(7, "77")).isTrue();
        assertThat(tree.values()).hasSize(7)
                .containsExactly("11", "22", "33", "44", "55", "66", "77");
        assertThat(tree.keySet()).hasSize(7)
                .containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    public void whenAddNullValue() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        assertThat(tree.insert(1, null)).isFalse();
        assertThat(tree.values().isEmpty()).isTrue();
        assertThat(tree.keySet().isEmpty()).isTrue();
    }

    @Test
    void whenUpdateValueThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.insert(1, "11");
        tree.insert(2, "22");
        tree.insert(3, "33");
        tree.insert(4, "44");
        tree.insert(5, "55");
        tree.insert(6, "66");
        tree.insert(7, "77");
        assertThat(tree.insert(5, "555")).isTrue();
        assertThat(tree.values()).hasSize(7)
                .containsExactly("11", "22", "33", "44", "555", "66", "77");
        assertThat(tree.keySet()).hasSize(7)
                .containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenDeleteKeyThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.insert(1, "11");
        tree.insert(2, "22");
        tree.insert(3, "33");
        tree.insert(4, "44");
        tree.insert(5, "55");
        tree.insert(6, "66");
        tree.insert(7, "77");
        assertThat(tree.remove(5)).isTrue();
        assertThat(tree.remove(0)).isFalse();
        assertThat(tree.values()).hasSize(6)
                .containsExactly("11", "22", "33", "44", "66", "77");
        assertThat(tree.keySet()).hasSize(6)
                .containsExactly(1, 2, 3, 4, 6, 7);
    }

    @Test
    void whenGetKeyThenOk() {
        TreeAVLMap<Integer, String> tree = new TreeAVLMap<>();
        tree.insert(1, "11");
        tree.insert(2, "22");
        tree.insert(3, "33");
        tree.insert(4, "44");
        tree.insert(5, "55");
        tree.insert(6, "66");
        tree.insert(7, "77");
        assertThat(tree.get(5)).isEqualTo("55");
        assertThat(tree.get(0)).isNull();
    }
}
