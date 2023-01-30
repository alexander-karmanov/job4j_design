package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkEmptyArray() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("array is empty");
    }

    @Test
    void checkNoKey() {
        NameLoad nameLoad = new NameLoad();
        String word1 = "=1";
        String word2 = "item=2";
        assertThatThrownBy(() -> nameLoad.parse(word1,word2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("does not contain a key")
                .hasMessageContaining(word1);
    }

    @Test
    void checkNoValue() {
        NameLoad nameLoad = new NameLoad();
        String word1 = "item=1";
        String word2 = "item2=";
        assertThatThrownBy(() -> nameLoad.parse(word1, word2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a value")
                .hasMessageContaining(word2);
    }

    @Test
    void checkNoEqualSign() {
        NameLoad nameLoad = new NameLoad();
        String word1 = "item";
        String word2 = "item2=2";
        assertThatThrownBy(() -> nameLoad.parse(word1, word2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain the symbol \"=\"")
                .hasMessageContaining(word1);
    }
}
