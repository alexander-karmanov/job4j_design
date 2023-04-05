package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
       boolean result = false;
       Optional<Node<E>> prnt = findBy(parent);
       Optional<Node<E>> chld = findBy(child);
       if (prnt.isPresent()) {
           if (chld.isEmpty()) {
               Node<E> newChild = new Node<>(child);
               prnt.get().children.add(newChild);
               result = true;
           }
       }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(e -> e.value.equals(value));
    }

    public boolean isBinary() {
        Predicate<Node<E>> predicate = e -> e.children.size() > 2;
        return findByPredicate(predicate).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
            break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
