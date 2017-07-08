package ru.nk.training;

import java.util.Stack;

/**
 * A bracket is considered to be any one of the following characters: (, ), {, }, [, or ].
 * Two brackets are considered to be a matched pair if the an opening bracket (i.e., (, [, or {) occurs
 * to the left of a closing bracket (i.e., ), ], or }) of the exact same type.
 * There are three types of matched pairs of brackets: [], {}, and ().
 * A matching pair of brackets is not balanced if the set of brackets it encloses are not matched.
 * For example, {[(])} is not balanced because the contents in between { and } are not balanced.
 * The pair of square brackets encloses a single, unbalanced opening bracket, (,
 * and the pair of parentheses encloses a single, unbalanced closing square bracket, ].
 * By this logic, we say a sequence of brackets is considered to be balanced if the following conditions are met:
 * - It contains no unmatched brackets.
 * - The subset of brackets enclosed within the confines of a matched pair of brackets
 * is also a matched pair of brackets.
 * Given  strings of brackets, determine whether each sequence of brackets is balanced.
 */
public class BalancedBracketsChecker {
    public boolean isBalanced(String expression) {
        if (expression == null) {
            throw new IllegalArgumentException();
        }
        Stack<Integer> stack = new Stack<>();
        for (char symbol : expression.toCharArray()) {
            int b = getBracketType(symbol);
            if (b > 0) {
                int p = stack.isEmpty() ? 0 : stack.pop();
                if (p + b != 0) {
                    return false;
                }
            } else {
                stack.push(b);
            }
        }
        return stack.isEmpty();
    }

    private int getBracketType(char bracket) {
        switch (bracket) {
            case '(':
                return -1;
            case ')':
                return 1;
            case '[':
                return -2;
            case ']':
                return 2;
            case '{':
                return -3;
            case '}':
                return 3;
        }
        throw new IllegalArgumentException();
    }
}
