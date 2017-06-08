package ru.nk.training;

/**
 * Write two conversion routines. The first routine converts a string
 * to a signed integer. You may assume that the string contains only digits and the
 * minus character ('-'), that it is a properly formatted integer number, and that the
 * number is within the range of an int type. The second routine converts a signed
 * integer stored as an int back to a string.
 */
public class IntegerStringConverter {
    private final static int MAX_INT_LENGTH = 11;

    private static int charToDigit(char c) {
        return c - '0';
    }

    public int stringToInt(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException();
        }

        int sign = 1, start = 0, r = 0;
        if (string.charAt(0) == '-') {
            sign = -1;
            start = 1;
        }
        for (int i = start; i < string.length(); ++i) {
            char c = string.charAt(i);
            r = r * 10 + charToDigit(c);
        }
        return sign * r;
    }

    public String intToString(int n) {
        StringBuilder builder = new StringBuilder(MAX_INT_LENGTH);
        int v = n;
        if (n < 0) {
            v = -v;
        }
        do {
            builder.insert(0, v % 10);
            v /= 10;
        } while (v > 0);
        if (n < 0) {
            builder.insert(0, '-');
        }
        return builder.toString();
    }
}
