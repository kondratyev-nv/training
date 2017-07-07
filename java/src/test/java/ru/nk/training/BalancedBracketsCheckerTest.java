package ru.nk.training;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BalancedBracketsCheckerTest {
    private BalancedBracketsChecker checker;

    @Before
    public void setUp() throws Exception {
        checker = new BalancedBracketsChecker();
    }

    @Test(expected = IllegalArgumentException.class)
    public void failsOnNullString() {
        checker.isBalanced(null);
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
