package ru.nk.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BalancedBracketsCheckerTest {
    private BalancedBracketsChecker checker;

    @BeforeEach
    public void setUp() {
        checker = new BalancedBracketsChecker();
    }

    @Test
    public void throwsOnNullString() {
        assertThrows(
                IllegalArgumentException.class,
                () -> checker.isBalanced(null)
        );
    }

    @Test
    public void throwsOnStringContainingNonBracketSymbols() {
        assertThrows(
                IllegalArgumentException.class,
                () -> checker.isBalanced("{1}")
        );
    }

    @Test
    public void returnsTrueForEmptyString() {
        assertTrue(checker.isBalanced(""));
    }

    @Test
    public void returnsTrueForTwoBalancedBrackets() {
        assertTrue(checker.isBalanced("()"));
        assertTrue(checker.isBalanced("{}"));
        assertTrue(checker.isBalanced("[]"));
    }

    @Test
    public void returnsTrueForMultipleBalancedBracketsOfSameType() {
        assertTrue(checker.isBalanced("(())()((()))"));
        assertTrue(checker.isBalanced("{{}}{}{{{}}}"));
        assertTrue(checker.isBalanced("[[]][][[[]]]"));
    }

    @Test
    public void returnsTrueForMultipleBalancedBracketsOfDifferentType() {
        assertTrue(checker.isBalanced("({()})[](({}))"));
        assertTrue(checker.isBalanced("{{[[(())]]}}"));
    }

    @Test
    public void returnsFalseForSingleBracket() {
        assertFalse(checker.isBalanced(")"));
        assertFalse(checker.isBalanced("}"));
        assertFalse(checker.isBalanced("]"));

        assertFalse(checker.isBalanced("("));
        assertFalse(checker.isBalanced("{"));
        assertFalse(checker.isBalanced("["));
    }

    @Test
    public void returnsFalseForTwoUnBalancedBrackets() {
        assertFalse(checker.isBalanced(")("));
        assertFalse(checker.isBalanced("}{"));
        assertFalse(checker.isBalanced("]["));
    }

    @Test
    public void returnsFalseWhenHasOddNumberOfBracket() {
        assertFalse(checker.isBalanced("(()()))"));
        assertFalse(checker.isBalanced("{{}"));
        assertFalse(checker.isBalanced("[]]"));
    }

    @Test
    public void returnsFalseForUnBalancedSetOfBracketOfSameType() {
        assertFalse(checker.isBalanced("(()(())))"));
        assertFalse(checker.isBalanced("{{{}"));
        assertFalse(checker.isBalanced("[]]]"));
    }

    @Test
    public void returnsFalseForMultipleUnBalancedBracketsOfDifferentType() {
        assertFalse(checker.isBalanced("{[(])}"));
        assertFalse(checker.isBalanced("({())}[((]{}))"));
    }
}
