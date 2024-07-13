package ru.job4j.algo.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Brackets {
    public boolean isValid(String s) {
        var stack = new Stack<>();
        var array = s.toCharArray();
        Map<Character, Character> brackets = new HashMap<>();
        brackets.put(')', '(');
        brackets.put('}', '{');
        brackets.put(']', '[');

        for (char c : array) {
            if (brackets.containsValue(c)) {
                stack.push(c);
            } else if (brackets.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != brackets.get(c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
