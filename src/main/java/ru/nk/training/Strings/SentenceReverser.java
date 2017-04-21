package ru.nk.training.Strings;

/**
 * Write a function that reverses the order of the words in a string.
 * For example, your function should transform the string
 * "Do or do not, there is no try." to "try. no is there not, do or Do".
 * Assume that all words are space delimited and treat punctuation the same as letters.
 */
public class SentenceReverser {
    public String reverseWords(String string, char separator) {
        if (string == null) {
            throw new IllegalArgumentException();
        }

        int n = string.length(), s = 0;
        char[] result = new char[n];
        for (int e = 0; e < n; ++e) {
            char c = string.charAt(e);
            if (c == separator) {
                result[n - e - 1] = c;
                for (int i = s; i < e; ++i) {
                    result[n - e + (i - s)] = string.charAt(i);
                }
                s = e + 1;
            }
        }
        for (int i = s; i < n; ++i) {
            result[i - s] = string.charAt(i);
        }

        return new String(result);
    }
}
