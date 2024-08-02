package ru.job4j.collection.binarytree;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AvlTreeTest {

    @Test
    void whenAddToEmptyTreeThenListContainsOneElement() {
        AvlTree<Integer> tree = new AvlTree<>();
        assertThat(tree.insert(10)).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(1)
                .containsExactly(10);
    }

    @Test
    void whenAddMaximumNotEndThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 10, 9, 7}) {
            tree.insert(element);
        }
        assertThat(tree.maximum()).isEqualTo(10);
    }

    @Test
    void whenAddMinimumIsEndThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        assertThat(tree.minimum()).isEqualTo(1);
    }

    @Test
    void symmetricalOrderIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenPreOrderThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        assertThat(tree.inPreOrder()).hasSize(7)
                .containsExactly(4, 2, 1, 3, 6, 5, 7);
    }

    @Test
    void whenPostOrderThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        assertThat(tree.inPostOrder()).hasSize(7)
                .containsExactly(1, 3, 2, 5, 7, 6, 4);
    }

    @Test
    public void whenRemoveNodeWithOneHeirThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(20);
        tree.insert(10);
        tree.insert(25);
        tree.insert(30);
        assertThat(tree.remove(25)).isTrue();
        assertThat(tree.contains(25)).isFalse();
        assertThat(tree.contains(30)).isTrue();
    }

    @Test
    public void whenRemoveNodeWithTwoHeirsThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(20);
        tree.insert(15);
        tree.insert(25);
        tree.insert(8);
        tree.insert(9);
        assertThat(tree.remove(15)).isTrue();
        assertThat(tree.contains(15)).isFalse();
        assertThat(tree.contains(8)).isTrue();
        assertThat(tree.contains(9)).isTrue();
    }

    @Test
    public void whenClearTreeThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(10);
        tree.insert(15);
        tree.insert(8);
        tree.insert(6);
        tree.insert(7);
        tree.insert(14);
        tree.insert(16);
        tree.clear();
        assertThat(tree.inSymmetricalOrder().isEmpty()).isTrue();
    }
}
